package com.example.lutando.domain.usecase

import com.example.lutando.domain.model.User
import com.example.lutando.domain.repository.UserRepository
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