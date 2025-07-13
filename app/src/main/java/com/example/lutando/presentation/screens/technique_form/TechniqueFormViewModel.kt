package com.example.lutando.presentation.screens.technique_form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lutando.domain.model.Technique
import com.example.lutando.domain.repository.TechniqueRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class TechniqueFormUiState(
    val name: String = "",
    val description: String = "",
    val martialArtId: Long = 0L,
    val hasVideo: Boolean = false,
    val hasPhoto: Boolean = false,
    val hasAudio: Boolean = false,
    val videoPath: String = "",
    val photoPath: String = "",
    val audioPath: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)

class TechniqueFormViewModel(
    private val techniqueRepository: TechniqueRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(TechniqueFormUiState())
    val uiState: StateFlow<TechniqueFormUiState> = _uiState.asStateFlow()

    fun setName(name: String) {
        _uiState.update { it.copy(name = name) }
    }

    fun setDescription(description: String) {
        _uiState.update { it.copy(description = description) }
    }

    fun setMartialArtId(martialArtId: Long) {
        _uiState.update { it.copy(martialArtId = martialArtId) }
    }

    fun setHasVideo(hasVideo: Boolean) {
        _uiState.update { it.copy(hasVideo = hasVideo) }
    }

    fun setHasPhoto(hasPhoto: Boolean) {
        _uiState.update { it.copy(hasPhoto = hasPhoto) }
    }

    fun setHasAudio(hasAudio: Boolean) {
        _uiState.update { it.copy(hasAudio = hasAudio) }
    }

    fun setVideoPath(path: String) {
        _uiState.update { it.copy(videoPath = path) }
    }

    fun setPhotoPath(path: String) {
        _uiState.update { it.copy(photoPath = path) }
    }

    fun setAudioPath(path: String) {
        _uiState.update { it.copy(audioPath = path) }
    }

    fun saveTechnique() {
        val currentState = _uiState.value
        
        if (currentState.name.isBlank()) {
            _uiState.update { it.copy(error = "Nome da técnica é obrigatório") }
            return
        }

        if (currentState.martialArtId == 0L) {
            _uiState.update { it.copy(error = "Modalidade é obrigatória") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            try {
                val now = System.currentTimeMillis()
                val technique = Technique(
                    id = 0L, // Será gerado pelo banco
                    name = currentState.name,
                    description = currentState.description,
                    martialArtId = currentState.martialArtId,
                    hasVideo = currentState.hasVideo,
                    hasPhoto = currentState.hasPhoto,
                    hasAudio = currentState.hasAudio,
                    videoPath = currentState.videoPath,
                    photoPath = currentState.photoPath,
                    audioPath = currentState.audioPath,
                    createdAt = now.toString(),
                    updatedAt = now.toString()
                )
                
                techniqueRepository.insertTechnique(technique)
                _uiState.update { it.copy(isSuccess = true, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        error = e.message ?: "Erro ao salvar técnica",
                        isLoading = false
                    )
                }
            }
        }
    }

    fun loadTechniqueForEdit(techniqueId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            
            try {
                val technique = techniqueRepository.getTechniqueById(techniqueId)
                technique?.let { tech ->
                    _uiState.update {
                        it.copy(
                            name = tech.name,
                            description = tech.description,
                            martialArtId = tech.martialArtId,
                            hasVideo = tech.hasVideo,
                            hasPhoto = tech.hasPhoto,
                            hasAudio = tech.hasAudio,
                            videoPath = tech.videoPath,
                            photoPath = tech.photoPath,
                            audioPath = tech.audioPath,
                            isLoading = false
                        )
                    }
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

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }

    fun clearSuccess() {
        _uiState.update { it.copy(isSuccess = false) }
    }
} 