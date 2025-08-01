package com.example.lutando.domain.usecase

import com.example.lutando.domain.model.Comment
import com.example.lutando.domain.repository.CommentRepository

/**
 * Use case para adicionar um novo comentário a uma técnica.
 */
class AddCommentUseCase(
    private val commentRepository: CommentRepository
) {
    
    /**
     * Executa o use case para adicionar um comentário.
     *
     * @param techniqueId ID da técnica
     * @param author Nome do autor do comentário
     * @param text Texto do comentário
     * @return ID do comentário criado
     */
    suspend operator fun invoke(
        techniqueId: Long,
        author: String,
        text: String
    ): Long {
        val comment = Comment(
            techniqueId = techniqueId,
            author = author,
            text = text
        )
        return commentRepository.addComment(comment)
    }
} 