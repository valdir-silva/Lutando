package com.example.lutando.domain.usecase

import com.example.lutando.domain.repository.MediaRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class DeleteMediaFileUseCaseTest {

    private lateinit var useCase: DeleteMediaFileUseCase
    private lateinit var mockRepository: MediaRepository

    @Before
    fun setup() {
        mockRepository = mockk()
        useCase = DeleteMediaFileUseCase(mockRepository)
    }

    @Test
    fun `deve deletar arquivo de mídia com sucesso`() = runTest {
        // Given
        val filePath = "/app/videos/technique_123.mp4"

        coEvery { mockRepository.deleteMediaFile(filePath) } returns Result.success(true)

        // When
        val result = useCase(filePath)

        // Then
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()!!)
    }

    @Test
    fun `deve retornar erro quando arquivo não existe`() = runTest {
        // Given
        val filePath = "/app/videos/inexistente.mp4"
        val errorMessage = "Arquivo não encontrado"

        coEvery { mockRepository.deleteMediaFile(filePath) } returns Result.failure(
            Exception(
                errorMessage
            )
        )

        // When
        val result = useCase(filePath)

        // Then
        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }

    @Test
    fun `deve deletar arquivo de foto`() = runTest {
        // Given
        val filePath = "/app/photos/technique_456.jpg"

        coEvery { mockRepository.deleteMediaFile(filePath) } returns Result.success(true)

        // When
        val result = useCase(filePath)

        // Then
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()!!)
    }

    @Test
    fun `deve deletar arquivo de áudio`() = runTest {
        // Given
        val filePath = "/app/audio/technique_789.mp3"

        coEvery { mockRepository.deleteMediaFile(filePath) } returns Result.success(true)

        // When
        val result = useCase(filePath)

        // Then
        assertTrue(result.isSuccess)
        assertTrue(result.getOrNull()!!)
    }

    @Test
    fun `deve chamar repositório com caminho correto`() = runTest {
        // Given
        val filePath = "/app/videos/test.mp4"

        coEvery { mockRepository.deleteMediaFile(filePath) } returns Result.success(true)

        // When
        useCase(filePath)

        // Then
        coEvery { mockRepository.deleteMediaFile(filePath) }
    }
} 