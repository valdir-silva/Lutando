package com.example.lutando.presentation.screens.technique_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lutando.domain.model.Technique
import com.example.lutando.domain.repository.TechniqueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TechniqueDetailUiState(
    val technique: Technique? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class TechniqueDetailViewModel(
    private val techniqueRepository: TechniqueRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TechniqueDetailUiState())
    val uiState: StateFlow<TechniqueDetailUiState> = _uiState.asStateFlow()

    fun loadTechnique(techniqueId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            try {
                val technique = techniqueRepository.getTechniqueById(techniqueId)
                _uiState.update { 
                    it.copy(
                        technique = technique,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        error = e.message ?: "Erro ao carregar técnica",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun deleteTechnique(techniqueId: Long) {
        viewModelScope.launch {
            try {
                techniqueRepository.deleteTechniqueById(techniqueId)
                // Técnica deletada com sucesso
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        error = e.message ?: "Erro ao deletar técnica"
                    )
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
} 