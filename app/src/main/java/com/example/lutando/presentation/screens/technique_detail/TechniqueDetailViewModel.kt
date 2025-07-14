package com.example.lutando.presentation.screens.technique_detail

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lutando.domain.model.MediaType
import com.example.lutando.domain.model.Technique
import com.example.lutando.domain.repository.TechniqueRepository
import com.example.lutando.domain.usecase.DeleteMediaFileUseCase
import com.example.lutando.domain.usecase.GetMediaUriUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TechniqueDetailUiState(
    val technique: Technique? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val mediaUris: Map<MediaType, Uri?> = emptyMap(),
    val isDeleting: Boolean = false
)

class TechniqueDetailViewModel(
    private val techniqueRepository: TechniqueRepository,
    private val getMediaUriUseCase: GetMediaUriUseCase,
    private val deleteMediaFileUseCase: DeleteMediaFileUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(TechniqueDetailUiState())
    val uiState: StateFlow<TechniqueDetailUiState> = _uiState.asStateFlow()

    fun loadTechnique(techniqueId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val technique = techniqueRepository.getTechniqueById(techniqueId)
                technique?.let { tech ->
                    // Carrega URIs de mídia
                    val mediaUris = mutableMapOf<MediaType, Uri?>()

                    if (tech.hasPhoto && tech.photoPath.isNotEmpty()) {
                        getMediaUriUseCase(tech.photoPath).onSuccess { uri ->
                            mediaUris[MediaType.PHOTO] = uri
                        }
                    }

                    if (tech.hasVideo && tech.videoPath.isNotEmpty()) {
                        getMediaUriUseCase(tech.videoPath).onSuccess { uri ->
                            mediaUris[MediaType.VIDEO] = uri
                        }
                    }

                    if (tech.hasAudio && tech.audioPath.isNotEmpty()) {
                        getMediaUriUseCase(tech.audioPath).onSuccess { uri ->
                            mediaUris[MediaType.AUDIO] = uri
                        }
                    }

                    _uiState.update {
                        it.copy(
                            technique = tech,
                            mediaUris = mediaUris,
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

    fun deleteTechnique(techniqueId: Long) {
        viewModelScope.launch {
            _uiState.update { it.copy(isDeleting = true) }

            try {
                val technique = techniqueRepository.getTechniqueById(techniqueId)
                technique?.let { tech ->
                    // Deleta arquivos de mídia associados
                    if (tech.hasPhoto && tech.photoPath.isNotEmpty()) {
                        deleteMediaFileUseCase(tech.photoPath)
                    }
                    if (tech.hasVideo && tech.videoPath.isNotEmpty()) {
                        deleteMediaFileUseCase(tech.videoPath)
                    }
                    if (tech.hasAudio && tech.audioPath.isNotEmpty()) {
                        deleteMediaFileUseCase(tech.audioPath)
                    }
                }

                techniqueRepository.deleteTechniqueById(techniqueId)
                _uiState.update { it.copy(isDeleting = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = e.message ?: "Erro ao deletar técnica",
                        isDeleting = false
                    )
                }
            }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
} 