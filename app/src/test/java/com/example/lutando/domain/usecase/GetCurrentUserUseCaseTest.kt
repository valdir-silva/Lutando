package com.example.lutando.domain.usecase

import com.example.lutando.domain.model.User
import com.example.lutando.domain.repository.UserRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class GetCurrentUserUseCaseTest {

    private lateinit var useCase: GetCurrentUserUseCase
    private lateinit var mockRepository: UserRepository

    @Before
    fun setup() {
        mockRepository = mockk()
        useCase = GetCurrentUserUseCase(mockRepository)
    }

    @Test
    fun `deve retornar usuário atual`() = runTest {
        // Given
        val user = User(
            id = 1L,
            name = "João Silva",
            email = "joao@email.com"
        )
        
        coEvery { mockRepository.getCurrentUser() } returns flowOf(user)
        
        // When
        val result = useCase()
        
        // Then
        result.collect { currentUser ->
            assertEquals(1L, currentUser?.id)
            assertEquals("João Silva", currentUser?.name)
            assertEquals("joao@email.com", currentUser?.email)
        }
    }

    @Test
    fun `deve retornar usuário sem email`() = runTest {
        // Given
        val user = User(
            id = 2L,
            name = "Maria Santos",
            email = null
        )
        
        coEvery { mockRepository.getCurrentUser() } returns flowOf(user)
        
        // When
        val result = useCase()
        
        // Then
        result.collect { currentUser ->
            assertEquals(2L, currentUser?.id)
            assertEquals("Maria Santos", currentUser?.name)
            assertNull(currentUser?.email)
        }
    }

    @Test
    fun `deve retornar usuário com dados mínimos`() = runTest {
        // Given
        val user = User(name = "Pedro Costa")
        
        coEvery { mockRepository.getCurrentUser() } returns flowOf(user)
        
        // When
        val result = useCase()
        
        // Then
        result.collect { currentUser ->
            assertEquals(0L, currentUser?.id)
            assertEquals("Pedro Costa", currentUser?.name)
            assertNull(currentUser?.email)
        }
    }
} 