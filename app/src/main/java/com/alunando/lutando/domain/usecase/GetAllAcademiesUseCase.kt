package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.Academy
import com.alunando.lutando.domain.repository.AcademyRepository
import kotlinx.coroutines.flow.Flow

class GetAllAcademiesUseCase(
    private val academyRepository: AcademyRepository
) {
    operator fun invoke(): Flow<List<Academy>> {
        return academyRepository.getAllAcademies()
    }
}