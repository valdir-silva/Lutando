package com.alunando.lutando.data.repository

import com.alunando.lutando.data.local.CommentDao
import com.alunando.lutando.domain.model.Comment
import com.alunando.lutando.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.*

/**
 * Implementação do repositório de comentários.
 */
class CommentRepositoryImpl(
    private val commentDao: CommentDao
) : CommentRepository {
    
    override fun getCommentsByTechniqueId(techniqueId: Long): Flow<List<Comment>> {
        println("Buscando comentários para técnica ID: $techniqueId")
        return commentDao.getCommentsByTechniqueId(techniqueId)
    }
    
    override suspend fun getCommentById(commentId: Long): Comment? {
        return commentDao.getCommentById(commentId)
    }
    
    override suspend fun addComment(comment: Comment): Long {
        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val commentWithTimestamp = comment.copy(
            createdAt = currentTime,
            updatedAt = currentTime
        )
        println("Adicionando comentário: $commentWithTimestamp")
        val commentId = commentDao.insertComment(commentWithTimestamp)
        println("Comentário adicionado com ID: $commentId")
        return commentId
    }
    
    override suspend fun updateComment(comment: Comment) {
        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val commentWithTimestamp = comment.copy(updatedAt = currentTime)
        commentDao.updateComment(commentWithTimestamp)
    }
    
    override suspend fun deleteComment(comment: Comment) {
        commentDao.deleteComment(comment)
    }
    
    override suspend fun deleteCommentsByTechniqueId(techniqueId: Long) {
        commentDao.deleteCommentsByTechniqueId(techniqueId)
    }
    
    override suspend fun getCommentCountByTechniqueId(techniqueId: Long): Int {
        return commentDao.getCommentCountByTechniqueId(techniqueId)
    }
} 