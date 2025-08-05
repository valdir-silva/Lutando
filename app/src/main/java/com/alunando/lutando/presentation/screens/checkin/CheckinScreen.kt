package com.alunando.lutando.presentation.screens.checkin

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.TextButton
import com.alunando.lutando.domain.model.Academy
import com.alunando.lutando.util.Resource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckinScreen(
    navController: NavController,
    viewModel: CheckinViewModel = koinViewModel()
) {
    val academyId by viewModel.academyId.collectAsState()
    val academiesResource by viewModel.academies.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var showAcademySelectionDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.checkinEvent.collectLatest { event ->
            when (event) {
                is CheckinViewModel.CheckinEvent.Success -> {
                    snackbarHostState.showSnackbar(event.message)
                }
                is CheckinViewModel.CheckinEvent.Error -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Fazer Check-in") })
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    showAcademySelectionDialog = true
                }
            ) {
                OutlinedTextField(
                    value = academyId,
                    onValueChange = { /* Read-only, value set by selection */ },
                    label = { Text("ID da Academia") },
                    readOnly = true,
                    enabled = false, // Make it non-interactive for typing
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { viewModel.onCheckinClick(academyId) },
                modifier = Modifier.fillMaxWidth(),
                enabled = academyId.isNotBlank()
            ) {
                Text("Fazer Check-in")
            }
        }
    }

    if (showAcademySelectionDialog) {
        AlertDialog(
            onDismissRequest = { showAcademySelectionDialog = false },
            title = { Text("Selecione uma Academia") },
            text = {
                when (academiesResource) {
                    is Resource.Loading -> {
                        CircularProgressIndicator()
                    }
                    is Resource.Success -> {
                        val academies = (academiesResource.data as? List<Academy>) ?: emptyList()
                        if (academies.isEmpty()) {
                            Text("Nenhuma academia encontrada.")
                        } else {
                            LazyColumn {
                                items(academies) { academy ->
                                    Text(
                                        text = academy.name,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                viewModel.onAcademyIdChange(academy.id)
                                                showAcademySelectionDialog = false
                                            }
                                            .padding(8.dp)
                                    )
                                }
                            }
                        }
                    }
                    is Resource.Error -> {
                        Text("Erro ao carregar academias: ${academiesResource.message}")
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showAcademySelectionDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}
