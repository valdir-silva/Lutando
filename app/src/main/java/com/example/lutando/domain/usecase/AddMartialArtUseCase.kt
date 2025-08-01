package com.example.lutando.domain.usecase

import com.example.lutando.domain.model.MartialArt
import com.example.lutando.domain.repository.MartialArtRepository
import javax.inject.Inject

/**
 * Use case para adicionar uma nova modalidade de arte marcial.
 */
class AddMartialArtUseCase @Inject constructor(
    private val martialArtRepository: MartialArtRepository
) {

    suspend operator fun invoke(martialArt: MartialArt): Long {
        return martialArtRepository.insertMartialArt(martialArt)
    }
} 