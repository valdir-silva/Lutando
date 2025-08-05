package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.Academy
import com.alunando.lutando.domain.repository.AcademyRepository

class GetAcademyByIdUseCase(
    private val academyRepository: AcademyRepository
) {
    suspend operator fun invoke(id: String): Academy? {
        return academyRepository.getAcademyById(id)
    }
}