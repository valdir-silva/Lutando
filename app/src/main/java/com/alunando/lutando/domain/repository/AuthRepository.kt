
package com.alunando.lutando.domain.repository

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    suspend fun signInAnonymously(): FirebaseUser?
    fun getCurrentUser(): FirebaseUser?
}
