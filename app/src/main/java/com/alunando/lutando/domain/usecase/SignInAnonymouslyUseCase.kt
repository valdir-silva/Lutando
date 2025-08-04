
package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.repository.AuthRepository

class SignInAnonymouslyUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke() = authRepository.signInAnonymously()
}
