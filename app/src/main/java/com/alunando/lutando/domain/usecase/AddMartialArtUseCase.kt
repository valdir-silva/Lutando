package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.MartialArt
import com.alunando.lutando.domain.repository.MartialArtRepository

/**
 * Use case para adicionar uma nova modalidade de arte marcial.
 */
class AddMartialArtUseCase(
    private val martialArtRepository: MartialArtRepository
) {

    suspend operator fun invoke(martialArt: MartialArt): Long {
        return martialArtRepository.insertMartialArt(martialArt)
    }
} 