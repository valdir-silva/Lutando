package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.repository.CommentRepository
import com.alunando.lutando.domain.model.Comment
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.mockito.kotlin.verify
import kotlin.test.assertEquals

class AddCommentUseCaseTest {
    
    private lateinit var commentRepository: CommentRepository
    private lateinit var addCommentUseCase: AddCommentUseCase
    
    @Before
    fun setup() {
        commentRepository = mock()
        addCommentUseCase = AddCommentUseCase(commentRepository)
    }
    
    @Test
    fun `invoke should add comment and return comment id`() = runTest {
        // Given
        val techniqueId = 1L
        val author = "João"
        val text = "Ótima técnica!"
        val expectedCommentId = 123L
        
        whenever(commentRepository.addComment(any())).thenReturn(expectedCommentId)
        
        // When
        val result = addCommentUseCase(techniqueId, author, text)
        
        // Then
        assertEquals(expectedCommentId, result)
        verify(commentRepository).addComment(
            Comment(
                techniqueId = techniqueId,
                author = author,
                text = text
            )
        )
    }
} 