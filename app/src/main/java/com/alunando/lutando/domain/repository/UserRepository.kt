package com.alunando.lutando.domain.repository

import com.alunando.lutando.domain.model.User
import kotlinx.coroutines.flow.Flow

/**
 * Interface do repositório de usuários.
 */
interface UserRepository {

    fun getCurrentUser(): Flow<User?>

    suspend fun getUserById(id: Long): User?

    suspend fun insertUser(user: User): Long

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun deleteUserById(id: Long)
} 