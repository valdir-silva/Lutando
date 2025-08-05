package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.Academy
import com.alunando.lutando.domain.repository.AcademyRepository

class AddAcademyUseCase(
    private val academyRepository: AcademyRepository
) {
    suspend operator fun invoke(academy: Academy): String {
        return academyRepository.insertAcademy(academy)
    }
}