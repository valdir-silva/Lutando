package com.example.lutando.domain.repository

import com.example.lutando.domain.model.Comment
import kotlinx.coroutines.flow.Flow

/**
 * Interface do repositório para operações de comentários.
 */
interface CommentRepository {
    
    /**
     * Busca todos os comentários de uma técnica específica.
     */
    fun getCommentsByTechniqueId(techniqueId: Long): Flow<List<Comment>>
    
    /**
     * Busca um comentário específico por ID.
     */
    suspend fun getCommentById(commentId: Long): Comment?
    
    /**
     * Adiciona um novo comentário.
     */
    suspend fun addComment(comment: Comment): Long
    
    /**
     * Atualiza um comentário existente.
     */
    suspend fun updateComment(comment: Comment)
    
    /**
     * Remove um comentário.
     */
    suspend fun deleteComment(comment: Comment)
    
    /**
     * Remove todos os comentários de uma técnica.
     */
    suspend fun deleteCommentsByTechniqueId(techniqueId: Long)
    
    /**
     * Conta quantos comentários uma técnica possui.
     */
    suspend fun getCommentCountByTechniqueId(techniqueId: Long): Int
} 