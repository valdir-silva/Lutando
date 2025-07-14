package com.example.lutando.presentation.screens.technique_form

import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lutando.domain.model.MediaType
import com.example.lutando.presentation.components.MediaCaptureButton
import com.example.lutando.presentation.components.MediaPreviewCard
import com.example.lutando.presentation.components.MediaRemoveButton
import org.koin.androidx.compose.koinViewModel

/**
 * Tela de formulário para adicionar/editar técnicas.
 * Versão atualizada para usar com Navigation Compose e funcionalidades de mídia.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechniqueFormScreen(
    martialArtId: Int? = null,
    techniqueId: Int? = null,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    viewModel: TechniqueFormViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    // Configurar martialArtId e carregar técnica se for edição
    LaunchedEffect(martialArtId, techniqueId) {
        martialArtId?.let { id ->
            viewModel.setMartialArtId(id.toLong())
        }
        techniqueId?.let { id ->
            if (id > 0) {
                viewModel.loadTechniqueForEdit(id.toLong())
            }
        }
    }
    
    // Observar sucesso e navegar de volta
    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onSaveClick()
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = if (techniqueId != null && techniqueId > 0) "Editar Técnica" else "Nova Técnica",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onCancelClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Cancelar"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { viewModel.saveTechnique() },
                        enabled = !uiState.isLoading
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Salvar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Nome da técnica
                        OutlinedTextField(
                            value = uiState.name,
                            onValueChange = { viewModel.setName(it) },
                            label = { Text("Nome da técnica") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )
                        
                        // Descrição da técnica
                        OutlinedTextField(
                            value = uiState.description,
                            onValueChange = { viewModel.setDescription(it) },
                            label = { Text("Descrição (opcional)") },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 3,
                            maxLines = 5
                        )
                        
                        // Seção de mídia
                        MediaSection(
                            uiState = uiState,
                            onMediaCaptured = { uri, mediaType ->
                                viewModel.saveMediaFile(uri, mediaType)
                            },
                            onMediaRemoved = { mediaType ->
                                viewModel.removeMediaFile(mediaType)
                            },
                            onError = { error ->
                                // Mostrar erro via Snackbar
                            }
                        )
                        
                        // Botão de salvar
                        Button(
                            onClick = { viewModel.saveTechnique() },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !uiState.isLoading && uiState.name.isNotBlank()
                        ) {
                            Text(
                                text = if (uiState.isLoading) "Salvando..." else "Salvar Técnica"
                            )
                        }
                    }
                }
            }
            
            // Snackbar para erros
            uiState.error?.let { error ->
                Snackbar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    action = {
                        TextButton(onClick = { viewModel.clearError() }) {
                            Text("OK")
                        }
                    }
                ) {
                    Text(error)
                }
            }
            
            // Snackbar para erros de mídia
            uiState.mediaError?.let { error ->
                Snackbar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    action = {
                        TextButton(onClick = { viewModel.clearMediaError() }) {
                            Text("OK")
                        }
                    }
                ) {
                    Text(error)
                }
            }
        }
    }
}

@Composable
private fun MediaSection(
    uiState: TechniqueFormUiState,
    onMediaCaptured: (Uri, MediaType) -> Unit,
    onMediaRemoved: (MediaType) -> Unit,
    onError: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Mídia da Técnica",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            
            // Seção de foto
            MediaTypeSection(
                title = "Foto da Técnica",
                hasMedia = uiState.hasPhoto,
                mediaPath = uiState.photoPath,
                mediaType = MediaType.PHOTO,
                onMediaCaptured = onMediaCaptured,
                onMediaRemoved = onMediaRemoved,
                onError = onError
            )
            
            // Seção de vídeo
            MediaTypeSection(
                title = "Vídeo da Técnica",
                hasMedia = uiState.hasVideo,
                mediaPath = uiState.videoPath,
                mediaType = MediaType.VIDEO,
                onMediaCaptured = onMediaCaptured,
                onMediaRemoved = onMediaRemoved,
                onError = onError
            )
            
            // Seção de áudio
            MediaTypeSection(
                title = "Áudio Explicativo",
                hasMedia = uiState.hasAudio,
                mediaPath = uiState.audioPath,
                mediaType = MediaType.AUDIO,
                onMediaCaptured = onMediaCaptured,
                onMediaRemoved = onMediaRemoved,
                onError = onError
            )
        }
    }
}

@Composable
private fun MediaTypeSection(
    title: String,
    hasMedia: Boolean,
    mediaPath: String,
    mediaType: MediaType,
    onMediaCaptured: (Uri, MediaType) -> Unit,
    onMediaRemoved: (MediaType) -> Unit,
    onError: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
        
        if (hasMedia && mediaPath.isNotEmpty()) {
            // Mostrar preview da mídia
            MediaPreviewCard(
                mediaType = mediaType,
                uri = Uri.parse(mediaPath),
                onRemove = { onMediaRemoved(mediaType) },
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            // Mostrar botão de captura
            MediaCaptureButton(
                mediaType = mediaType,
                onMediaCaptured = { uri -> onMediaCaptured(uri, mediaType) },
                onError = onError,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TechniqueFormScreenPreview() {
    MaterialTheme {
        TechniqueFormScreen(
            martialArtId = 1,
            onSaveClick = {},
            onCancelClick = {}
        )
    }
} 