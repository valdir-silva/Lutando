package com.example.lutando.data.local

import androidx.room.*
import com.example.lutando.domain.model.Comment
import kotlinx.coroutines.flow.Flow

/**
 * DAO para operações de comentários no banco de dados.
 */
@Dao
interface CommentDao {
    
    /**
     * Busca todos os comentários de uma técnica específica.
     */
    @Query("SELECT * FROM comments WHERE techniqueId = :techniqueId ORDER BY createdAt DESC")
    fun getCommentsByTechniqueId(techniqueId: Long): Flow<List<Comment>>
    
    /**
     * Verifica se a tabela de comentários existe.
     */
    @Query("SELECT name FROM sqlite_master WHERE type='table' AND name='comments'")
    suspend fun checkCommentsTableExists(): String?
    
    /**
     * Busca um comentário específico por ID.
     */
    @Query("SELECT * FROM comments WHERE id = :commentId")
    suspend fun getCommentById(commentId: Long): Comment?
    
    /**
     * Insere um novo comentário.
     */
    @Insert
    suspend fun insertComment(comment: Comment): Long
    
    /**
     * Atualiza um comentário existente.
     */
    @Update
    suspend fun updateComment(comment: Comment)
    
    /**
     * Remove um comentário específico.
     */
    @Delete
    suspend fun deleteComment(comment: Comment)
    
    /**
     * Remove todos os comentários de uma técnica específica.
     */
    @Query("DELETE FROM comments WHERE techniqueId = :techniqueId")
    suspend fun deleteCommentsByTechniqueId(techniqueId: Long)
    
    /**
     * Conta quantos comentários uma técnica possui.
     */
    @Query("SELECT COUNT(*) FROM comments WHERE techniqueId = :techniqueId")
    suspend fun getCommentCountByTechniqueId(techniqueId: Long): Int
} 