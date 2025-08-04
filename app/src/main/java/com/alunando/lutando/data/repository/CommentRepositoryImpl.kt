package com.alunando.lutando.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import com.alunando.lutando.domain.model.Comment
import com.alunando.lutando.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import java.text.SimpleDateFormat
import java.util.*

/**
 * Implementação do repositório de comentários.
 */
class CommentRepositoryImpl(
    private val firestore: FirebaseFirestore
) : CommentRepository {

    private val commentsCollection = firestore.collection("comments")

    override fun getCommentsByTechniqueId(techniqueId: String): Flow<List<Comment>> = callbackFlow {
        println("Buscando comentários para técnica ID: $techniqueId")
        val registration = commentsCollection
            .whereEqualTo("techniqueId", techniqueId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    println("Erro ao buscar comentários: ${e.message}")
                    close(e) // Fecha o flow com o erro
                    return@addSnapshotListener
                }

                if (snapshot != null) {
                    val comments = snapshot.documents.mapNotNull { document ->
                        document.toObject(Comment::class.java)?.copy(id = document.id)
                    }
                    trySend(comments).isSuccess // Envia os comentários para o flow
                }
            }

        awaitClose { registration.remove() } // Remove o listener quando o flow é cancelado ou fechado
    }

    
    override suspend fun getCommentById(commentId: String): Comment? {
        return commentsCollection.document(commentId).get().await().toObject(Comment::class.java)?.copy(id = commentId)
    }
    
    override suspend fun addComment(comment: Comment): String {
        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val commentWithTimestamp = comment.copy(
            createdAt = currentTime,
            updatedAt = currentTime
        )
        println("Adicionando comentário: $commentWithTimestamp")
        val documentRef = commentsCollection.add(commentWithTimestamp).await()
        println("Comentário adicionado com ID: ${documentRef.id}")
        return documentRef.id
    }
    
    override suspend fun updateComment(comment: Comment) {
        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val commentWithTimestamp = comment.copy(updatedAt = currentTime)
        commentsCollection.document(comment.id).set(commentWithTimestamp).await()
    }
    
    override suspend fun deleteComment(comment: Comment) {
        commentsCollection.document(comment.id).delete().await()
    }
    
    override suspend fun deleteCommentsByTechniqueId(techniqueId: String) {
        val snapshot = commentsCollection.whereEqualTo("techniqueId", techniqueId).get().await()
        for (document in snapshot.documents) {
            document.reference.delete().await()
        }
    }
    
    override suspend fun getCommentCountByTechniqueId(techniqueId: String): Int {
        val snapshot = commentsCollection.whereEqualTo("techniqueId", techniqueId).get().await()
        return snapshot.size()
    }
}