# Technical Context: Lutando
*Version: 1.1*
*Created: 2025-01-27*
*Last Updated: 2025-01-27*

## Technology Stack
- **Platform**: Android (Kotlin)
- **UI Framework**: Jetpack Compose
- **Navigation**: Navigation Compose 2.7.7
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room Database (SQLite)
- **Dependency Injection**: Koin 3.5.3
- **Media Storage**: Internal Storage + MediaStore API
- **Audio/Video**: MediaRecorder, CameraX
- **Image Processing**: Glide ou Coil
- **Testing**: JUnit, Mockito, Espresso, Compose Testing

## Development Environment Setup
- **IDE**: Android Studio Hedgehog ou superior / Cursor IDE
- **SDK**: Android API 24+ (Android 7.0)
- **Build System**: Gradle com Kotlin DSL
- **Language**: Kotlin 1.9.22
- **Minimum SDK**: API 24
- **Target SDK**: API 36
- **Compile SDK**: API 36

## Dependencies
### Core Android
- **AndroidX Core**: 1.16.0 - Funcionalidades core do Android
- **AndroidX Lifecycle**: 2.9.0 - Gerenciamento de ciclo de vida
- **AndroidX Activity Compose**: 1.10.1 - Integração Activity + Compose

### Jetpack Compose
- **Compose BOM**: 2024.09.00 - Bill of Materials para versões consistentes
- **Compose UI**: Interface de usuário declarativa
- **Compose Material3**: Design system Material 3
- **Compose Navigation**: 2.7.7 - Navegação entre telas
- **Compose ViewModel**: Integração com ViewModel

### Navigation
- **Navigation Compose**: 2.7.7 - Navegação type-safe para Compose
- **NavHost**: Componente principal de navegação
- **NavController**: Controle de navegação
- **NavArgument**: Argumentos tipados para rotas

### Database & Storage
- **Room**: 2.6.1 - ORM para SQLite
- **Room Compiler**: 2.6.1 - Annotations processor
- **Room KTX**: 2.6.1 - Extensões Kotlin para Room

### Dependency Injection
- **Koin Core**: 3.5.3 - Framework de injeção de dependência
- **Koin Android**: 3.5.3 - Integração com Android
- **Koin Compose**: 3.5.3 - Integração com Jetpack Compose

### Media & Camera
- **CameraX**: 1.3.1 - API de câmera moderna
- **MediaRecorder**: API nativa para gravação de áudio/vídeo
- **Glide**: 4.16.0 - Carregamento de imagens

### Testing
- **JUnit**: 4.13.2 - Testes unitários
- **Mockito**: 5.8.0 - Mocking framework
- **Espresso**: 3.6.1 - Testes de UI
- **Compose Testing**: Testes para Jetpack Compose
- **Test Rules**: 1.2.1 - Regras de teste

### Architecture & Clean Code
- **Coroutines**: 1.7.3 - Programação assíncrona
- **Flow**: 1.7.3 - Streams reativos
- **Gson**: 2.10.1 - Serialização JSON

## Technical Constraints
- Aplicativo deve funcionar offline
- Armazenamento local de mídia (fotos, vídeos, áudios)
- Performance otimizada para dispositivos com recursos limitados
- Compatibilidade com Android 7.0+
- Navegação type-safe com argumentos tipados

## Build and Deployment
- **Build Process**: Gradle build system
- **Deployment Procedure**: APK para testes, AAB para Play Store
- **CI/CD**: GitHub Actions (opcional para POC)
- **Build Status**: ✅ Compilação bem-sucedida

## Testing Approach
- **Unit Testing**: JUnit + Mockito para ViewModels e Use Cases
- **UI Testing**: Espresso + Compose Testing para telas
- **Navigation Testing**: Testes de navegação entre telas
- **Integration Testing**: Testes de banco de dados e repositórios
- **Test Coverage**: Mínimo 70% para POC

## Project Structure
```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/lutando/
│   │   │   ├── data/
│   │   │   │   ├── local/
│   │   │   │   │   ├── Converters.kt
│   │   │   │   │   ├── InitialData.kt
│   │   │   │   │   ├── LutandoDatabase.kt
│   │   │   │   │   ├── MartialArtDao.kt
│   │   │   │   │   ├── TechniqueDao.kt
│   │   │   │   │   └── UserDao.kt
│   │   │   │   └── repository/
│   │   │   │       ├── MartialArtRepositoryImpl.kt
│   │   │   │       ├── TechniqueRepositoryImpl.kt
│   │   │   │       └── UserRepositoryImpl.kt
│   │   │   ├── domain/
│   │   │   │   ├── model/
│   │   │   │   │   ├── MartialArt.kt
│   │   │   │   │   ├── Technique.kt
│   │   │   │   │   └── User.kt
│   │   │   │   ├── repository/
│   │   │   │   │   ├── MartialArtRepository.kt
│   │   │   │   │   ├── TechniqueRepository.kt
│   │   │   │   │   └── UserRepository.kt
│   │   │   │   └── usecase/
│   │   │   │       ├── GetAllMartialArtsUseCase.kt
│   │   │   │       ├── GetCurrentUserUseCase.kt
│   │   │   │       └── GetTechniquesByMartialArtUseCase.kt
│   │   │   ├── presentation/
│   │   │   │   ├── components/
│   │   │   │   │   └── MartialArtCard.kt
│   │   │   │   ├── navigation/
│   │   │   │   │   ├── NavRoutes.kt
│   │   │   │   │   └── LutandoNavigation.kt
│   │   │   │   └── screens/
│   │   │   │       ├── home/
│   │   │   │       │   ├── HomeScreen.kt
│   │   │   │       │   └── HomeViewModel.kt
│   │   │   │       ├── martial_art_detail/
│   │   │   │       │   ├── MartialArtDetailScreen.kt
│   │   │   │       │   └── MartialArtDetailViewModel.kt
│   │   │   │       ├── technique_detail/
│   │   │   │       │   ├── TechniqueDetailScreen.kt
│   │   │   │       │   └── TechniqueDetailViewModel.kt
│   │   │   │       └── technique_form/
│   │   │   │           ├── TechniqueFormScreen.kt
│   │   │   │           └── TechniqueFormViewModel.kt
│   │   │   ├── di/
│   │   │   │   └── KoinModule.kt
│   │   │   └── ui/
│   │   │       └── theme/
│   │   │           ├── Color.kt
│   │   │           ├── Theme.kt
│   │   │           └── Type.kt
│   │   └── res/
│   ├── test/
│   └── androidTest/
```

## Navigation Implementation Details

### Navigation Components
- **NavRoutes.kt**: Definição centralizada de rotas
- **LutandoNavigation.kt**: Grafo de navegação principal
- **MainActivity.kt**: Single Activity com Navigation Compose

### Navigation Routes
```kotlin
object NavRoutes {
    const val HOME = "home"
    const val MARTIAL_ART_DETAIL = "martial_art_detail/{martialArtId}"
    const val TECHNIQUE_DETAIL = "technique_detail/{techniqueId}"
    const val TECHNIQUE_FORM = "technique_form/{martialArtId}"
    const val TECHNIQUE_EDIT = "technique_edit/{techniqueId}"
}
```

### Navigation Arguments
- **martialArtId**: Int - ID da modalidade de arte marcial
- **techniqueId**: Int - ID da técnica

### Navigation Patterns
- **Single Activity**: Uma única Activity com Navigation Compose
- **Type-safe Navigation**: Argumentos tipados para cada rota
- **Back Stack Management**: Navegação automática de volta
- **State Preservation**: Estado preservado durante navegação

## Performance Considerations
- **Lazy Loading**: LazyColumn para listas grandes
- **State Management**: StateFlow para atualizações eficientes
- **Navigation**: Single Activity para performance
- **Database**: Queries otimizadas com Room
- **Memory Management**: ViewModels com lifecycle awareness

## Code Style Guidelines

### Import Organization
- **Order**: Imports organizados em ordem alfabética
- **Grouping**: Imports agrupados por categoria:
  1. Imports do Kotlin (kotlin.*)
  2. Imports do Android (android.*)
  3. Imports do AndroidX (androidx.*)
  4. Imports do Jetpack Compose (androidx.compose.*)
  5. Imports de bibliotecas externas (com.*, org.*)
  6. Imports locais do projeto (com.example.lutando.*)

### Import Examples
```kotlin
// Kotlin imports
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

// Android imports
import android.content.Context
import android.net.Uri

// AndroidX imports
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

// External libraries
import com.example.lutando.domain.model.MartialArt
import com.example.lutando.domain.repository.MartialArtRepository
import com.example.lutando.presentation.navigation.NavRoutes
```

### Indentation Rules
- **Spaces**: Usar 4 espaços para indentação (não tabs)
- **Line Length**: Máximo 120 caracteres por linha
- **Function Parameters**: Parâmetros em múltiplas linhas quando necessário
- **Chained Calls**: Quebrar em múltiplas linhas para legibilidade

### Indentation Examples
```kotlin
// Function with multiple parameters
fun createTechnique(
    name: String,
    description: String,
    martialArtId: Int,
    mediaFiles: List<String>
): Technique {
    return Technique(
        id = 0,
        name = name,
        description = description,
        martialArtId = martialArtId,
        mediaFiles = mediaFiles
    )
}

// Chained calls
LazyColumn(
    modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    verticalArrangement = Arrangement.spacedBy(8.dp)
) {
    items(martialArts) { martialArt ->
        MartialArtCard(
            martialArt = martialArt,
            onClick = { onMartialArtClick(martialArt.id) }
        )
    }
}
```

### Code Formatting Standards
- **Braces**: Chaves na mesma linha para funções e classes
- **Spacing**: Espaço após vírgulas e operadores
- **Naming**: camelCase para variáveis e funções, PascalCase para classes
- **Comments**: Comentários em português quando necessário

### IDE Configuration
- **Android Studio**: Configurar para usar 4 espaços
- **Auto-format**: Habilitar formatação automática ao salvar
- **Import optimization**: Organizar imports automaticamente
- **Code style**: Seguir Kotlin official style guide

## Known Issues & Warnings
- ⚠️ **ArrowBack Deprecation**: Warnings sobre uso de ArrowBack (não crítico)
- ⚠️ **Unused Variables**: Variáveis não utilizadas nos previews (não crítico)
- ✅ **Compilation**: Projeto compila sem erros

---

*This document describes the technologies used in the project and how they're configured.* 