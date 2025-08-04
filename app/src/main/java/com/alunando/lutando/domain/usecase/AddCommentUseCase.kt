package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.Comment
import com.alunando.lutando.domain.repository.CommentRepository

/**
 * Use case para adicionar um novo comentário a uma técnica.
 */
class AddCommentUseCase(
    private val commentRepository: CommentRepository
) {

    suspend operator fun invoke(
        techniqueId: String,
        authorId: String,
        authorName: String,
        text: String
    ): String {
        val comment = Comment(
            techniqueId = techniqueId,
            authorId = authorId,
            authorName = authorName,
            text = text
        )
        return commentRepository.addComment(comment)
    }
} 