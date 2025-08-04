package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.Comment
import com.alunando.lutando.domain.repository.CommentRepository
import kotlinx.coroutines.flow.Flow

/**
 * Use case para buscar comentários de uma técnica específica.
 */
class GetCommentsByTechniqueUseCase(
    private val commentRepository: CommentRepository
) {
    
    /**
     * Executa o use case para buscar comentários de uma técnica.
     *
     * @param techniqueId ID da técnica
     * @return Flow com lista de comentários ordenados por data de criação (mais recentes primeiro)
     */
    operator fun invoke(techniqueId: String): Flow<List<Comment>> {
        return commentRepository.getCommentsByTechniqueId(techniqueId)
    }
} 