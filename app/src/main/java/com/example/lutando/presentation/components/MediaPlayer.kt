package com.example.lutando.presentation.components

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.example.lutando.R
import com.example.lutando.domain.model.MediaType

/**
 * Componente de player de mídia usando ExoPlayer.
 *
 * @param uri URI do arquivo de mídia
 * @param mediaType Tipo de mídia
 * @param modifier Modifier para customização do layout
 */
@Composable
fun MediaPlayer(
    uri: Uri,
    mediaType: MediaType,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val isPreview = LocalInspectionMode.current

    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        when {
            isPreview -> {
                // Mostrar placeholder no preview
                MediaPlayerPlaceholder(mediaType = mediaType)
            }

            else -> {
                // Usar ExoPlayer no app real
                MediaPlayerContent(uri = uri, mediaType = mediaType, context = context)
            }
        }
    }
}

@Composable
private fun MediaPlayerContent(
    uri: Uri,
    mediaType: MediaType,
    context: android.content.Context
) {
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(uri)
            setMediaItem(mediaItem)
            prepare()
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    when (mediaType) {
        MediaType.VIDEO -> {
            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        player = exoPlayer
                        useController = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )
        }

        MediaType.AUDIO -> {
            AndroidView(
                factory = { ctx ->
                    PlayerView(ctx).apply {
                        player = exoPlayer
                        useController = true
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(4f / 1f)
            )
        }

        else -> {
            // Para fotos, não usar MediaPlayer
        }
    }
}

@Composable
@Preview
private fun MediaPlayerContentPreview() {
    val isPreview = LocalInspectionMode.current
    if (isPreview) {
        // Opção 1: Use o placeholder existente
        MediaPlayerPlaceholder(mediaType = MediaType.VIDEO)

        // Opção 2: Ou, se você precisar visualizar alguns aspectos do MediaPlayerContent
        // você pode criar uma versão simplificada ou passar dados mock que não
        // acionem a inicialização completa do ExoPlayer.
        // Por exemplo, você pode passar um sinalizador para MediaPlayerContent
        // para pular a configuração do ExoPlayer na visualização.

    } else {
        // Este caminho só seria usado se você de alguma forma chamasse
        // MediaPlayerContentPreview diretamente em um contexto que não seja de visualização (improvável).
        val context = LocalContext.current
        MediaPlayerContent(
            uri = "android.resource://com.example.lutando/raw/sample_video".toUri(),
            mediaType = MediaType.VIDEO,
            context = context
        )
    }
}

@Composable
private fun MediaPlayerPlaceholder(mediaType: MediaType) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(if (mediaType == MediaType.VIDEO) 16f / 9f else 4f / 1f)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = when (mediaType) {
                    MediaType.VIDEO -> painterResource(id = R.drawable.ic_media_video)
                    MediaType.AUDIO -> painterResource(id = R.drawable.ic_media_audio)
                    else -> painterResource(id = R.drawable.ic_media_photo)
                },
                contentDescription = null,
                modifier = Modifier.size(48.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = when (mediaType) {
                    MediaType.VIDEO -> "Preview de Vídeo"
                    MediaType.AUDIO -> "Preview de Áudio"
                    else -> "Preview de Foto"
                },
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
fun MediaPlayerPreview() {
    MediaPlayer(
        uri = "".toUri(),
        mediaType = MediaType.VIDEO
    )
}

@Preview
@Composable
fun MediaPlayerPlaceholderPreview() {
    MediaPlayerPlaceholder(mediaType = MediaType.VIDEO)
}
