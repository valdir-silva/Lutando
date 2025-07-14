package com.example.lutando.domain.usecase

import com.example.lutando.domain.model.MartialArt
import com.example.lutando.domain.repository.MartialArtRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetAllMartialArtsUseCaseTest {

    private lateinit var useCase: GetAllMartialArtsUseCase
    private lateinit var mockRepository: MartialArtRepository

    @Before
    fun setup() {
        mockRepository = mockk()
        useCase = GetAllMartialArtsUseCase(mockRepository)
    }

    @Test
    fun `deve retornar lista de artes marciais do repositório`() = runTest {
        // Given
        val martialArts = listOf(
            MartialArt(id = 1L, name = "Jiu-Jitsu"),
            MartialArt(id = 2L, name = "Muay Thai"),
            MartialArt(id = 3L, name = "Boxe")
        )

        coEvery { mockRepository.getAllMartialArts() } returns flowOf(martialArts)

        // When
        val result = useCase()

        // Then
        result.collect { arts ->
            assertEquals(3, arts.size)
            assertEquals("Jiu-Jitsu", arts[0].name)
            assertEquals("Muay Thai", arts[1].name)
            assertEquals("Boxe", arts[2].name)
        }
    }

    @Test
    fun `deve retornar lista vazia quando repositório não tem dados`() = runTest {
        // Given
        coEvery { mockRepository.getAllMartialArts() } returns flowOf(emptyList())

        // When
        val result = useCase()

        // Then
        result.collect { arts ->
            assertEquals(0, arts.size)
        }
    }

    @Test
    fun `deve retornar lista com uma arte marcial`() = runTest {
        // Given
        val martialArt = MartialArt(id = 1L, name = "Karatê", description = "Arte marcial japonesa")
        coEvery { mockRepository.getAllMartialArts() } returns flowOf(listOf(martialArt))

        // When
        val result = useCase()

        // Then
        result.collect { arts ->
            assertEquals(1, arts.size)
            assertEquals("Karatê", arts[0].name)
            assertEquals("Arte marcial japonesa", arts[0].description)
        }
    }
} 