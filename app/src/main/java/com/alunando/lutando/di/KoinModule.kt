package com.alunando.lutando.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alunando.lutando.data.local.CommentDao
import com.alunando.lutando.data.local.InitialData
import com.alunando.lutando.data.local.LutandoDatabase
import com.alunando.lutando.data.local.MartialArtDao
import com.alunando.lutando.data.local.TechniqueDao
import com.alunando.lutando.data.local.UserDao
import com.alunando.lutando.data.media.MediaManager
import com.alunando.lutando.data.media.PermissionManager
import com.alunando.lutando.data.repository.CommentRepositoryImpl
import com.alunando.lutando.data.repository.MartialArtRepositoryImpl
import com.alunando.lutando.data.repository.MediaRepositoryImpl
import com.alunando.lutando.data.repository.TechniqueRepositoryImpl
import com.alunando.lutando.data.repository.UserRepositoryImpl
import com.alunando.lutando.domain.repository.CommentRepository
import com.alunando.lutando.domain.repository.MartialArtRepository
import com.alunando.lutando.domain.repository.MediaRepository
import com.alunando.lutando.domain.repository.TechniqueRepository
import com.alunando.lutando.domain.repository.UserRepository
import com.alunando.lutando.domain.usecase.AddCommentUseCase
import com.alunando.lutando.domain.usecase.AddMartialArtUseCase
import com.alunando.lutando.domain.usecase.DeleteCommentUseCase
import com.alunando.lutando.domain.usecase.DeleteMediaFileUseCase
import com.alunando.lutando.domain.usecase.GetAllMartialArtsUseCase
import com.alunando.lutando.domain.usecase.GetCommentsByTechniqueUseCase
import com.alunando.lutando.domain.usecase.GetCurrentUserUseCase
import com.alunando.lutando.domain.usecase.GetMediaUriUseCase
import com.alunando.lutando.domain.usecase.GetTechniquesByMartialArtUseCase
import com.alunando.lutando.domain.usecase.SaveMediaFileUseCase
import com.alunando.lutando.domain.usecase.UpdateCommentUseCase
import com.alunando.lutando.presentation.screens.home.HomeViewModel
import com.alunando.lutando.presentation.screens.martial_art_detail.MartialArtDetailViewModel
import com.alunando.lutando.presentation.screens.martial_art_form.MartialArtFormViewModel
import com.alunando.lutando.presentation.screens.technique_detail.TechniqueDetailViewModel
import com.alunando.lutando.presentation.screens.technique_form.TechniqueFormViewModel
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

    // Database
    single {
        val context = androidContext()
        Room.databaseBuilder(
            context,
            LutandoDatabase::class.java,
            "lutando_database"
        )
            .addMigrations(LutandoDatabase.MIGRATION_1_2)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    // Popula o banco de dados na primeira criação
                    CoroutineScope(Dispatchers.IO).launch {
                        val database = get<LutandoDatabase>()
                        val userDao = database.userDao()
                        val martialArtDao = database.martialArtDao()

                        userDao.insertUser(InitialData.defaultUser)
                        InitialData.martialArts.forEach { martialArt ->
                            martialArtDao.insertMartialArt(martialArt)
                        }
                    }
                }
            })
            .build()
    }

    // DAOs
    single<UserDao> { get<LutandoDatabase>().userDao() }
    single<MartialArtDao> { get<LutandoDatabase>().martialArtDao() }
    single<TechniqueDao> { get<LutandoDatabase>().techniqueDao() }
    single<CommentDao> { get<LutandoDatabase>().commentDao() }

    // Media Managers
    single { MediaManager(androidContext()) }
    single { PermissionManager(androidContext()) }

    // Repositories
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<MartialArtRepository> { MartialArtRepositoryImpl(get()) }
    single<TechniqueRepository> { TechniqueRepositoryImpl(get()) }
    single<MediaRepository> { MediaRepositoryImpl(get(), get()) }
    single<CommentRepository> { CommentRepositoryImpl(get()) }

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

    // ViewModels
    viewModel { HomeViewModel(get()) }
    viewModel { MartialArtDetailViewModel(get(), get()) }
    viewModel { TechniqueDetailViewModel(get(), get(), get(), get(), get(), get(), get(), get()) }
    viewModel { TechniqueFormViewModel(get(), get(), get(), get()) }
    viewModel { MartialArtFormViewModel(get()) }
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