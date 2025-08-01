package com.example.lutando.presentation.screens.martial_art_form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lutando.ui.theme.LutandoTheme
import org.koin.androidx.compose.koinViewModel

/**
 * Tela de formulário para adicionar modalidades de artes marciais.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MartialArtFormScreen(
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    viewModel: MartialArtFormViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Observar sucesso e navegar de volta
    LaunchedEffect(uiState.isSuccess) {
        if (uiState.isSuccess) {
            onSaveClick()
        }
    }

    MartialArtFormContent(
        uiState = uiState,
        onSaveClick = { viewModel.saveMartialArt() },
        onCancelClick = onCancelClick,
        onNameChange = { viewModel.setName(it) },
        onDescriptionChange = { viewModel.setDescription(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MartialArtFormContent(
    uiState: MartialArtFormUiState,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    onNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Nova Modalidade",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onCancelClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                actions = {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(16.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        IconButton(
                            onClick = onSaveClick,
                            enabled = uiState.name.isNotBlank()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Salvar"
                            )
                        }
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    )
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = "Informações da Modalidade",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )

                        OutlinedTextField(
                            value = uiState.name,
                            onValueChange = onNameChange,
                            label = { Text("Nome da modalidade") },
                            placeholder = { Text("Ex: Jiu-Jitsu Brasileiro") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true,
                            isError = uiState.error != null && uiState.name.isBlank()
                        )

                        OutlinedTextField(
                            value = uiState.description,
                            onValueChange = onDescriptionChange,
                            label = { Text("Descrição (opcional)") },
                            placeholder = { Text("Descreva brevemente a modalidade...") },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 3,
                            maxLines = 5
                        )
                    }
                }

                // Botões de ação
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = onSaveClick,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = uiState.name.isNotBlank() && !uiState.isLoading
                    ) {
                        Text("Salvar Modalidade")
                    }

                    TextButton(
                        onClick = onCancelClick,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !uiState.isLoading
                    ) {
                        Text("Cancelar")
                    }
                }
            }

            // Snackbar para erros
            if (uiState.error != null) {
                Snackbar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                ) {
                    Text(uiState.error)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MartialArtFormScreenPreview() {
    LutandoTheme {
        MartialArtFormScreen(
            onSaveClick = {},
            onCancelClick = {}
        )
    }
} 