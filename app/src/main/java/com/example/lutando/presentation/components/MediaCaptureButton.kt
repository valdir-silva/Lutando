package com.example.lutando.presentation.components

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.lutando.R
import com.example.lutando.domain.model.MediaType

/**
 * Botão de captura de mídia para ser usado em formulários.
 *
 * @param mediaType Tipo de mídia a ser capturada
 * @param onMediaCaptured Callback executado quando a mídia é capturada
 * @param onError Callback executado quando ocorre erro
 * @param modifier Modifier para customização do layout
 */
@Composable
fun MediaCaptureButton(
    mediaType: MediaType,
    onMediaCaptured: (Uri) -> Unit,
    onError: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var showCaptureDialog by remember { mutableStateOf(false) }
    var showMediaCapture by remember { mutableStateOf(false) }

    Button(
        onClick = { showCaptureDialog = true },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary
        )
    ) {
        Icon(
            painter = when (mediaType) {
                MediaType.PHOTO -> painterResource(id = R.drawable.ic_camera_alt)
                MediaType.VIDEO -> painterResource(id = R.drawable.ic_videocam)
                MediaType.AUDIO -> painterResource(id = R.drawable.ic_mic)
            },
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = when (mediaType) {
                MediaType.PHOTO -> "Capturar Foto"
                MediaType.VIDEO -> "Capturar Vídeo"
                MediaType.AUDIO -> "Gravar Áudio"
            },
            fontWeight = FontWeight.Medium
        )
    }

    if (showCaptureDialog) {
        AlertDialog(
            onDismissRequest = { showCaptureDialog = false },
            title = {
                Text(
                    text = when (mediaType) {
                        MediaType.PHOTO -> "Capturar Foto"
                        MediaType.VIDEO -> "Capturar Vídeo"
                        MediaType.AUDIO -> "Gravar Áudio"
                    },
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = when (mediaType) {
                        MediaType.PHOTO -> "Escolha como capturar a foto da técnica"
                        MediaType.VIDEO -> "Escolha como capturar o vídeo da técnica"
                        MediaType.AUDIO -> "Escolha como gravar o áudio explicativo"
                    }
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showCaptureDialog = false
                        showMediaCapture = true
                    }
                ) {
                    Text("Capturar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showCaptureDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    if (showMediaCapture) {
        MediaCapture(
            mediaType = mediaType,
            onMediaCaptured = { uri ->
                onMediaCaptured(uri)
                showMediaCapture = false
            },
            onError = { error ->
                onError(error)
                showMediaCapture = false
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}

/**
 * Botão de remoção de mídia.
 *
 * @param mediaType Tipo de mídia a ser removida
 * @param onRemove Callback executado quando a mídia é removida
 * @param modifier Modifier para customização do layout
 */
@Composable
fun MediaRemoveButton(
    mediaType: MediaType,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onRemove,
        modifier = modifier,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.error
        )
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = when (mediaType) {
                MediaType.PHOTO -> "Remover Foto"
                MediaType.VIDEO -> "Remover Vídeo"
                MediaType.AUDIO -> "Remover Áudio"
            },
            fontWeight = FontWeight.Medium
        )
    }
}

/**
 * Card de preview de mídia.
 *
 * @param mediaType Tipo de mídia
 * @param uri URI da mídia
 * @param onRemove Callback executado quando a mídia é removida
 * @param modifier Modifier para customização do layout
 */
@Composable
fun MediaPreviewCard(
    mediaType: MediaType,
    uri: Uri,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = when (mediaType) {
                            MediaType.PHOTO -> painterResource(id = R.drawable.ic_media_photo)
                            MediaType.VIDEO -> painterResource(id = R.drawable.ic_media_video)
                            MediaType.AUDIO -> painterResource(id = R.drawable.ic_media_audio)
                        },
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = when (mediaType) {
                            MediaType.PHOTO -> "Foto capturada"
                            MediaType.VIDEO -> "Vídeo capturado"
                            MediaType.AUDIO -> "Áudio gravado"
                        },
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium
                    )
                }

                IconButton(onClick = onRemove) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Remover",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Preview da mídia
            when (mediaType) {
                MediaType.PHOTO -> {
                    MediaImageView(
                        uri = uri,
                        contentDescription = "Preview da foto",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }

                MediaType.VIDEO -> {
                    MediaPlayer(
                        uri = uri,
                        mediaType = mediaType,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }

                MediaType.AUDIO -> {
                    MediaPlayer(
                        uri = uri,
                        mediaType = mediaType,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    )
                }
            }
        }
    }
} 