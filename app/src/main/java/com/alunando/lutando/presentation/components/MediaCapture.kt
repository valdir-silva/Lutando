package com.alunando.lutando.presentation.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alunando.lutando.R
import com.alunando.lutando.data.media.MediaManager
import com.alunando.lutando.data.media.PermissionManager
import com.alunando.lutando.domain.model.MediaType

/**
 * Componente para captura de mídia usando câmera, microfone e galeria.
 *
 * @param mediaType Tipo de mídia a ser capturada
 * @param onMediaCaptured Callback executado quando a mídia é capturada
 * @param onError Callback executado quando ocorre erro
 * @param modifier Modifier para customização do layout
 */
@Composable
fun MediaCapture(
    mediaType: MediaType,
    onMediaCaptured: (Uri) -> Unit,
    onError: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val mediaManager = remember { MediaManager(context) }
    val permissionManager = remember { PermissionManager(context) }
    var isCapturing by remember { mutableStateOf(false) }
    var showPermissionDialog by remember { mutableStateOf(false) }
    var permissionDenied by remember { mutableStateOf(false) }
    var currentPhotoUri by remember { mutableStateOf<Uri?>(null) }
    var currentVideoUri by remember { mutableStateOf<Uri?>(null) }

    // Launcher para galeria
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { onMediaCaptured(it) }
    }

    // Launcher para captura de foto
    val photoCaptureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success) {
            currentPhotoUri?.let { onMediaCaptured(it) }
        } else {
            onError("Falha ao capturar foto")
        }
    }

    // Launcher para captura de vídeo
    val videoCaptureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo()
    ) { success ->
        if (success) {
            currentVideoUri?.let { onMediaCaptured(it) }
        } else {
            onError("Falha ao capturar vídeo")
        }
    }

    // Launcher para gravação de áudio
    val audioCaptureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { onMediaCaptured(it) }
    }


    // Launcher para permissões
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val allGranted = permissions.values.all { it }
        if (allGranted) {
            // Permissões concedidas, pode capturar
            when (mediaType) {
                MediaType.PHOTO -> capturePhoto(
                    mediaManager,
                    photoCaptureLauncher,
                    currentPhotoUri
                ) { uri ->
                    currentPhotoUri = uri
                }

                MediaType.VIDEO -> captureVideo(
                    mediaManager,
                    videoCaptureLauncher,
                    currentVideoUri
                ) { uri ->
                    currentVideoUri = uri
                }

                MediaType.AUDIO -> {
                    // Áudio é tratado pelo AudioRecorder
                }
            }
        } else {
            permissionDenied = true
            onError("Permissões necessárias não foram concedidas")
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (isCapturing) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            when (mediaType) {
                MediaType.PHOTO -> {
                    PhotoCaptureOptions(
                        onCameraClick = {
                            if (permissionManager.hasAllRequiredPermissions(MediaType.PHOTO)) {
                                capturePhoto(
                                    mediaManager,
                                    photoCaptureLauncher,
                                    currentPhotoUri
                                ) { uri ->
                                    currentPhotoUri = uri
                                }
                            } else {
                                requestPermissions(
                                    permissionManager,
                                    MediaType.PHOTO,
                                    permissionLauncher
                                )
                            }
                        },
                        onGalleryClick = { galleryLauncher.launch("image/*") },
                        onError = onError
                    )
                }

                MediaType.VIDEO -> {
                    VideoCaptureOptions(
                        onCameraClick = {
                            if (permissionManager.hasAllRequiredPermissions(MediaType.VIDEO)) {
                                captureVideo(
                                    mediaManager,
                                    videoCaptureLauncher,
                                    currentVideoUri
                                ) { uri ->
                                    currentVideoUri = uri
                                }
                            } else {
                                requestPermissions(
                                    permissionManager,
                                    MediaType.VIDEO,
                                    permissionLauncher
                                )
                            }
                        },
                        onGalleryClick = { galleryLauncher.launch("video/*") },
                        onError = onError
                    )
                }

                MediaType.AUDIO -> {
                    AudioRecorder(
                        onAudioRecorded = onMediaCaptured,
                        onError = onError,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }

    // Dialog de permissão
    if (showPermissionDialog) {
        AlertDialog(
            onDismissRequest = { showPermissionDialog = false },
            title = { Text("Permissão Necessária") },
            text = {
                Text(
                    when (mediaType) {
                        MediaType.PHOTO -> "Para capturar fotos, o aplicativo precisa de permissão para acessar a câmera e o armazenamento."
                        MediaType.VIDEO -> "Para capturar vídeos, o aplicativo precisa de permissão para acessar a câmera, microfone e o armazenamento."
                        MediaType.AUDIO -> "Para gravar áudio, o aplicativo precisa de permissão para acessar o microfone e o armazenamento."
                    }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showPermissionDialog = false
                        requestPermissions(permissionManager, mediaType, permissionLauncher)
                    }
                ) {
                    Text("Conceder")
                }
            },
            dismissButton = {
                TextButton(onClick = { showPermissionDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
private fun PhotoCaptureOptions(
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
    onError: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Capturar Foto",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Botão da câmera
            Button(
                onClick = onCameraClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_camera_alt),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Câmera")
            }

            // Botão da galeria
            OutlinedButton(
                onClick = onGalleryClick,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_photo_library),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Galeria")
            }
        }
    }
}

@Composable
private fun VideoCaptureOptions(
    onCameraClick: () -> Unit,
    onGalleryClick: () -> Unit,
    onError: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Capturar Vídeo",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Botão da câmera
            Button(
                onClick = onCameraClick,
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_videocam),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Câmera")
            }

            // Botão da galeria
            OutlinedButton(
                onClick = onGalleryClick,
                modifier = Modifier.weight(1f)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_video_library),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Galeria")
            }
        }
    }
}

private fun capturePhoto(
    mediaManager: MediaManager,
    launcher: ActivityResultLauncher<Uri>,
    currentUri: Uri?,
    onUriCreated: (Uri) -> Unit
) {
    try {
        val photoFile = mediaManager.createPhotoFile()
        val photoUri = mediaManager.getUriForFile(photoFile)
        onUriCreated(photoUri)
        launcher.launch(photoUri)
    } catch (e: Exception) {
        // Tratar erro
    }
}

private fun captureVideo(
    mediaManager: MediaManager,
    launcher: ActivityResultLauncher<Uri>,
    currentUri: Uri?,
    onUriCreated: (Uri) -> Unit
) {
    try {
        val videoFile = mediaManager.createVideoFile()
        val videoUri = mediaManager.getUriForFile(videoFile)
        onUriCreated(videoUri)
        launcher.launch(videoUri)
    } catch (e: Exception) {
        // Tratar erro
    }
}


private fun requestPermissions(
    permissionManager: PermissionManager,
    mediaType: MediaType,
    launcher: ActivityResultLauncher<Array<String>>
) {
    val permissions = permissionManager.getRequiredPermissions(mediaType)
    launcher.launch(permissions)
} 