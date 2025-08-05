package com.alunando.lutando.di

import android.content.Context

import com.alunando.lutando.data.media.MediaManager
import com.alunando.lutando.data.media.PermissionManager
import com.alunando.lutando.data.repository.AuthRepositoryImpl
import com.alunando.lutando.data.repository.CommentRepositoryImpl
import com.alunando.lutando.data.repository.MartialArtRepositoryFirebaseImpl
import com.alunando.lutando.data.repository.MediaRepositoryImpl
import com.alunando.lutando.data.repository.TechniqueRepositoryImpl
import com.alunando.lutando.data.repository.UserRepositoryImpl
import com.alunando.lutando.domain.repository.AuthRepository
import com.alunando.lutando.domain.repository.CommentRepository
import com.alunando.lutando.domain.repository.MartialArtRepository
import com.alunando.lutando.domain.repository.MediaRepository
import com.alunando.lutando.domain.repository.TechniqueRepository
import com.alunando.lutando.domain.repository.AcademyRepository
import com.alunando.lutando.domain.repository.UserRepository
import com.alunando.lutando.domain.usecase.AddAcademyUseCase
import com.alunando.lutando.domain.usecase.AddCommentUseCase
import com.alunando.lutando.domain.usecase.DeleteAcademyUseCase
import com.alunando.lutando.domain.usecase.GetAllAcademiesUseCase
import com.alunando.lutando.domain.usecase.GetAcademyByIdUseCase
import com.alunando.lutando.domain.usecase.UpdateAcademyUseCase
import com.alunando.lutando.data.repository.AcademyRepositoryFirebaseImpl
import com.alunando.lutando.domain.usecase.AddMartialArtUseCase
import com.alunando.lutando.domain.usecase.DeleteCommentUseCase
import com.alunando.lutando.domain.usecase.DeleteMediaFileUseCase
import com.alunando.lutando.domain.usecase.GetAllMartialArtsUseCase
import com.alunando.lutando.domain.usecase.GetCommentsByTechniqueUseCase
import com.alunando.lutando.domain.usecase.GetCurrentUserUseCase
import com.alunando.lutando.domain.usecase.GetMediaUriUseCase
import com.alunando.lutando.domain.usecase.GetTechniquesByMartialArtUseCase
import com.alunando.lutando.domain.usecase.SaveMediaFileUseCase
import com.alunando.lutando.domain.usecase.SignInAnonymouslyUseCase
import com.alunando.lutando.domain.usecase.UpdateCommentUseCase
import com.alunando.lutando.presentation.screens.home.HomeViewModel
import com.alunando.lutando.presentation.screens.martial_art_detail.MartialArtDetailViewModel
import com.alunando.lutando.presentation.screens.martial_art_form.MartialArtFormViewModel
import com.alunando.lutando.presentation.screens.technique_detail.TechniqueDetailViewModel
import com.alunando.lutando.presentation.screens.technique_form.TechniqueFormViewModel
import com.alunando.lutando.presentation.screens.martial_arts_list.MartialArtsListViewModel
import com.alunando.lutando.presentation.screens.academy.AcademyViewModel
import com.alunando.lutando.data.repository.CheckinRepositoryFirebaseImpl
import com.alunando.lutando.domain.repository.CheckinRepository
import com.alunando.lutando.domain.usecase.AddCheckinUseCase
import com.alunando.lutando.domain.usecase.GetCheckinsByAcademyUseCase
import com.alunando.lutando.domain.usecase.GetCheckinsByAthleteUseCase
import com.alunando.lutando.presentation.screens.academy_checkins.AcademyCheckinsViewModel
import com.alunando.lutando.presentation.screens.athlete_checkins.AthleteCheckinsViewModel
import com.alunando.lutando.presentation.screens.checkin.CheckinViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * Módulo Koin para injeção de dependência do aplicativo Lutando.
 */
val appModule = module {

    // Firebase
    single { FirebaseAuth.getInstance() }
    single { FirebaseFirestore.getInstance() }

    

    // Media Managers
    single { MediaManager(androidContext()) }
    single { PermissionManager(androidContext()) }

    // Repositories
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<MartialArtRepository> { MartialArtRepositoryFirebaseImpl(get(), get()) }
    single<TechniqueRepository> { TechniqueRepositoryImpl(get(), get()) }
    single<MediaRepository> { MediaRepositoryImpl(get(), get()) }
    single<CommentRepository> { CommentRepositoryImpl(get()) }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
    single<AcademyRepository> { AcademyRepositoryFirebaseImpl(get(), get()) }
    single<CheckinRepository> { CheckinRepositoryFirebaseImpl(get()) }


    // Use Cases
    single { GetAllMartialArtsUseCase(get()) }
    single { GetCurrentUserUseCase(get()) }
    single { GetTechniquesByMartialArtUseCase(get()) }
    single { SaveMediaFileUseCase(get()) }
    single { DeleteMediaFileUseCase(get()) }
    single { GetMediaUriUseCase(get()) }
    single { GetCommentsByTechniqueUseCase(get()) }
    single { AddCommentUseCase(get()) }
    single { UpdateCommentUseCase(get()) }
    single { DeleteCommentUseCase(get()) }
    single { AddMartialArtUseCase(get()) }
    single { SignInAnonymouslyUseCase(get()) }
    single { AddAcademyUseCase(get(), get()) }
    single { GetAllAcademiesUseCase(get()) }
    single { GetAcademyByIdUseCase(get()) }
    single { UpdateAcademyUseCase(get(), get()) }
    single { DeleteAcademyUseCase(get()) }
    single { AddCheckinUseCase(get()) }
    single { GetCheckinsByAthleteUseCase(get()) }
    single { GetCheckinsByAcademyUseCase(get()) }

    // ViewModels
    viewModel { HomeViewModel(get()) }
    viewModel { MartialArtDetailViewModel(get(), get()) }
    viewModel { TechniqueDetailViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
    viewModel { TechniqueFormViewModel(get(), get(), get(), get()) }
    viewModel { MartialArtFormViewModel(get()) }
    viewModel { MartialArtsListViewModel(get()) }
    viewModel { AcademyViewModel(get(), get(), get(), get(), get()) }
    viewModel { CheckinViewModel(get(), get(), get()) }
    viewModel { AthleteCheckinsViewModel(get(), get()) }
    viewModel { AcademyCheckinsViewModel(get(), get(), get()) }
}

/**
 * Inicializa o Koin no aplicativo.
 */
fun initKoin(context: Context) {
    startKoin {
        androidContext(context)
        modules(appModule)
    }
} 