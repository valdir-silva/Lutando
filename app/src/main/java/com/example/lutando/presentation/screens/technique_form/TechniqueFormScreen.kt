package com.example.lutando.presentation.screens.technique_form

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
import org.koin.androidx.compose.koinViewModel

/**
 * Tela de formulário para adicionar/editar técnicas.
 * Versão atualizada para usar com Navigation Compose.
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
                            hasVideo = uiState.hasVideo,
                            hasPhoto = uiState.hasPhoto,
                            hasAudio = uiState.hasAudio,
                            onVideoChange = { viewModel.setHasVideo(it) },
                            onPhotoChange = { viewModel.setHasPhoto(it) },
                            onAudioChange = { viewModel.setHasAudio(it) }
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
        }
    }
}

@Composable
private fun MediaSection(
    hasVideo: Boolean,
    hasPhoto: Boolean,
    hasAudio: Boolean,
    onVideoChange: (Boolean) -> Unit,
    onPhotoChange: (Boolean) -> Unit,
    onAudioChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Mídia da Técnica",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            
            // Checkbox para vídeo
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = hasVideo,
                    onCheckedChange = onVideoChange
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Incluir vídeo da técnica",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            
            // Checkbox para foto
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = hasPhoto,
                    onCheckedChange = onPhotoChange
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Incluir foto da técnica",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            
            // Checkbox para áudio
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = hasAudio,
                    onCheckedChange = onAudioChange
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Incluir áudio explicativo",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            
            if (hasVideo || hasPhoto || hasAudio) {
                Text(
                    text = "Nota: A captura de mídia será implementada na próxima versão",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
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