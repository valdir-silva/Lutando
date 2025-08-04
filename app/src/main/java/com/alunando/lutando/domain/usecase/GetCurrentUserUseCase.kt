package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.repository.AuthRepository

class GetCurrentUserUseCase(private val authRepository: AuthRepository) {
    operator fun invoke() = authRepository.getCurrentUser()
}