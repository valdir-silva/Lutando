package com.example.lutando.presentation.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.lutando.data.media.MediaManager
import com.example.lutando.domain.model.MediaType

/**
 * Componente para captura de mídia usando galeria.
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
    var isCapturing by remember { mutableStateOf(false) }
    
    // Launcher para galeria
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let { onMediaCaptured(it) }
    }
    
    Box(modifier = modifier.fillMaxSize()) {
        if (isCapturing) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            when (mediaType) {
                MediaType.PHOTO -> {
                    PhotoCapture(
                        onPhotoCaptured = { uri ->
                            onMediaCaptured(uri)
                        },
                        onError = onError,
                        galleryLauncher = galleryLauncher
                    )
                }
                MediaType.VIDEO -> {
                    VideoCapture(
                        onVideoCaptured = { uri ->
                            onMediaCaptured(uri)
                        },
                        onError = onError,
                        galleryLauncher = galleryLauncher
                    )
                }
                MediaType.AUDIO -> {
                    AudioCapture(
                        onAudioCaptured = { uri ->
                            onMediaCaptured(uri)
                        },
                        onError = onError,
                        galleryLauncher = galleryLauncher
                    )
                }
            }
        }
    }
}

@Composable
private fun PhotoCapture(
    onPhotoCaptured: (Uri) -> Unit,
    onError: (String) -> Unit,
    galleryLauncher: ActivityResultLauncher<String>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { galleryLauncher.launch("image/*") }
        ) {
            Text("Selecionar foto da galeria")
        }
    }
}

@Composable
private fun VideoCapture(
    onVideoCaptured: (Uri) -> Unit,
    onError: (String) -> Unit,
    galleryLauncher: ActivityResultLauncher<String>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { galleryLauncher.launch("video/*") }
        ) {
            Text("Selecionar vídeo da galeria")
        }
    }
}

@Composable
private fun AudioCapture(
    onAudioCaptured: (Uri) -> Unit,
    onError: (String) -> Unit,
    galleryLauncher: ActivityResultLauncher<String>
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = { galleryLauncher.launch("audio/*") }
        ) {
            Text("Selecionar áudio da galeria")
        }
    }
} 