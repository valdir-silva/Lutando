package com.example.lutando

import com.example.lutando.data.local.ConvertersTest
import com.example.lutando.domain.model.MartialArtTest
import com.example.lutando.domain.model.TechniqueTest
import com.example.lutando.domain.model.UserTest
import com.example.lutando.domain.usecase.DeleteMediaFileUseCaseTest
import com.example.lutando.domain.usecase.GetAllMartialArtsUseCaseTest
import com.example.lutando.domain.usecase.GetCurrentUserUseCaseTest
import com.example.lutando.domain.usecase.GetMediaUriUseCaseTest
import com.example.lutando.domain.usecase.GetTechniquesByMartialArtUseCaseTest
import com.example.lutando.domain.usecase.SaveMediaFileUseCaseTest
import com.example.lutando.presentation.screens.home.HomeViewModelTest
import com.example.lutando.presentation.screens.martial_art_detail.MartialArtDetailViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * Suíte de testes unitários para o aplicativo Lutando.
 *
 * Esta suíte inclui testes para:
 * - Modelos de domínio (MartialArt, Technique, User, MediaFile)
 * - Use Cases (todos os casos de uso da aplicação)
 * - ViewModels (HomeViewModel, MartialArtDetailViewModel)
 * - Conversores de dados (Converters do Room)
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    // Modelos de domínio
    MartialArtTest::class,
    TechniqueTest::class,
    UserTest::class,

    // Use Cases
    GetAllMartialArtsUseCaseTest::class,
    GetTechniquesByMartialArtUseCaseTest::class,
    GetCurrentUserUseCaseTest::class,
    SaveMediaFileUseCaseTest::class,
    DeleteMediaFileUseCaseTest::class,
    GetMediaUriUseCaseTest::class,

    // ViewModels
    HomeViewModelTest::class,
    MartialArtDetailViewModelTest::class,

    // Conversores
    ConvertersTest::class
)
class TestSuite 