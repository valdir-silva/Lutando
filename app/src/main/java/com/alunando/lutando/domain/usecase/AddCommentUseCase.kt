package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.Comment
import com.alunando.lutando.domain.repository.CommentRepository

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
        techniqueId: String,
        author: String,
        text: String
    ): String {
        val comment = Comment(
            techniqueId = techniqueId,
            author = author,
            text = text
        )
        return commentRepository.addComment(comment)
    }
} 