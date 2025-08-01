package com.example.lutando.domain.usecase

import com.example.lutando.domain.model.Comment
import com.example.lutando.domain.repository.CommentRepository

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