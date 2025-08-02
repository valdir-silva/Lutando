package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.MartialArt
import com.alunando.lutando.domain.repository.MartialArtRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case para obter todas as modalidades de artes marciais.
 */
class GetAllMartialArtsUseCase(
    private val martialArtRepository: MartialArtRepository
) {
    operator fun invoke(): Flow<List<MartialArt>> {
        return martialArtRepository.getAllMartialArts()
    }
} 