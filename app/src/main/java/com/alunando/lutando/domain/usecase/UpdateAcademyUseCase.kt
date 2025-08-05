package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.Academy
import com.alunando.lutando.domain.repository.AcademyRepository

class UpdateAcademyUseCase(
    private val academyRepository: AcademyRepository,
    private val getAcademyByIdUseCase: GetAcademyByIdUseCase
) {
    suspend operator fun invoke(academy: Academy) {
        val existingAcademy = getAcademyByIdUseCase(academy.id)
        if (existingAcademy != null) {
            academy.creatorUid = existingAcademy.creatorUid
        }
        academyRepository.updateAcademy(academy)
    }
}