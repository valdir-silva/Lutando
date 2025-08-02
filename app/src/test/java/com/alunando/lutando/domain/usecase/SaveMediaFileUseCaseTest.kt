package com.alunando.lutando.domain.usecase

import android.net.Uri
import com.alunando.lutando.domain.model.MediaType
import com.alunando.lutando.domain.repository.MediaRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SaveMediaFileUseCaseTest {

    private lateinit var useCase: SaveMediaFileUseCase
    private lateinit var mockRepository: MediaRepository

    @Before
    fun setup() {
        mockRepository = mockk(relaxed = true)
        useCase = SaveMediaFileUseCase(mockRepository)

        // Mock Uri.parse() para todos os testes
        mockkStatic(Uri::class)
    }

    @Test
    fun `deve salvar arquivo de vídeo com sucesso`() = runTest {
        // Given
        val mockUri = mockk<Uri>()
        every { Uri.parse("content://media/video.mp4") } returns mockUri
        val mediaType = MediaType.VIDEO
        val expectedPath = "/app/videos/technique_123.mp4"

        coEvery { mockRepository.saveMediaFile(mockUri, mediaType) } returns Result.success(
            expectedPath
        )

        // When
        val result = useCase(mockUri, mediaType)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedPath, result.getOrNull())
    }

    @Test
    fun `deve salvar arquivo de foto com sucesso`() = runTest {
        // Given
        val mockUri = mockk<Uri>()
        every { Uri.parse("content://media/photo.jpg") } returns mockUri
        val mediaType = MediaType.PHOTO
        val expectedPath = "/app/photos/technique_456.jpg"

        coEvery { mockRepository.saveMediaFile(mockUri, mediaType) } returns Result.success(
            expectedPath
        )

        // When
        val result = useCase(mockUri, mediaType)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedPath, result.getOrNull())
    }

    @Test
    fun `deve salvar arquivo de áudio com sucesso`() = runTest {
        // Given
        val mockUri = mockk<Uri>()
        every { Uri.parse("content://media/audio.mp3") } returns mockUri
        val mediaType = MediaType.AUDIO
        val expectedPath = "/app/audio/technique_789.mp3"

        coEvery { mockRepository.saveMediaFile(mockUri, mediaType) } returns Result.success(
            expectedPath
        )

        // When
        val result = useCase(mockUri, mediaType)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedPath, result.getOrNull())
    }

    @Test
    fun `deve retornar erro quando repositório falha`() = runTest {
        // Given
        val mockUri = mockk<Uri>()
        every { Uri.parse("content://media/video.mp4") } returns mockUri
        val mediaType = MediaType.VIDEO
        val errorMessage = "Erro ao salvar arquivo"

        coEvery { mockRepository.saveMediaFile(mockUri, mediaType) } returns Result.failure(
            Exception(errorMessage)
        )

        // When
        val result = useCase(mockUri, mediaType)

        // Then
        assertTrue(result.isFailure)
        assertEquals(errorMessage, result.exceptionOrNull()?.message)
    }

    @Test
    fun `deve chamar repositório com parâmetros corretos`() = runTest {
        // Given
        val mockUri = mockk<Uri>()
        every { Uri.parse("content://media/test.mp4") } returns mockUri
        val mediaType = MediaType.VIDEO
        val expectedPath = "/app/videos/test.mp4"

        coEvery { mockRepository.saveMediaFile(mockUri, mediaType) } returns Result.success(
            expectedPath
        )

        // When
        val result = useCase(mockUri, mediaType)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(expectedPath, result.getOrNull())
    }
} 