package com.example.lutando.presentation.screens.martial_art_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lutando.domain.model.MartialArt
import com.example.lutando.domain.model.Technique
import com.example.lutando.domain.usecase.GetAllMartialArtsUseCase
import com.example.lutando.domain.usecase.GetTechniquesByMartialArtUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class MartialArtDetailUiState(
    val martialArt: MartialArt? = null,
    val techniques: List<Technique> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class MartialArtDetailViewModel(
    private val getTechniquesByMartialArtUseCase: GetTechniquesByMartialArtUseCase,
    private val getAllMartialArtsUseCase: GetAllMartialArtsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MartialArtDetailUiState())
    val uiState: StateFlow<MartialArtDetailUiState> = _uiState.asStateFlow()

    fun loadMartialArt(martialArtId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                // Carregar a modalidade
                getAllMartialArtsUseCase().collect { martialArts ->
                    val martialArt = martialArts.find { it.id == martialArtId }

                    if (martialArt != null) {
                        _uiState.update { it.copy(martialArt = martialArt) }

                        // Carregar as técnicas
                        getTechniquesByMartialArtUseCase(martialArtId).collect { techniques ->
                            _uiState.update {
                                it.copy(
                                    techniques = techniques,
                                    isLoading = false
                                )
                            }
                        }
                    } else {
                        _uiState.update {
                            it.copy(
                                error = "Modalidade não encontrada",
                                isLoading = false
                            )
                        }
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.message ?: "Erro ao carregar dados",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun loadMartialArtDetail(martialArtId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                getTechniquesByMartialArtUseCase(martialArtId).collect { techniques ->
                    _uiState.update {
                        it.copy(
                            techniques = techniques,
                            isLoading = false
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.message ?: "Erro ao carregar técnicas",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun setMartialArt(martialArt: MartialArt) {
        _uiState.update { it.copy(martialArt = martialArt) }
        loadMartialArtDetail(martialArt.id)
    }

    fun refreshTechniques() {
        _uiState.value.martialArt?.let { martialArt ->
            loadMartialArtDetail(martialArt.id)
        }
    }
} 