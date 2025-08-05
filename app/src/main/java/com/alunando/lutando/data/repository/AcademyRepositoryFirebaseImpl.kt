package com.alunando.lutando.data.repository

import com.alunando.lutando.domain.model.Academy
import com.alunando.lutando.domain.repository.AcademyRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth
import android.util.Log

class AcademyRepositoryFirebaseImpl(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : AcademyRepository {

    private val collection = firestore.collection("academies")

    override fun getAllAcademies(): Flow<List<Academy>> {
        val currentUserUid = firebaseAuth.currentUser?.uid
        return if (currentUserUid != null) {
            collection.whereEqualTo("creatorUid", currentUserUid).snapshots().map { snapshot ->
                snapshot.toObjects<Academy>()
            }
        } else {
            flowOf(emptyList())
        }
    }

    override suspend fun getAcademyById(id: String): Academy? {
        return try {
            collection.document(id).get().await().toObject(Academy::class.java)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun insertAcademy(academy: Academy): String {
        return try {
            val document = collection.document()
            academy.id = document.id
            academy.creatorUid = firebaseAuth.currentUser?.uid
            document.set(academy).await()
            document.id
        } catch (e: Exception) {
            Log.e("AcademyRepository", "Error adding academy", e)
            throw e
        }
    }

    override suspend fun updateAcademy(academy: Academy) {
        collection.document(academy.id).set(academy).await()
    }

    override suspend fun deleteAcademy(academy: Academy) {
        collection.document(academy.id).delete().await()
    }

    override suspend fun deleteAcademyById(id: String) {
        collection.document(id).delete().await()
    }
}