package com.alunando.lutando.presentation.screens.martial_art_form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alunando.lutando.domain.model.MartialArt
import com.alunando.lutando.domain.usecase.AddMartialArtUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * ViewModel para a tela de formulário de modalidades de artes marciais.
 */
class MartialArtFormViewModel(
    private val addMartialArtUseCase: AddMartialArtUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(MartialArtFormUiState())
    val uiState: StateFlow<MartialArtFormUiState> = _uiState.asStateFlow()

    fun setName(name: String) {
        _uiState.update { it.copy(name = name, error = null) }
    }

    fun setDescription(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun saveMartialArt() {
        val currentState = _uiState.value

        if (currentState.name.isBlank()) {
            _uiState.update { it.copy(error = "Nome da modalidade é obrigatório") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val now = System.currentTimeMillis()
                val martialArt = MartialArt(
                    id = 0L, // Será gerado pelo banco
                    name = currentState.name.trim(),
                    description = currentState.description.trim(),
                    color = "#FF6200EE", // Cor padrão
                    createdAt = now,
                    updatedAt = now
                )

                addMartialArtUseCase(martialArt)
                _uiState.update { it.copy(isSuccess = true, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.message ?: "Erro ao salvar modalidade",
                        isLoading = false
                    )
                }
            }
        }
    }
}

/**
 * Estado da UI para a tela de formulário de modalidades.
 */
data class MartialArtFormUiState(
    val name: String = "",
    val description: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
) 