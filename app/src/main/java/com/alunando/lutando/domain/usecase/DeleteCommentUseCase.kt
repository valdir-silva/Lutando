package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.Comment
import com.alunando.lutando.domain.repository.CommentRepository

/**
 * Use case para deletar um comentário.
 */
class DeleteCommentUseCase(
    private val commentRepository: CommentRepository
) {
    
    /**
     * Executa o use case para deletar um comentário.
     *
     * @param comment Comentário a ser deletado
     */
    suspend operator fun invoke(comment: Comment) {
        commentRepository.deleteComment(comment)
    }
} 