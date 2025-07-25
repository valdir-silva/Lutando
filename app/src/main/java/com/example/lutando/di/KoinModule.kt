package com.example.lutando.di

import android.content.Context
import com.example.lutando.data.local.LutandoDatabase
import com.example.lutando.data.local.MartialArtDao
import com.example.lutando.data.local.TechniqueDao
import com.example.lutando.data.local.UserDao
import com.example.lutando.data.media.MediaManager
import com.example.lutando.data.media.PermissionManager
import com.example.lutando.data.repository.MartialArtRepositoryImpl
import com.example.lutando.data.repository.MediaRepositoryImpl
import com.example.lutando.data.repository.TechniqueRepositoryImpl
import com.example.lutando.data.repository.UserRepositoryImpl
import com.example.lutando.domain.repository.MartialArtRepository
import com.example.lutando.domain.repository.MediaRepository
import com.example.lutando.domain.repository.TechniqueRepository
import com.example.lutando.domain.repository.UserRepository
import com.example.lutando.domain.usecase.DeleteMediaFileUseCase
import com.example.lutando.domain.usecase.GetAllMartialArtsUseCase
import com.example.lutando.domain.usecase.GetCurrentUserUseCase
import com.example.lutando.domain.usecase.GetMediaUriUseCase
import com.example.lutando.domain.usecase.GetTechniquesByMartialArtUseCase
import com.example.lutando.domain.usecase.SaveMediaFileUseCase
import com.example.lutando.presentation.screens.home.HomeViewModel
import com.example.lutando.presentation.screens.martial_art_detail.MartialArtDetailViewModel
import com.example.lutando.presentation.screens.technique_detail.TechniqueDetailViewModel
import com.example.lutando.presentation.screens.technique_form.TechniqueFormViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * Módulo Koin para injeção de dependência do aplicativo Lutando.
 */
val appModule = module {

    // Database
    single { LutandoDatabase.getDatabase(androidContext()) }

    // DAOs
    single<UserDao> { get<LutandoDatabase>().userDao() }
    single<MartialArtDao> { get<LutandoDatabase>().martialArtDao() }
    single<TechniqueDao> { get<LutandoDatabase>().techniqueDao() }

    // Media Managers
    single { MediaManager(androidContext()) }
    single { PermissionManager(androidContext()) }

    // Repositories
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<MartialArtRepository> { MartialArtRepositoryImpl(get()) }
    single<TechniqueRepository> { TechniqueRepositoryImpl(get()) }
    single<MediaRepository> { MediaRepositoryImpl(get(), get()) }

    // Use Cases
    single { GetAllMartialArtsUseCase(get()) }
    single { GetCurrentUserUseCase(get()) }
    single { GetTechniquesByMartialArtUseCase(get()) }
    single { SaveMediaFileUseCase(get()) }
    single { DeleteMediaFileUseCase(get()) }
    single { GetMediaUriUseCase(get()) }

    // ViewModels
    viewModel { HomeViewModel(get()) }
    viewModel { MartialArtDetailViewModel(get(), get()) }
    viewModel { TechniqueDetailViewModel(get(), get(), get()) }
    viewModel { TechniqueFormViewModel(get(), get(), get(), get()) }
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