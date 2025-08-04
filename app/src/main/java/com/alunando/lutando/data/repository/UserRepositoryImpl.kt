package com.alunando.lutando.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.tasks.await
import com.alunando.lutando.domain.model.User
import com.alunando.lutando.domain.repository.UserRepository

class UserRepositoryImpl(
    private val firestore: FirebaseFirestore
) : UserRepository {

    private val usersCollection = firestore.collection("users")

    override fun getCurrentUser(): Flow<User?> {
        // Implementar lógica para obter o usuário logado do Firebase Auth
        // Por enquanto, retorna um flow vazio ou nulo
        return flowOf(null)
    }

    override suspend fun getUserById(id: String): User? {
        return usersCollection.document(id).get().await().toObject(User::class.java)?.copy(id = id)
    }

    override suspend fun insertUser(user: User): String {
        val documentRef = usersCollection.add(user).await()
        return documentRef.id
    }

    override suspend fun updateUser(user: User) {
        usersCollection.document(user.id).set(user).await()
    }

    override suspend fun deleteUser(user: User) {
        usersCollection.document(user.id).delete().await()
    }

    override suspend fun deleteUserById(id: String) {
        usersCollection.document(id).delete().await()
    }
} 