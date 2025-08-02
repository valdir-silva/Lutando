package com.alunando.lutando.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alunando.lutando.domain.model.MartialArt
import com.alunando.lutando.domain.usecase.GetAllMartialArtsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel da tela principal.
 */
class HomeViewModel(
    private val getAllMartialArtsUseCase: GetAllMartialArtsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadMartialArts()
    }

    private fun loadMartialArts() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            try {
                getAllMartialArtsUseCase().collect { martialArts ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            martialArts = martialArts,
                            error = null
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Erro ao carregar modalidades"
                    )
                }
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