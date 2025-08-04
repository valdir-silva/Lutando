package com.alunando.lutando.presentation.screens.martial_art_detail

import com.alunando.lutando.domain.repository.MartialArtRepository

import app.cash.turbine.test
import com.alunando.lutando.domain.model.MartialArt
import com.alunando.lutando.domain.model.Technique
import com.alunando.lutando.domain.usecase.GetAllMartialArtsUseCase
import com.alunando.lutando.domain.usecase.GetTechniquesByMartialArtUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MartialArtDetailViewModelTest {

    private lateinit var viewModel: MartialArtDetailViewModel
    private lateinit var mockGetTechniquesByMartialArtUseCase: GetTechniquesByMartialArtUseCase
    private lateinit var mockMartialArtRepository: MartialArtRepository
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockGetTechniquesByMartialArtUseCase = mockk(relaxed = true)
        mockMartialArtRepository = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `deve carregar modalidade e técnicas com sucesso`() = runTest(testDispatcher) {
        // Given
        val martialArtId = "1"
        val martialArt = MartialArt(id = martialArtId, name = "Jiu-Jitsu")
        val martialArts = listOf(martialArt)
        val techniques = listOf(
            Technique(id = "1", name = "Kimura", martialArtId = martialArtId),
            Technique(id = "2", name = "Armbar", martialArtId = martialArtId)
        )

        coEvery { mockMartialArtRepository.getMartialArtById(martialArtId) } returns martialArt
        coEvery { mockGetTechniquesByMartialArtUseCase(martialArtId) } returns flowOf(techniques)

        // When
        viewModel = MartialArtDetailViewModel(
            mockGetTechniquesByMartialArtUseCase,
            mockMartialArtRepository
        )
        viewModel.loadMartialArt(martialArtId)
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertEquals(martialArt, state.martialArt)
            assertEquals(2, state.techniques.size)
            assertEquals("Kimura", state.techniques[0].name)
            assertEquals("Armbar", state.techniques[1].name)
            assertNull(state.error)
        }
    }

    @Test
    fun `deve mostrar erro quando modalidade não é encontrada`() = runTest(testDispatcher) {
        // Given
        val martialArtId = "999"
        coEvery { mockMartialArtRepository.getMartialArtById(martialArtId) } returns null

        // When
        viewModel = MartialArtDetailViewModel(
            mockGetTechniquesByMartialArtUseCase,
            mockMartialArtRepository
        )
        viewModel.loadMartialArt(martialArtId)
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertNull(state.martialArt)
            assertTrue(state.techniques.isEmpty())
            assertEquals("Modalidade não encontrada", state.error)
        }
    }

    @Test
    fun `deve carregar detalhes da modalidade com sucesso`() = runTest(testDispatcher) {
        // Given
        val martialArtId = "2"
        val techniques = listOf(
            Technique(id = "1", name = "Triangle", martialArtId = martialArtId),
            Technique(id = "2", name = "Omoplata", martialArtId = martialArtId)
        )

        coEvery { mockGetTechniquesByMartialArtUseCase(martialArtId) } returns flowOf(techniques)

        // When
        viewModel = MartialArtDetailViewModel(
            mockGetTechniquesByMartialArtUseCase,
            mockMartialArtRepository
        )
        viewModel.loadMartialArtDetail(martialArtId)
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertEquals(2, state.techniques.size)
            assertEquals("Triangle", state.techniques[0].name)
            assertEquals("Omoplata", state.techniques[1].name)
            assertNull(state.error)
        }
    }

    @Test
    fun `deve mostrar erro quando carregamento de técnicas falha`() = runTest(testDispatcher) {
        // Given
        val martialArtId = "3"
        val errorMessage = "Erro ao carregar técnicas"

        coEvery { mockGetTechniquesByMartialArtUseCase(martialArtId) } throws Exception(errorMessage)

        // When
        viewModel = MartialArtDetailViewModel(
            mockGetTechniquesByMartialArtUseCase,
            mockMartialArtRepository
        )
        viewModel.loadMartialArtDetail(martialArtId)
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertTrue(state.techniques.isEmpty())
            assertEquals(errorMessage, state.error)
        }
    }

    @Test
    fun `deve definir modalidade e carregar técnicas`() = runTest(testDispatcher) {
        // Given
        val martialArt = MartialArt(id = "4", name = "Muay Thai")
        val techniques = listOf(
            Technique(id = "1", name = "Jab", martialArtId = martialArt.id),
            Technique(id = "2", name = "Cross", martialArtId = martialArt.id)
        )

        coEvery { mockGetTechniquesByMartialArtUseCase(martialArt.id) } returns flowOf(techniques)

        // When
        viewModel = MartialArtDetailViewModel(
            mockGetTechniquesByMartialArtUseCase,
            mockMartialArtRepository
        )
        viewModel.setMartialArt(martialArt)
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertEquals(2, state.techniques.size)
            assertEquals("Jab", state.techniques[0].name)
            assertEquals("Cross", state.techniques[1].name)
            assertNull(state.error)
        }
    }

    @Test
    fun `deve recarregar técnicas quando refresh é chamado`() = runTest(testDispatcher) {
        // Given
        val martialArt = MartialArt(id = "5", name = "Boxe")
        val initialTechniques =
            listOf(Technique(id = "1", name = "Jab", martialArtId = martialArt.id))
        val refreshedTechniques = listOf(
            Technique(id = "1", name = "Jab", martialArtId = martialArt.id),
            Technique(id = "2", name = "Hook", martialArtId = martialArt.id)
        )

        var callCount = 0
        coEvery { mockGetTechniquesByMartialArtUseCase(martialArt.id) } answers {
            callCount++
            if (callCount == 1) flowOf(initialTechniques) else flowOf(refreshedTechniques)
        }

        // When
        viewModel = MartialArtDetailViewModel(
            mockGetTechniquesByMartialArtUseCase,
            mockMartialArtRepository
        )
        viewModel.setMartialArt(martialArt)
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        viewModel.uiState.test {
            val firstState = awaitItem()
            assertFalse(firstState.isLoading)
            assertEquals(1, firstState.techniques.size)
            assertEquals("Jab", firstState.techniques[0].name)
            // Recarregar
            viewModel.refreshTechniques()
            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)
            testDispatcher.scheduler.advanceUntilIdle()
            val refreshState = awaitItem()
            assertFalse(refreshState.isLoading)
            assertEquals(2, refreshState.techniques.size)
            assertEquals("Jab", refreshState.techniques[0].name)
            assertEquals("Hook", refreshState.techniques[1].name)
            assertNull(refreshState.error)
        }
    }

    @Test
    fun `deve mostrar lista vazia quando não há técnicas`() = runTest(testDispatcher) {
        // Given
        val martialArtId = "6"
        val martialArt = MartialArt(id = martialArtId, name = "Karatê")
        val martialArts = listOf(martialArt)

        coEvery { mockMartialArtRepository.getMartialArtById(martialArtId) } returns martialArt
        coEvery { mockGetTechniquesByMartialArtUseCase(martialArtId) } returns flowOf(emptyList())

        // When
        viewModel = MartialArtDetailViewModel(
            mockGetTechniquesByMartialArtUseCase,
            mockMartialArtRepository
        )
        viewModel.loadMartialArt(martialArtId)
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertEquals(martialArt, state.martialArt)
            assertTrue(state.techniques.isEmpty())
            assertNull(state.error)
        }
    }
} 