package com.alunando.lutando.presentation.screens.academy_form

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.alunando.lutando.domain.model.Academy
import com.alunando.lutando.presentation.screens.academy.AcademyViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcademyFormScreen(
    academyId: String? = null,
    onSaveClick: () -> Unit,
    onCancelClick: () -> Unit,
    viewModel: AcademyViewModel = koinViewModel()
) {
    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    val currentAcademy by viewModel.currentAcademy.collectAsStateWithLifecycle()

    LaunchedEffect(academyId) {
        if (academyId != null) {
            viewModel.getAcademy(academyId)
        } else {
            viewModel.resetCurrentAcademy()
        }
    }

    LaunchedEffect(currentAcademy) {
        currentAcademy?.let {
            name = it.name
            address = it.address
            phone = it.phone
            email = it.email
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (academyId == null) "Adicionar Academia" else "Editar Academia") },
                navigationIcon = {
                    IconButton(onClick = onCancelClick) {
                        Icon(Icons.Filled.ArrowBack, "Voltar")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome da Academia") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Endereço") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Telefone") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    val academy = Academy(
                        id = academyId ?: "",
                        name = name,
                        address = address,
                        phone = phone,
                        email = email
                    )
                    if (academyId == null) {
                        viewModel.addAcademy(academy)
                    } else {
                        viewModel.updateAcademy(academy)
                    }
                    onSaveClick()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (academyId == null) "Salvar Academia" else "Atualizar Academia")
            }
        }
    }
}