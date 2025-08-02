package com.alunando.lutando

import com.alunando.lutando.data.local.ConvertersTest
import com.alunando.lutando.domain.model.MartialArtTest
import com.alunando.lutando.domain.model.TechniqueTest
import com.alunando.lutando.domain.model.UserTest
import com.alunando.lutando.domain.usecase.DeleteMediaFileUseCaseTest
import com.alunando.lutando.domain.usecase.GetAllMartialArtsUseCaseTest
import com.alunando.lutando.domain.usecase.GetCurrentUserUseCaseTest
import com.alunando.lutando.domain.usecase.GetMediaUriUseCaseTest
import com.alunando.lutando.domain.usecase.GetTechniquesByMartialArtUseCaseTest
import com.alunando.lutando.domain.usecase.SaveMediaFileUseCaseTest
import com.alunando.lutando.presentation.screens.home.HomeViewModelTest
import com.alunando.lutando.presentation.screens.martial_art_detail.MartialArtDetailViewModelTest
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