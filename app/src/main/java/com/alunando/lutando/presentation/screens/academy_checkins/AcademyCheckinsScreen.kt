package com.alunando.lutando.presentation.screens.academy_checkins

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.alunando.lutando.presentation.components.checkin.CheckinCard
import com.alunando.lutando.util.Resource
import com.alunando.lutando.domain.model.Checkin
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcademyCheckinsScreen(
    navController: NavController,
    viewModel: AcademyCheckinsViewModel = koinViewModel()
) {
    val checkinsResource by viewModel.checkins.collectAsState()
    val academyId by viewModel.academyId.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Check-ins da Academia ${academyId ?: ""}") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when (checkinsResource) {
                is Resource.Loading<List<Checkin>> -> {
                    CircularProgressIndicator()
                }
                is Resource.Success<List<Checkin>> -> {
                    val checkins = checkinsResource.data ?: emptyList()
                    if (checkins.isEmpty()) {
                        Text("Nenhum check-in encontrado para esta academia.")
                    } else {
                        LazyColumn(
                            contentPadding = PaddingValues(vertical = 8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(checkins) {
                                CheckinCard(checkin = it)
                            }
                        }
                    }
                }
                is Resource.Error<List<Checkin>> -> {
                    Text("Erro: ${checkinsResource.message}")
                }
            }
        }
    }
}
