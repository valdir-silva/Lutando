package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.Comment
import com.alunando.lutando.domain.repository.CommentRepository

/**
 * Use case para atualizar um comentário existente.
 */
class UpdateCommentUseCase(
    private val commentRepository: CommentRepository
) {
    
    /**
     * Executa o use case para atualizar um comentário.
     *
     * @param comment Comentário atualizado
     */
    suspend operator fun invoke(comment: Comment) {
        commentRepository.updateComment(comment)
    }
} 