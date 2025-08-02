package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.repository.MediaRepository

/**
 * Use case para excluir arquivos de mídia.
 */
class DeleteMediaFileUseCase(
    private val mediaRepository: MediaRepository
) {

    /**
     * Executa o use case para excluir um arquivo de mídia.
     *
     * @param filePath Caminho do arquivo a ser excluído
     * @return Result contendo true se excluído com sucesso ou erro
     */
    suspend operator fun invoke(filePath: String): Result<Boolean> {
        return mediaRepository.deleteMediaFile(filePath)
    }
} 