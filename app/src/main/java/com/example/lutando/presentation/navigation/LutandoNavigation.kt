package com.example.lutando.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.lutando.presentation.screens.home.HomeScreen
import com.example.lutando.presentation.screens.martial_art_detail.MartialArtDetailScreen
import com.example.lutando.presentation.screens.technique_detail.TechniqueDetailScreen
import com.example.lutando.presentation.screens.technique_form.TechniqueFormScreen

/**
 * Componente principal de navegação do aplicativo Lutando
 * Utiliza Navigation Compose para gerenciar a navegação entre telas
 */
@Composable
fun LutandoNavigation(
    navController: NavHostController,
    startDestination: String = NavRoutes.HOME
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Tela inicial - Lista de modalidades
        composable(NavRoutes.HOME) {
            HomeScreen(
                onMartialArtClick = { martialArtId ->
                    navController.navigate(NavRoutes.martialArtDetail(martialArtId.toInt()))
                },
                onAddMartialArtClick = {
                    // TODO: Implementar adição de modalidade
                }
            )
        }
        
        // Tela de detalhes da modalidade
        composable(
            route = NavRoutes.MARTIAL_ART_DETAIL,
            arguments = listOf(
                navArgument("martialArtId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val martialArtId = backStackEntry.arguments?.getInt("martialArtId") ?: 0
            MartialArtDetailScreen(
                martialArtId = martialArtId,
                onTechniqueClick = { techniqueId ->
                    navController.navigate(NavRoutes.techniqueDetail(techniqueId.toInt()))
                },
                onAddTechniqueClick = { currentMartialArtId ->
                    navController.navigate(NavRoutes.techniqueForm(currentMartialArtId))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        
        // Tela de detalhes da técnica
        composable(
            route = NavRoutes.TECHNIQUE_DETAIL,
            arguments = listOf(
                navArgument("techniqueId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val techniqueId = backStackEntry.arguments?.getInt("techniqueId") ?: 0
            TechniqueDetailScreen(
                techniqueId = techniqueId,
                onEditClick = { currentTechniqueId ->
                    navController.navigate(NavRoutes.techniqueEdit(currentTechniqueId))
                },
                onBackClick = {
                    navController.popBackStack()
                },
                onDeleteClick = {
                    // TODO: Implementar deleção
                    navController.popBackStack()
                }
            )
        }
        
        // Tela de formulário de nova técnica
        composable(
            route = NavRoutes.TECHNIQUE_FORM,
            arguments = listOf(
                navArgument("martialArtId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val martialArtId = backStackEntry.arguments?.getInt("martialArtId") ?: 0
            TechniqueFormScreen(
                martialArtId = martialArtId,
                onSaveClick = {
                    navController.popBackStack()
                },
                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }
        
        // Tela de edição de técnica existente
        composable(
            route = NavRoutes.TECHNIQUE_EDIT,
            arguments = listOf(
                navArgument("techniqueId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val techniqueId = backStackEntry.arguments?.getInt("techniqueId") ?: 0
            TechniqueFormScreen(
                techniqueId = techniqueId,
                onSaveClick = {
                    navController.popBackStack()
                },
                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }
    }
} 