package com.alunando.lutando.presentation.screens.martial_arts_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alunando.lutando.presentation.components.MartialArtCard
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MartialArtsListScreen(
    onMartialArtClick: (String) -> Unit,
    onAddMartialArtClick: () -> Unit,
    viewModel: MartialArtsListViewModel = koinViewModel()
) {
    val martialArts by viewModel.martialArts.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Minhas Artes Marciais") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddMartialArtClick) {
                Icon(Icons.Filled.Add, "Adicionar nova arte marcial")
            }
        }
    ) {
        paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (martialArts.isEmpty()) {
                Text(
                    text = "Nenhuma arte marcial encontrada. Adicione uma nova!",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(martialArts) {
                        MartialArtCard(martialArt = it, onClick = { onMartialArtClick(it.id) })
                    }
                }
            }
        }
    }
}
