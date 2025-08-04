
package com.alunando.lutando.data.repository

import com.alunando.lutando.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepositoryImpl(private val firebaseAuth: FirebaseAuth) : AuthRepository {

    override suspend fun signInAnonymously(): FirebaseUser? {
        return try {
            val authResult = firebaseAuth.signInAnonymously().await()
            authResult.user
        } catch (e: Exception) {
            null
        }
    }

    override fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }
}
