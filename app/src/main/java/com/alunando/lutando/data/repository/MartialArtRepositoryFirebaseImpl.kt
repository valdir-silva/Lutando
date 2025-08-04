package com.alunando.lutando.data.repository

import com.alunando.lutando.domain.model.MartialArt
import com.alunando.lutando.domain.repository.MartialArtRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import com.google.firebase.auth.FirebaseAuth

class MartialArtRepositoryFirebaseImpl(
    private val firestore: FirebaseFirestore,
    private val firebaseAuth: FirebaseAuth
) : MartialArtRepository {

    private val collection = firestore.collection("martial_arts")

    override fun getAllMartialArts(): Flow<List<MartialArt>> {
        val currentUserUid = firebaseAuth.currentUser?.uid
        return if (currentUserUid != null) {
            collection.whereEqualTo("creatorUid", currentUserUid).snapshots().map { snapshot ->
                snapshot.toObjects<MartialArt>()
            }
        } else {
            // Retorna um fluxo vazio se não houver usuário logado
            kotlinx.coroutines.flow.flowOf(emptyList())
        }
    }

    override suspend fun getMartialArtById(id: String): MartialArt? {
        return try {
            collection.document(id).get().await().toObject(MartialArt::class.java)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun searchMartialArts(query: String): List<MartialArt> {
        // Firestore search capabilities are different from SQL LIKE.
        // This will require a more advanced implementation (e.g., using a search service).
        // For now, we can do a simple "startsWith" query.
        return try {
            collection.whereGreaterThanOrEqualTo("name", query)
                .whereLessThanOrEqualTo("name", query + '\uf8ff')
                .get().await().toObjects()
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun insertMartialArt(martialArt: MartialArt): String {
        val document = collection.document()
        martialArt.id = document.id
        martialArt.creatorUid = firebaseAuth.currentUser?.uid
        document.set(martialArt).await()
        return document.id
    }

    override suspend fun updateMartialArt(martialArt: MartialArt) {
        collection.document(martialArt.id).set(martialArt).await()
    }

    override suspend fun deleteMartialArt(martialArt: MartialArt) {
        collection.document(martialArt.id).delete().await()
    }

    override suspend fun deleteMartialArtById(id: String) {
        collection.document(id).delete().await()
    }
}
