package com.alunando.lutando.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.channels.awaitClose
import com.alunando.lutando.domain.model.Technique
import com.alunando.lutando.domain.repository.TechniqueRepository
import kotlinx.coroutines.flow.Flow

/**
 * Implementação do repositório de técnicas.
 */
class TechniqueRepositoryImpl(
    private val firestore: FirebaseFirestore
) : TechniqueRepository {

    private val techniquesCollection = firestore.collection("techniques")

    override fun getTechniquesByMartialArt(martialArtId: String): Flow<List<Technique>> = callbackFlow {
        val registration = techniquesCollection
            .whereEqualTo("martialArtId", martialArtId)
            .orderBy("name", Query.Direction.ASCENDING)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    close(e)
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val techniques = snapshot.documents.mapNotNull { document ->
                        document.toObject(Technique::class.java)?.copy(id = document.id)
                    }
                    trySend(techniques).isSuccess
                }
            }
        awaitClose { registration.remove() }
    }

    override suspend fun getTechniqueById(id: String): Technique? {
        return techniquesCollection.document(id).get().await().toObject(Technique::class.java)?.copy(id = id)
    }

    override suspend fun searchTechniques(martialArtId: String, query: String): List<Technique> {
        val snapshot = techniquesCollection
            .whereEqualTo("martialArtId", martialArtId)
            .orderBy("name")
            .startAt(query)
            .endAt(query + "\uf8ff")
            .get()
            .await()
        return snapshot.documents.mapNotNull { document ->
            document.toObject(Technique::class.java)?.copy(id = document.id)
        }
    }

    override suspend fun insertTechnique(technique: Technique): String {
        val documentRef = techniquesCollection.add(technique).await()
        return documentRef.id
    }

    override suspend fun updateTechnique(technique: Technique) {
        techniquesCollection.document(technique.id).set(technique).await()
    }

    override suspend fun deleteTechnique(technique: Technique) {
        techniquesCollection.document(technique.id).delete().await()
    }

    override suspend fun deleteTechniqueById(id: String) {
        techniquesCollection.document(id).delete().await()
    }

    override suspend fun deleteTechniquesByMartialArt(martialArtId: String) {
        val snapshot = techniquesCollection.whereEqualTo("martialArtId", martialArtId).get().await()
        for (document in snapshot.documents) {
            document.reference.delete().await()
        }
    }
} 