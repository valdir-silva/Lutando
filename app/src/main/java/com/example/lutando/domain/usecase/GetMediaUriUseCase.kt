package com.example.lutando.domain.usecase

import android.net.Uri
import com.example.lutando.domain.repository.MediaRepository

/**
 * Use case para obter URI de arquivos de mídia.
 */
class GetMediaUriUseCase(
    private val mediaRepository: MediaRepository
) {

    /**
     * Executa o use case para obter o URI de um arquivo de mídia.
     *
     * @param filePath Caminho do arquivo
     * @return Result contendo o URI do arquivo ou null se não existir
     */
    suspend operator fun invoke(filePath: String): Result<Uri?> {
        return mediaRepository.getMediaUri(filePath)
    }
} 