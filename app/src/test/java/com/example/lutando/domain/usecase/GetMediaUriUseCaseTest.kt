package com.example.lutando.domain.usecase

import android.net.Uri
import com.example.lutando.domain.repository.MediaRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetMediaUriUseCaseTest {

    private lateinit var useCase: GetMediaUriUseCase
    private lateinit var mockRepository: MediaRepository

    @Before
    fun setup() {
        mockRepository = mockk(relaxed = true)
        useCase = GetMediaUriUseCase(mockRepository)
        // Mock Uri.parse() para todos os testes
        mockkStatic(Uri::class)
    }

    @Test
    fun `deve obter URI de arquivo de vídeo com sucesso`() = runTest {
        // Given
        val filePath = "/app/videos/technique_123.mp4"
        val mockUri = mockk<Uri>()
        every { Uri.parse("content://media/video.mp4") } returns mockUri
        
        coEvery { mockRepository.getMediaUri(filePath) } returns Result.success(mockUri)
        
        // When
        val result = useCase(filePath)
        
        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockUri, result.getOrNull())
    }

    @Test
    fun `deve obter URI de arquivo de foto com sucesso`() = runTest {
        // Given
        val filePath = "/app/photos/technique_456.jpg"
        val mockUri = mockk<Uri>()
        every { Uri.parse("content://media/photo.jpg") } returns mockUri
        
        coEvery { mockRepository.getMediaUri(filePath) } returns Result.success(mockUri)
        
        // When
        val result = useCase(filePath)
        
        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockUri, result.getOrNull())
    }

    @Test
    fun `deve obter URI de arquivo de áudio com sucesso`() = runTest {
        // Given
        val filePath = "/app/audio/technique_789.mp3"
        val mockUri = mockk<Uri>()
        every { Uri.parse("content://media/audio.mp3") } returns mockUri
        
        coEvery { mockRepository.getMediaUri(filePath) } returns Result.success(mockUri)
        
        // When
        val result = useCase(filePath)
        
        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockUri, result.getOrNull())
    }

    @Test
    fun `deve retornar erro quando arquivo não existe`() = runTest {
        // Given
        val filePath = "/app/videos/inexistente.mp4"
        val errorMessage = "Arquivo não encontrado"
        
        coEvery { mockRepository.getMediaUri(filePath) } returns Result.failure(Exception(errorMessage))
        
        // When
        val result = useCase(filePath)
        
        // Then
        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }

    @Test
    fun `deve chamar repositório com caminho correto`() = runTest {
        // Given
        val filePath = "/app/videos/test.mp4"
        val mockUri = mockk<Uri>()
        every { Uri.parse("content://media/test.mp4") } returns mockUri
        
        coEvery { mockRepository.getMediaUri(filePath) } returns Result.success(mockUri)
        
        // When
        val result = useCase(filePath)
        
        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockUri, result.getOrNull())
    }

    @Test
    fun `deve retornar erro quando caminho está vazio`() = runTest {
        // Given
        val filePath = ""
        val errorMessage = "Caminho do arquivo não pode estar vazio"
        
        coEvery { mockRepository.getMediaUri(filePath) } returns Result.failure(Exception(errorMessage))
        
        // When
        val result = useCase(filePath)
        
        // Then
        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }
} 