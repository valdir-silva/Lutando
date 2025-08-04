package com.alunando.lutando.presentation.screens.martial_art_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alunando.lutando.domain.model.MartialArt
import com.alunando.lutando.domain.model.Technique
import org.koin.androidx.compose.koinViewModel

/**
 * Tela de detalhes da modalidade de arte marcial.
 * Versão atualizada para usar com Navigation Compose.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MartialArtDetailScreen(
    martialArtId: String,
    onBackClick: () -> Unit,
    onAddTechniqueClick: (String) -> Unit,
    onTechniqueClick: (Long) -> Unit,
    viewModel: MartialArtDetailViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Carregar dados quando a tela é criada
    LaunchedEffect(martialArtId) {
        viewModel.loadMartialArt(martialArtId)
    }

    MartialArtDetailContent(
        uiState = uiState,
        onBackClick = onBackClick,
        onAddTechniqueClick = { onAddTechniqueClick(martialArtId) },
        onTechniqueClick = onTechniqueClick,
        onRetry = { viewModel.loadMartialArt(martialArtId) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MartialArtDetailContent(
    uiState: MartialArtDetailUiState,
    onBackClick: () -> Unit,
    onAddTechniqueClick: () -> Unit,
    onTechniqueClick: (Long) -> Unit,
    onRetry: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = uiState.martialArt?.name ?: "Carregando...",
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
                    IconButton(onClick = { /* TODO: Menu de opções */ }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Mais opções"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddTechniqueClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar técnica",
                    tint = Color.White
                )
            }
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
                        onRetry = onRetry,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                uiState.martialArt == null -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                uiState.techniques.isEmpty() -> {
                    EmptyTechniquesState(
                        martialArtName = uiState.martialArt?.name ?: "",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(uiState.techniques) { technique ->
                            TechniqueCard(
                                technique = technique,
                                onClick = { onTechniqueClick(technique.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TechniqueCard(
    technique: Technique,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = technique.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (technique.description.isNotBlank()) {
                Text(
                    text = technique.description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Criado em ${technique.createdAt}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Indicadores de mídia
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (technique.hasVideo) {
                        MediaIndicator(
                            text = "Vídeo",
                            backgroundColor = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (technique.hasPhoto) {
                        MediaIndicator(
                            text = "Foto",
                            backgroundColor = MaterialTheme.colorScheme.secondary
                        )
                    }
                    if (technique.hasAudio) {
                        MediaIndicator(
                            text = "Áudio",
                            backgroundColor = MaterialTheme.colorScheme.tertiary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MediaIndicator(
    text: String,
    backgroundColor: Color
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(backgroundColor)
            .padding(horizontal = 6.dp, vertical = 2.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White
        )
    }
}

@Composable
private fun EmptyTechniquesState(
    martialArtName: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Nenhuma técnica encontrada",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Adicione sua primeira técnica de $martialArtName",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
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
            text = "Erro ao carregar técnicas",
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
fun MartialArtDetailScreenPreview() {
    val sampleMartialArt = MartialArt(
        id = "1",
        name = "Jiu-Jitsu",
        description = "Arte marcial brasileira"
    )
    val sampleTechniques = listOf(
        Technique(
            id = 1L,
            name = "Kimura",
            description = "Chave de braço clássica do Jiu-Jitsu",
            martialArtId = 1L,
            createdAt = "2024-01-27",
            hasVideo = true,
            hasPhoto = true,
            hasAudio = false
        ),
        Technique(
            id = 2L,
            name = "Triângulo",
            description = "Finalização com as pernas",
            martialArtId = 1L,
            createdAt = "2024-01-26",
            hasVideo = true,
            hasPhoto = false,
            hasAudio = true
        )
    )
    val uiState = MartialArtDetailUiState(
        martialArt = sampleMartialArt,
        techniques = sampleTechniques,
        isLoading = false,
        error = null
    )
    MaterialTheme {
        MartialArtDetailContent(
            uiState = uiState,
            onBackClick = {},
            onAddTechniqueClick = {},
            onTechniqueClick = {},
            onRetry = {}
        )
    }
} 