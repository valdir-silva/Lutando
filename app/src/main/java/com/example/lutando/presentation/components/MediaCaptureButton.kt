package com.example.lutando.presentation.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.lutando.domain.model.MediaType
import com.example.lutando.R

/**
 * Botão flutuante para captura de mídia.
 *
 * @param mediaType Tipo de mídia a ser capturada
 * @param onClick Callback executado quando o botão é clicado
 * @param modifier Modifier para customização do layout
 */
@Composable
fun MediaCaptureButton(
    mediaType: MediaType,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val iconRes = when (mediaType) {
        MediaType.PHOTO -> R.drawable.ic_media_photo
        MediaType.VIDEO -> R.drawable.ic_media_video
        MediaType.AUDIO -> R.drawable.ic_media_audio
    }

    val contentDescription = when (mediaType) {
        MediaType.PHOTO -> "Capturar foto"
        MediaType.VIDEO -> "Capturar vídeo"
        MediaType.AUDIO -> "Gravar áudio"
    }

    FloatingActionButton(
        onClick = onClick,
        modifier = modifier.size(56.dp),
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp)
        )
    }
} 