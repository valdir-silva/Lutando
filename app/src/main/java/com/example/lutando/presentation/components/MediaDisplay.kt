package com.example.lutando.presentation.components

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lutando.domain.model.MediaType

/**
 * Componente que exibe mídia baseado no tipo.
 * 
 * @param uri URI do arquivo de mídia
 * @param mediaType Tipo de mídia
 * @param contentDescription Descrição do conteúdo para acessibilidade
 * @param modifier Modifier para customização do layout
 */
@Composable
fun MediaDisplay(
    uri: Uri,
    mediaType: MediaType,
    contentDescription: String? = null,
    modifier: Modifier = Modifier
) {
    when (mediaType) {
        MediaType.PHOTO -> {
            MediaImageView(
                uri = uri,
                contentDescription = contentDescription,
                modifier = modifier
            )
        }
        MediaType.VIDEO -> {
            MediaPlayer(
                uri = uri,
                mediaType = mediaType,
                modifier = modifier
            )
        }
        MediaType.AUDIO -> {
            MediaPlayer(
                uri = uri,
                mediaType = mediaType,
                modifier = modifier
            )
        }
    }
} 