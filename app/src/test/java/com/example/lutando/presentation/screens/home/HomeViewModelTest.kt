package com.example.lutando.presentation.screens.home

import app.cash.turbine.test
import com.example.lutando.domain.model.MartialArt
import com.example.lutando.domain.usecase.GetAllMartialArtsUseCase
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
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var mockGetAllMartialArtsUseCase: GetAllMartialArtsUseCase
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockGetAllMartialArtsUseCase = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `deve carregar artes marciais com sucesso`() = runTest(testDispatcher) {
        // Given
        val martialArts = listOf(
            MartialArt(id = 1L, name = "Jiu-Jitsu"),
            MartialArt(id = 2L, name = "Muay Thai"),
            MartialArt(id = 3L, name = "Boxe")
        )
        
        coEvery { mockGetAllMartialArtsUseCase() } returns flowOf(martialArts)
        
        // When
        viewModel = HomeViewModel(mockGetAllMartialArtsUseCase)
        testDispatcher.scheduler.advanceUntilIdle()
        
        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertEquals(3, state.martialArts.size)
            assertEquals("Jiu-Jitsu", state.martialArts[0].name)
            assertEquals("Muay Thai", state.martialArts[1].name)
            assertEquals("Boxe", state.martialArts[2].name)
            assertNull(state.error)
        }
    }

    @Test
    fun `deve mostrar estado de carregamento inicialmente`() = runTest(testDispatcher) {
        // Given
        coEvery { mockGetAllMartialArtsUseCase() } returns flowOf(emptyList())
        
        // When & Then
        viewModel = HomeViewModel(mockGetAllMartialArtsUseCase)
        testDispatcher.scheduler.advanceUntilIdle()
        viewModel.uiState.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertTrue(state.martialArts.isEmpty())
            assertNull(state.error)
        }
    }

    @Test
    fun `deve mostrar lista vazia quando não há artes marciais`() = runTest(testDispatcher) {
        // Given
        coEvery { mockGetAllMartialArtsUseCase() } returns flowOf(emptyList())
        
        // When
        viewModel = HomeViewModel(mockGetAllMartialArtsUseCase)
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertTrue(state.martialArts.isEmpty())
            assertNull(state.error)
        }
    }

    @Test
    fun `deve mostrar erro quando use case falha`() = runTest(testDispatcher) {
        // Given
        val errorMessage = "Erro ao carregar dados"
        coEvery { mockGetAllMartialArtsUseCase() } throws Exception(errorMessage)
        
        // When
        viewModel = HomeViewModel(mockGetAllMartialArtsUseCase)
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        viewModel.uiState.test {
            val state = awaitItem()
            assertFalse(state.isLoading)
            assertTrue(state.martialArts.isEmpty())
            assertEquals(errorMessage, state.error)
        }
    }

    @Test
    fun `deve recarregar dados quando refresh é chamado`() = runTest(testDispatcher) {
        // Given
        val initialArts = listOf(MartialArt(id = 1L, name = "Jiu-Jitsu"))
        val refreshedArts = listOf(
            MartialArt(id = 1L, name = "Jiu-Jitsu"),
            MartialArt(id = 2L, name = "Muay Thai")
        )
        
        var callCount = 0
        coEvery { mockGetAllMartialArtsUseCase() } answers {
            callCount++
            if (callCount == 1) flowOf(initialArts) else flowOf(refreshedArts)
        }
        
        // When
        viewModel = HomeViewModel(mockGetAllMartialArtsUseCase)
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        viewModel.uiState.test {
            // Estado inicial após o primeiro carregamento
            val firstState = awaitItem()
            assertFalse(firstState.isLoading)
            assertEquals(1, firstState.martialArts.size)
            assertEquals("Jiu-Jitsu", firstState.martialArts[0].name)
            // Recarregar
            viewModel.refresh()
            // O Flow pode emitir um estado de loading antes do resultado final
            val loadingState = awaitItem()
            assertTrue(loadingState.isLoading)
            testDispatcher.scheduler.advanceUntilIdle()
            val refreshState = awaitItem()
            assertFalse(refreshState.isLoading)
            assertEquals(2, refreshState.martialArts.size)
            assertEquals("Jiu-Jitsu", refreshState.martialArts[0].name)
            assertEquals("Muay Thai", refreshState.martialArts[1].name)
            assertNull(refreshState.error)
        }
    }

    @Test
    fun `deve limpar erro quando clearError é chamado`() = runTest(testDispatcher) {
        // Given
        val errorMessage = "Erro ao carregar dados"
        coEvery { mockGetAllMartialArtsUseCase() } throws Exception(errorMessage)
        
        // When
        viewModel = HomeViewModel(mockGetAllMartialArtsUseCase)
        testDispatcher.scheduler.advanceUntilIdle()
        // Then
        viewModel.uiState.test {
            val errorState = awaitItem()
            assertFalse(errorState.isLoading)
            assertEquals(errorMessage, errorState.error)
            // Limpar erro
            viewModel.clearError()
            val clearedState = awaitItem()
            assertNull(clearedState.error)
        }
    }
} 