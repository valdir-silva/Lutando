package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.Academy
import com.alunando.lutando.domain.repository.AcademyRepository

import kotlinx.coroutines.flow.first

class AddAcademyUseCase(
    private val academyRepository: AcademyRepository,
    private val getAllAcademiesUseCase: GetAllAcademiesUseCase
) {
    suspend operator fun invoke(academy: Academy): String {
        val existingAcademies = getAllAcademiesUseCase().first()
        if (existingAcademies.isNotEmpty()) {
            throw UserAlreadyHasAcademyException()
        }
        return academyRepository.insertAcademy(academy)
    }
}