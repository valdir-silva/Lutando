package com.example.lutando

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.lutando.presentation.screens.home.HomeScreen
import com.example.lutando.ui.theme.LutandoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LutandoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    HomeScreen(
                        onMartialArtClick = { martialArtId ->
                            // TODO: Navegar para tela de técnicas da modalidade
                        },
                        onAddMartialArtClick = {
                            // TODO: Navegar para tela de adicionar modalidade
                        }
                    )
                }
            }
        }
    }
}