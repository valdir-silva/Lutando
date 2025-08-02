package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.User
import com.alunando.lutando.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case para obter o usuário atual.
 */
class GetCurrentUserUseCase(
    private val userRepository: UserRepository
) {
    operator fun invoke(): Flow<User?> {
        return userRepository.getCurrentUser()
    }
} 