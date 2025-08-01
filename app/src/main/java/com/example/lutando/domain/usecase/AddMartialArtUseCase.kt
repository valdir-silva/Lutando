package com.example.lutando.domain.usecase

import com.example.lutando.domain.model.MartialArt
import com.example.lutando.domain.repository.MartialArtRepository

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