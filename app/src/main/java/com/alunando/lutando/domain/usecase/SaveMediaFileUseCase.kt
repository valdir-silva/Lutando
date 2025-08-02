package com.alunando.lutando.domain.usecase

import android.net.Uri
import com.alunando.lutando.domain.model.MediaType
import com.alunando.lutando.domain.repository.MediaRepository

/**
 * Use case para salvar arquivos de mídia.
 */
class SaveMediaFileUseCase(
    private val mediaRepository: MediaRepository
) {

    /**
     * Executa o use case para salvar um arquivo de mídia.
     *
     * @param sourceUri URI do arquivo de origem
     * @param mediaType Tipo de mídia
     * @return Result contendo o caminho do arquivo salvo ou erro
     */
    suspend operator fun invoke(sourceUri: Uri, mediaType: MediaType): Result<String> {
        return mediaRepository.saveMediaFile(sourceUri, mediaType)
    }
} 