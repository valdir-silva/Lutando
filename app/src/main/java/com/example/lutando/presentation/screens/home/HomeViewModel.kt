package com.example.lutando.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lutando.domain.model.MartialArt
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel da tela principal.
 */
class HomeViewModel() : ViewModel() {
    
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    
    init {
        loadMartialArts()
    }
    
    private fun loadMartialArts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            // TODO: Implementar quando adicionar Room Database
            // Por enquanto, vamos simular dados
            kotlinx.coroutines.delay(1000) // Simular delay de rede
            
            val mockMartialArts = listOf(
                MartialArt(
                    id = 1,
                    name = "Jiu-Jitsu",
                    description = "Arte marcial brasileira focada em luta no chão",
                    color = "#FF5722"
                ),
                MartialArt(
                    id = 2,
                    name = "Muay Thai",
                    description = "Arte marcial tailandesa conhecida como boxe tailandês",
                    color = "#FF9800"
                ),
                MartialArt(
                    id = 3,
                    name = "Boxe",
                    description = "Esporte de combate usando apenas os punhos",
                    color = "#2196F3"
                )
            )
            
            _uiState.update { 
                it.copy(
                    isLoading = false,
                    martialArts = mockMartialArts,
                    error = null
                )
            }
        }
    }
    
    fun refresh() {
        loadMartialArts()
    }
    
    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

/**
 * Estado da UI da tela principal.
 */
data class HomeUiState(
    val isLoading: Boolean = false,
    val martialArts: List<MartialArt> = emptyList(),
    val error: String? = null
) 