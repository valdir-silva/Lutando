package com.alunando.lutando.data.repository

import com.alunando.lutando.domain.model.Technique
import com.alunando.lutando.domain.repository.TechniqueRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

/**
 * Implementação do repositório de técnicas com Firebase.
 */
class TechniqueRepositoryImpl(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : TechniqueRepository {

    private val collection = firestore.collection("techniques")

    override fun getTechniquesByMartialArt(martialArtId: String): Flow<List<Technique>> {
        val currentUserUid = firebaseAuth.currentUser?.uid
        return if (currentUserUid != null) {
            collection
                .whereEqualTo("martialArtId", martialArtId)
                .whereEqualTo("creatorUid", currentUserUid)
                .orderBy("name", Query.Direction.ASCENDING)
                .snapshots()
                .map { snapshot -> snapshot.toObjects() }
        } else {
            kotlinx.coroutines.flow.flowOf(emptyList())
        }
    }

    override suspend fun getTechniqueById(id: String): Technique? {
        return try {
            collection.document(id).get().await().toObject(Technique::class.java)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun searchTechniques(martialArtId: String, query: String): List<Technique> {
        val currentUserUid = firebaseAuth.currentUser?.uid
        return if (currentUserUid != null) {
            try {
                collection
                    .whereEqualTo("martialArtId", martialArtId)
                    .whereEqualTo("creatorUid", currentUserUid)
                    .whereGreaterThanOrEqualTo("name", query)
                    .whereLessThanOrEqualTo("name", query + '')
                    .get().await().toObjects()
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    override suspend fun insertTechnique(technique: Technique): String {
        val document = collection.document()
        technique.id = document.id
        technique.creatorUid = firebaseAuth.currentUser?.uid
        document.set(technique).await()
        return document.id
    }

    override suspend fun updateTechnique(technique: Technique) {
        collection.document(technique.id).set(technique).await()
    }

    override suspend fun deleteTechnique(technique: Technique) {
        collection.document(technique.id).delete().await()
    }

    override suspend fun deleteTechniqueById(id: String) {
        collection.document(id).delete().await()
    }

    override suspend fun deleteTechniquesByMartialArt(martialArtId: String) {
        val snapshot = collection.whereEqualTo("martialArtId", martialArtId).get().await()
        for (document in snapshot.documents) {
            document.reference.delete().await()
        }
    }
}
