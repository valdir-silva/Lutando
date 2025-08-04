package com.alunando.lutando

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.alunando.lutando.domain.usecase.SignInAnonymouslyUseCase
import com.alunando.lutando.presentation.navigation.LutandoNavigation
import com.alunando.lutando.ui.theme.LutandoTheme
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val signInAnonymouslyUseCase: SignInAnonymouslyUseCase by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            signInAnonymouslyUseCase()
        }

        setContent {
            LutandoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    LutandoNavigation(navController = navController)
                }
            }
        }
    }
}