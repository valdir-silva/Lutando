package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.repository.AuthRepository

import com.google.firebase.auth.FirebaseUser
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class GetCurrentUserUseCaseTest {

    private lateinit var useCase: GetCurrentUserUseCase
    private lateinit var mockRepository: AuthRepository

    @Before
    fun setup() {
        mockRepository = mockk()
        useCase = GetCurrentUserUseCase(mockRepository)
    }

    @Test
    fun `deve retornar usuário atual`() = runTest {
        // Given
        val mockFirebaseUser = mockk<FirebaseUser>()
        every { mockFirebaseUser.uid } returns "1"
        every { mockFirebaseUser.displayName } returns "João Silva"
        every { mockFirebaseUser.email } returns "joao@email.com"

        every { mockRepository.getCurrentUser() } returns mockFirebaseUser

        // When
        val result = useCase()

        // Then
        assertEquals("1", result?.uid)
        assertEquals("João Silva", result?.displayName)
        assertEquals("joao@email.com", result?.email)
    }

    @Test
    fun `deve retornar usuário sem email`() = runTest {
        // Given
        val mockFirebaseUser = mockk<FirebaseUser>()
        every { mockFirebaseUser.uid } returns "2"
        every { mockFirebaseUser.displayName } returns "Maria Santos"
        every { mockFirebaseUser.email } returns null

        every { mockRepository.getCurrentUser() } returns mockFirebaseUser

        // When
        val result = useCase()

        // Then
        assertEquals("2", result?.uid)
        assertEquals("Maria Santos", result?.displayName)
        assertNull(result?.email)
    }

    @Test
    fun `deve retornar usuário com dados mínimos`() = runTest {
        // Given
        val mockFirebaseUser = mockk<FirebaseUser>()
        every { mockFirebaseUser.uid } returns ""
        every { mockFirebaseUser.displayName } returns "Pedro Costa"
        every { mockFirebaseUser.email } returns null

        every { mockRepository.getCurrentUser() } returns mockFirebaseUser

        // When
        val result = useCase()

        // Then
        assertEquals("", result?.uid)
        assertEquals("Pedro Costa", result?.displayName)
        assertNull(result?.email)
    }
} 