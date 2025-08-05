package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.Academy
import com.alunando.lutando.domain.repository.AcademyRepository

class UpdateAcademyUseCase(
    private val academyRepository: AcademyRepository
) {
    suspend operator fun invoke(academy: Academy) {
        academyRepository.updateAcademy(academy)
    }
}