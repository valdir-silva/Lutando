package com.example.lutando.presentation.screens.technique_detail

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lutando.domain.model.MediaType
import com.example.lutando.domain.model.Technique
import com.example.lutando.presentation.components.MediaDisplay
import org.koin.androidx.compose.koinViewModel

/**
 * Tela de detalhes da técnica.
 * Versão atualizada para usar com Navigation Compose e exibir mídia.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TechniqueDetailScreen(
    techniqueId: Int,
    onBackClick: () -> Unit,
    onEditClick: (Int) -> Unit,
    onDeleteClick: () -> Unit,
    viewModel: TechniqueDetailViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    // Carregar técnica quando a tela é criada
    LaunchedEffect(techniqueId) {
        viewModel.loadTechnique(techniqueId.toLong())
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = uiState.technique?.name ?: "Detalhes da Técnica",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { onEditClick(techniqueId) }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Editar"
                        )
                    }
                    IconButton(onClick = onDeleteClick) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Deletar"
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
                uiState.error != null -> {
                    ErrorState(
                        error = uiState.error!!,
                        onRetry = { viewModel.loadTechnique(techniqueId.toLong()) },
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                uiState.technique != null -> {
                    TechniqueContent(
                        technique = uiState.technique!!,
                        mediaUris = uiState.mediaUris,
                        modifier = Modifier.fillMaxSize()
                    )
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
private fun TechniqueContent(
    technique: Technique,
    mediaUris: Map<MediaType, Uri?>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Nome da técnica
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = technique.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                
                if (technique.description.isNotBlank()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = technique.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        
        // Informações da técnica
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Informações",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                
                InfoRow("Criado em", technique.createdAt)
                InfoRow("Atualizado em", technique.updatedAt)
            }
        }
        
        // Seção de mídia
        if (technique.hasVideo || technique.hasPhoto || technique.hasAudio) {
            MediaSection(technique = technique, mediaUris = mediaUris)
        }
    }
}

@Composable
private fun InfoRow(
    label: String,
    value: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun MediaSection(
    technique: Technique,
    mediaUris: Map<MediaType, Uri?>
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
                text = "Mídia",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            
            // Chips indicando tipos de mídia disponíveis
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (technique.hasVideo) {
                    MediaChip(
                        text = "Vídeo",
                        backgroundColor = MaterialTheme.colorScheme.primary
                    )
                }
                if (technique.hasPhoto) {
                    MediaChip(
                        text = "Foto",
                        backgroundColor = MaterialTheme.colorScheme.secondary
                    )
                }
                if (technique.hasAudio) {
                    MediaChip(
                        text = "Áudio",
                        backgroundColor = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
            
            // Exibir mídia
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Foto
                if (technique.hasPhoto && technique.photoPath.isNotEmpty()) {
                    val photoUri = mediaUris[MediaType.PHOTO]
                    if (photoUri != null) {
                        MediaDisplay(
                            uri = photoUri,
                            mediaType = MediaType.PHOTO,
                            contentDescription = "Foto da técnica ${technique.name}",
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        MediaPlaceholder(
                            title = "Foto",
                            message = "Carregando foto...",
                            mediaType = MediaType.PHOTO
                        )
                    }
                }
                
                // Vídeo
                if (technique.hasVideo && technique.videoPath.isNotEmpty()) {
                    val videoUri = mediaUris[MediaType.VIDEO]
                    if (videoUri != null) {
                        MediaDisplay(
                            uri = videoUri,
                            mediaType = MediaType.VIDEO,
                            contentDescription = "Vídeo da técnica ${technique.name}",
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        MediaPlaceholder(
                            title = "Vídeo",
                            message = "Carregando vídeo...",
                            mediaType = MediaType.VIDEO
                        )
                    }
                }
                
                // Áudio
                if (technique.hasAudio && technique.audioPath.isNotEmpty()) {
                    val audioUri = mediaUris[MediaType.AUDIO]
                    if (audioUri != null) {
                        MediaDisplay(
                            uri = audioUri,
                            mediaType = MediaType.AUDIO,
                            contentDescription = "Áudio da técnica ${technique.name}",
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        MediaPlaceholder(
                            title = "Áudio",
                            message = "Carregando áudio...",
                            mediaType = MediaType.AUDIO
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MediaChip(
    text: String,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = Color.White
        )
    }
}

@Composable
private fun MediaPlaceholder(
    title: String,
    message: String,
    mediaType: MediaType
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ErrorState(
    error: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Erro ao carregar técnica",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = error,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRetry) {
            Text("Tentar novamente")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TechniqueDetailScreenPreview() {
    val sampleTechnique = Technique(
        id = 1L,
        name = "Kimura",
        description = "Técnica de finalização do jiu-jitsu brasileiro que utiliza uma chave de braço",
        martialArtId = 1L,
        hasVideo = true,
        hasPhoto = true,
        hasAudio = false,
        videoPath = "/videos/kimura.mp4",
        photoPath = "/photos/kimura.jpg",
        audioPath = "",
        createdAt = "2025-01-27T10:00:00",
        updatedAt = "2025-01-27T10:00:00"
    )
    
    MaterialTheme {
        TechniqueDetailScreen(
            techniqueId = 1,
            onBackClick = {},
            onEditClick = { _ -> },
            onDeleteClick = {}
        )
    }
} 