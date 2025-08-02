package com.alunando.lutando.domain.usecase

import com.alunando.lutando.domain.model.Technique
import com.alunando.lutando.domain.repository.TechniqueRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class GetTechniquesByMartialArtUseCaseTest {

    private lateinit var useCase: GetTechniquesByMartialArtUseCase
    private lateinit var mockRepository: TechniqueRepository

    @Before
    fun setup() {
        mockRepository = mockk()
        useCase = GetTechniquesByMartialArtUseCase(mockRepository)
    }

    @Test
    fun `deve retornar técnicas de uma arte marcial específica`() = runTest {
        // Given
        val martialArtId = 1L
        val techniques = listOf(
            Technique(id = 1L, name = "Kimura", martialArtId = martialArtId),
            Technique(id = 2L, name = "Armbar", martialArtId = martialArtId),
            Technique(id = 3L, name = "Triangle", martialArtId = martialArtId)
        )

        coEvery { mockRepository.getTechniquesByMartialArt(martialArtId) } returns flowOf(techniques)

        // When
        val result = useCase(martialArtId)

        // Then
        result.collect { techs ->
            assertEquals(3, techs.size)
            assertEquals("Kimura", techs[0].name)
            assertEquals("Armbar", techs[1].name)
            assertEquals("Triangle", techs[2].name)
            techs.forEach { technique ->
                assertEquals(martialArtId, technique.martialArtId)
            }
        }
    }

    @Test
    fun `deve retornar lista vazia quando não há técnicas para a arte marcial`() = runTest {
        // Given
        val martialArtId = 999L
        coEvery { mockRepository.getTechniquesByMartialArt(martialArtId) } returns flowOf(emptyList())

        // When
        val result = useCase(martialArtId)

        // Then
        result.collect { techniques ->
            assertEquals(0, techniques.size)
        }
    }

    @Test
    fun `deve retornar técnica com mídia`() = runTest {
        // Given
        val martialArtId = 2L
        val technique = Technique(
            id = 1L,
            name = "Omoplata",
            description = "Chave de ombro",
            martialArtId = martialArtId,
            hasVideo = true,
            hasPhoto = true,
            videoPath = "/videos/omoplata.mp4",
            photoPath = "/photos/omoplata.jpg"
        )

        coEvery { mockRepository.getTechniquesByMartialArt(martialArtId) } returns flowOf(
            listOf(
                technique
            )
        )

        // When
        val result = useCase(martialArtId)

        // Then
        result.collect { techniques ->
            assertEquals(1, techniques.size)
            val tech = techniques[0]
            assertEquals("Omoplata", tech.name)
            assertEquals("Chave de ombro", tech.description)
            assertTrue(tech.hasVideo)
            assertTrue(tech.hasPhoto)
            assertEquals("/videos/omoplata.mp4", tech.videoPath)
            assertEquals("/photos/omoplata.jpg", tech.photoPath)
        }
    }

    @Test
    fun `deve chamar repositório com ID correto`() = runTest {
        // Given
        val martialArtId = 5L
        coEvery { mockRepository.getTechniquesByMartialArt(martialArtId) } returns flowOf(emptyList())

        // When
        useCase(martialArtId)

        // Then
        coEvery { mockRepository.getTechniquesByMartialArt(martialArtId) }
    }
} 