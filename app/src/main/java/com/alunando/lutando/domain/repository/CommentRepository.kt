package com.alunando.lutando.domain.repository

import com.alunando.lutando.domain.model.Comment
import kotlinx.coroutines.flow.Flow

/**
 * Interface do repositório para operações de comentários.
 */
interface CommentRepository {
    
    /**
     * Busca todos os comentários de uma técnica específica.
     */
    fun getCommentsByTechniqueId(techniqueId: String): Flow<List<Comment>>
    
    /**
     * Busca um comentário específico por ID.
     */
    suspend fun getCommentById(commentId: String): Comment?
    
    /**
     * Adiciona um novo comentário.
     */
    suspend fun addComment(comment: Comment): String
    
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
    suspend fun deleteCommentsByTechniqueId(techniqueId: String)
    
    /**
     * Conta quantos comentários uma técnica possui.
     */
    suspend fun getCommentCountByTechniqueId(techniqueId: String): Int
} 