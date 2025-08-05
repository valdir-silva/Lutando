package com.alunando.lutando.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.Text
import androidx.navigation.navArgument
import com.alunando.lutando.presentation.screens.home.HomeScreen
import com.alunando.lutando.presentation.screens.martial_arts_list.MartialArtsListScreen
import com.alunando.lutando.presentation.screens.martial_art_detail.MartialArtDetailScreen
import com.alunando.lutando.presentation.screens.martial_art_form.MartialArtFormScreen
import com.alunando.lutando.presentation.screens.technique_detail.TechniqueDetailScreen
import com.alunando.lutando.presentation.screens.technique_form.TechniqueFormScreen
import com.alunando.lutando.presentation.screens.academy.AcademyScreen
import com.alunando.lutando.presentation.screens.academy_form.AcademyFormScreen
import com.alunando.lutando.presentation.screens.checkin.CheckinScreen
import com.alunando.lutando.presentation.screens.athlete_checkins.AthleteCheckinsScreen
import com.alunando.lutando.presentation.screens.academy_checkins.AcademyCheckinsScreen

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
        // Nova Tela Inicial (Dashboard)
        composable(NavRoutes.HOME) {
            HomeScreen(
                onMartialArtsListClick = {
                    navController.navigate(NavRoutes.MARTIAL_ARTS_LIST)
                },
                onCreateAcademyClick = {
                    navController.navigate(NavRoutes.ACADEMY_LIST)
                },
                onCheckInClick = {
                    navController.navigate(NavRoutes.CHECK_IN)
                },
                onAthleteCheckinsClick = {
                    navController.navigate(NavRoutes.ATHLETE_CHECKINS)
                },
                onAcademyCheckinsClick = {
                    navController.navigate(NavRoutes.ACADEMY_CHECKINS)
                }
            )
        }

        // Tela de Listagem de Artes Marciais (antiga HOME)
        composable(NavRoutes.MARTIAL_ARTS_LIST) {
            MartialArtsListScreen(
                onMartialArtClick = { martialArtId ->
                    navController.navigate(NavRoutes.martialArtDetail(martialArtId))
                },
                onAddMartialArtClick = {
                    navController.navigate(NavRoutes.MARTIAL_ART_FORM)
                }
            )
        }

        // Tela de Listagem de Academias
        composable(NavRoutes.ACADEMY_LIST) {
            AcademyScreen(
                onAddAcademyClick = {
                    navController.navigate(NavRoutes.academyForm())
                },
                onEditAcademyClick = { academyId ->
                    navController.navigate(NavRoutes.academyForm(academyId))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // Tela de formulário de Academia (Adição/Edição)
        composable(
            route = NavRoutes.ACADEMY_FORM,
            arguments = listOf(
                navArgument("academyId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) { backStackEntry ->
            val academyId = backStackEntry.arguments?.getString("academyId")
            AcademyFormScreen(
                academyId = academyId,
                onSaveClick = {
                    navController.popBackStack()
                },
                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }

        

        // Placeholder para Fazer Check-in
        composable(NavRoutes.CHECK_IN) {
            CheckinScreen(navController = navController)
        }

        // Tela de Meus Check-ins
        composable(NavRoutes.ATHLETE_CHECKINS) {
            AthleteCheckinsScreen(navController = navController)
        }

        // Tela de Check-ins da Academia
        composable(NavRoutes.ACADEMY_CHECKINS) {
            AcademyCheckinsScreen(navController = navController)
        }

        // Tela de formulário de nova modalidade
        composable(NavRoutes.MARTIAL_ART_FORM) {
            MartialArtFormScreen(
                onSaveClick = {
                    navController.popBackStack()
                },
                onCancelClick = {
                    navController.popBackStack()
                }
            )
        }

        // Tela de detalhes da modalidade
        composable(
            route = NavRoutes.MARTIAL_ART_DETAIL,
            arguments = listOf(
                navArgument("martialArtId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val martialArtId = backStackEntry.arguments?.getString("martialArtId") ?: ""
            MartialArtDetailScreen(
                martialArtId = martialArtId,
                onTechniqueClick = { techniqueId ->
                    navController.navigate(NavRoutes.techniqueDetail(techniqueId))
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
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val techniqueId = backStackEntry.arguments?.getString("techniqueId") ?: ""
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
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val martialArtId = backStackEntry.arguments?.getString("martialArtId") ?: ""
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
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val techniqueId = backStackEntry.arguments?.getString("techniqueId") ?: ""
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