package com.alunando.lutando.presentation.screens.technique_detail

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alunando.lutando.domain.model.Comment
import com.alunando.lutando.domain.model.MediaType
import com.alunando.lutando.domain.model.Technique
import com.alunando.lutando.domain.repository.TechniqueRepository
import com.alunando.lutando.domain.usecase.AddCommentUseCase
import com.alunando.lutando.domain.usecase.DeleteCommentUseCase
import com.alunando.lutando.domain.usecase.DeleteMediaFileUseCase
import com.alunando.lutando.domain.usecase.GetCommentsByTechniqueUseCase
import com.alunando.lutando.domain.usecase.GetCurrentUserUseCase
import com.alunando.lutando.domain.usecase.GetMediaUriUseCase
import com.alunando.lutando.domain.usecase.UpdateCommentUseCase
import com.google.firebase.auth.FirebaseAuth
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
    val isDeleting: Boolean = false,
    val comments: List<Comment> = emptyList(),
    val currentUser: String = "Usuário",
    val currentUserId: String = "", // Adicionado para o ID do usuário logado
    val isAddingComment: Boolean = false,
    val isEditingComment: Boolean = false,
    val commentToEdit: Comment? = null,
    val commentToDelete: Comment? = null
)

class TechniqueDetailViewModel(
    private val techniqueRepository: TechniqueRepository,
    private val getMediaUriUseCase: GetMediaUriUseCase,
    private val deleteMediaFileUseCase: DeleteMediaFileUseCase,
    private val getCommentsByTechniqueUseCase: GetCommentsByTechniqueUseCase,
    private val addCommentUseCase: AddCommentUseCase,
    private val updateCommentUseCase: UpdateCommentUseCase,
    private val deleteCommentUseCase: DeleteCommentUseCase,
    private val firebaseAuth: FirebaseAuth
) : ViewModel() {

    private val _uiState = MutableStateFlow(TechniqueDetailUiState())
    val uiState: StateFlow<TechniqueDetailUiState> = _uiState.asStateFlow()

    fun loadTechnique(techniqueId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }

            try {
                val firebaseUser = firebaseAuth.currentUser
                val authorId = firebaseUser?.uid ?: ""
                val authorName = firebaseUser?.displayName ?: "Anônimo"
                
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
                            currentUser = authorName,
                            currentUserId = authorId,
                            isLoading = false
                        )
                    }
                    
                    // Carrega comentários
                    loadComments(techniqueId)
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
    
    private fun loadComments(techniqueId: String) {
        viewModelScope.launch {
            try {
                getCommentsByTechniqueUseCase(techniqueId).collect { comments ->
                    _uiState.update { it.copy(comments = comments) }
                }
            } catch (e: Exception) {
                // Log do erro para debug
                println("Erro ao carregar comentários: ${e.message}")
                _uiState.update { it.copy(comments = emptyList()) }
            }
        }
    }

    fun deleteTechnique(techniqueId: String) {
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
    
    // Funções para comentários
    fun addComment(text: String) {
        val techniqueId = _uiState.value.technique?.id ?: return
        val currentUser = firebaseAuth.currentUser ?: return

        viewModelScope.launch {
            _uiState.update { it.copy(isAddingComment = true) }

            try {
                val commentId = addCommentUseCase(
                    techniqueId = techniqueId,
                    authorId = currentUser.uid,
                    authorName = currentUser.displayName ?: "Anônimo",
                    text = text
                )
                println("Comentário adicionado com ID: $commentId")
                _uiState.update { it.copy(isAddingComment = false) }
            } catch (e: Exception) {
                println("Erro ao adicionar comentário: ${e.message}")
                e.printStackTrace()
                _uiState.update {
                    it.copy(
                        error = "Erro ao adicionar comentário: ${e.message}",
                        isAddingComment = false
                    )
                }
            }
        }
    }
    
    fun editComment(comment: Comment) {
        _uiState.update { 
            it.copy(
                commentToEdit = comment,
                isEditingComment = true
            )
        }
    }
    
    fun updateComment(text: String) {
        val comment = _uiState.value.commentToEdit ?: return
        
        viewModelScope.launch {
            _uiState.update { it.copy(isEditingComment = true) }
            
            try {
                updateCommentUseCase(comment.copy(text = text))
                _uiState.update { 
                    it.copy(
                        isEditingComment = false,
                        commentToEdit = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = "Erro ao atualizar comentário: ${e.message}",
                        isEditingComment = false
                    )
                }
            }
        }
    }
    
    fun deleteComment(comment: Comment) {
        _uiState.update { 
            it.copy(
                commentToDelete = comment
            )
        }
    }
    
    fun confirmDeleteComment() {
        val comment = _uiState.value.commentToDelete ?: return
        
        viewModelScope.launch {
            try {
                deleteCommentUseCase(comment)
                _uiState.update { it.copy(commentToDelete = null) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(
                        error = "Erro ao deletar comentário: ${e.message}",
                        commentToDelete = null
                    )
                }
            }
        }
    }
    
    fun dismissEditDialog() {
        _uiState.update { 
            it.copy(
                isEditingComment = false,
                commentToEdit = null
            )
        }
    }
    
    fun dismissDeleteDialog() {
        _uiState.update { it.copy(commentToDelete = null) }
    }
} 