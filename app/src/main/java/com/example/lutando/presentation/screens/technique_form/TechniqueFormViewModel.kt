package com.example.lutando.presentation.screens.technique_form

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lutando.domain.model.MediaType
import com.example.lutando.domain.model.Technique
import com.example.lutando.domain.repository.MediaRepository
import com.example.lutando.domain.repository.TechniqueRepository
import com.example.lutando.domain.usecase.DeleteMediaFileUseCase
import com.example.lutando.domain.usecase.SaveMediaFileUseCase
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
    val isSuccess: Boolean = false,
    val isSavingMedia: Boolean = false,
    val mediaError: String? = null
)

class TechniqueFormViewModel(
    private val techniqueRepository: TechniqueRepository,
    private val saveMediaFileUseCase: SaveMediaFileUseCase,
    private val deleteMediaFileUseCase: DeleteMediaFileUseCase,
    private val mediaRepository: MediaRepository
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
    
    /**
     * Salva um arquivo de mídia e atualiza o estado da técnica.
     */
    fun saveMediaFile(sourceUri: Uri, mediaType: MediaType) {
        viewModelScope.launch {
            _uiState.update { it.copy(isSavingMedia = true, mediaError = null) }
            
            try {
                val result = saveMediaFileUseCase(sourceUri, mediaType)
                result.fold(
                    onSuccess = { filePath ->
                        when (mediaType) {
                            MediaType.PHOTO -> {
                                _uiState.update { 
                                    it.copy(
                                        hasPhoto = true,
                                        photoPath = filePath,
                                        isSavingMedia = false
                                    )
                                }
                            }
                            MediaType.VIDEO -> {
                                _uiState.update { 
                                    it.copy(
                                        hasVideo = true,
                                        videoPath = filePath,
                                        isSavingMedia = false
                                    )
                                }
                            }
                            MediaType.AUDIO -> {
                                _uiState.update { 
                                    it.copy(
                                        hasAudio = true,
                                        audioPath = filePath,
                                        isSavingMedia = false
                                    )
                                }
                            }
                        }
                    },
                    onFailure = { exception ->
                        _uiState.update { 
                            it.copy(
                                mediaError = exception.message ?: "Erro ao salvar mídia",
                                isSavingMedia = false
                            )
                        }
                    }
                )
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        mediaError = e.message ?: "Erro ao salvar mídia",
                        isSavingMedia = false
                    )
                }
            }
        }
    }
    
    /**
     * Remove um arquivo de mídia e atualiza o estado da técnica.
     */
    fun removeMediaFile(mediaType: MediaType) {
        viewModelScope.launch {
            val currentState = _uiState.value
            val filePath = when (mediaType) {
                MediaType.PHOTO -> currentState.photoPath
                MediaType.VIDEO -> currentState.videoPath
                MediaType.AUDIO -> currentState.audioPath
            }
            
            if (filePath.isNotEmpty()) {
                deleteMediaFileUseCase(filePath)
            }
            
            when (mediaType) {
                MediaType.PHOTO -> {
                    _uiState.update { 
                        it.copy(
                            hasPhoto = false,
                            photoPath = ""
                        )
                    }
                }
                MediaType.VIDEO -> {
                    _uiState.update { 
                        it.copy(
                            hasVideo = false,
                            videoPath = ""
                        )
                    }
                }
                MediaType.AUDIO -> {
                    _uiState.update { 
                        it.copy(
                            hasAudio = false,
                            audioPath = ""
                        )
                    }
                }
            }
        }
    }
    
    /**
     * Verifica se as permissões necessárias para um tipo de mídia estão concedidas.
     */
    fun hasRequiredPermissions(mediaType: MediaType): Boolean {
        return mediaRepository.hasRequiredPermissions(mediaType)
    }
    
    /**
     * Obtém as permissões necessárias para um tipo de mídia.
     */
    fun getRequiredPermissions(mediaType: MediaType): Array<String> {
        return mediaRepository.getRequiredPermissions(mediaType)
    }
    
    /**
     * Limpa erros de mídia.
     */
    fun clearMediaError() {
        _uiState.update { it.copy(mediaError = null) }
    }
} 