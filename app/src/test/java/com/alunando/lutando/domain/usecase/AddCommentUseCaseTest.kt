package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.repository.CommentRepository
import com.alunando.lutando.domain.model.Comment
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.coVerify
import org.junit.Assert.assertEquals

class AddCommentUseCaseTest {
    
    private lateinit var commentRepository: CommentRepository
    private lateinit var addCommentUseCase: AddCommentUseCase
    
    @Before
    fun setup() {
        commentRepository = mockk()
        addCommentUseCase = AddCommentUseCase(commentRepository)
    }
    
    @Test
    fun `invoke should add comment and return comment id`() = runTest {
        // Given
        val techniqueId = "tech123"
        val author = "João"
        val text = "Ótima técnica!"
        val expectedCommentId = "comment456"
        
        coEvery { commentRepository.addComment(any()) } returns expectedCommentId
        
        // When
        val result = addCommentUseCase(techniqueId, author, text)
        
        // Then
        assertEquals(expectedCommentId, result)
        coVerify { commentRepository.addComment(
            Comment(
                techniqueId = techniqueId,
                author = author,
                text = text
            )
        ) }
    }
} 