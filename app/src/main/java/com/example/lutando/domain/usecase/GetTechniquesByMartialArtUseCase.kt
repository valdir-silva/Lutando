package com.example.lutando.domain.usecase

import com.example.lutando.domain.model.Technique
import com.example.lutando.domain.repository.TechniqueRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case para obter técnicas por modalidade de arte marcial.
 */
class GetTechniquesByMartialArtUseCase(
    private val techniqueRepository: TechniqueRepository
) {
    
    operator fun invoke(martialArtId: Long): Flow<List<Technique>> {
        return techniqueRepository.getTechniquesByMartialArt(martialArtId)
    }
} 