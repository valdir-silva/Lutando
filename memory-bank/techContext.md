# Technical Context: Lutando
*Version: 1.0*
*Created: 2025-01-27*
*Last Updated: 2025-01-27*

## Technology Stack
- **Platform**: Android (Kotlin)
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room Database (SQLite)
- **Media Storage**: Internal Storage + MediaStore API
- **Audio/Video**: MediaRecorder, CameraX
- **Image Processing**: Glide ou Coil
- **Testing**: JUnit, Mockito, Espresso, Compose Testing

## Development Environment Setup
- **IDE**: Android Studio Hedgehog ou superior
- **SDK**: Android API 24+ (Android 7.0)
- **Build System**: Gradle com Kotlin DSL
- **Language**: Kotlin 1.9+
- **Minimum SDK**: API 24
- **Target SDK**: API 34

## Dependencies
### Core Android
- **AndroidX Core**: 1.12.0 - Funcionalidades core do Android
- **AndroidX AppCompat**: 1.6.1 - Compatibilidade com versões antigas
- **AndroidX Lifecycle**: 2.7.0 - Gerenciamento de ciclo de vida

### Jetpack Compose
- **Compose BOM**: 2024.01.00 - Bill of Materials para versões consistentes
- **Compose UI**: Interface de usuário declarativa
- **Compose Material3**: Design system Material 3
- **Compose Navigation**: Navegação entre telas
- **Compose ViewModel**: Integração com ViewModel

### Database & Storage
- **Room**: 2.6.1 - ORM para SQLite
- **Room Compiler**: 2.6.1 - Annotations processor
- **DataStore**: 1.0.0 - Armazenamento de preferências

### Media & Camera
- **CameraX**: 1.3.1 - API de câmera moderna
- **MediaRecorder**: API nativa para gravação de áudio/vídeo
- **Glide**: 4.16.0 - Carregamento de imagens

### Testing
- **JUnit**: 4.13.2 - Testes unitários
- **Mockito**: 5.8.0 - Mocking framework
- **Espresso**: 3.5.1 - Testes de UI
- **Compose Testing**: Testes para Jetpack Compose
- **Test Rules**: 1.5.0 - Regras de teste

### Architecture & Clean Code
- **Hilt**: 2.48 - Injeção de dependência
- **Coroutines**: 1.7.3 - Programação assíncrona
- **Flow**: 1.7.3 - Streams reativos

## Technical Constraints
- Aplicativo deve funcionar offline
- Armazenamento local de mídia (fotos, vídeos, áudios)
- Performance otimizada para dispositivos com recursos limitados
- Compatibilidade com Android 7.0+

## Build and Deployment
- **Build Process**: Gradle build system
- **Deployment Procedure**: APK para testes, AAB para Play Store
- **CI/CD**: GitHub Actions (opcional para POC)

## Testing Approach
- **Unit Testing**: JUnit + Mockito para ViewModels e Use Cases
- **UI Testing**: Espresso + Compose Testing para telas
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
│   │   │   │   ├── repository/
│   │   │   │   └── model/
│   │   │   ├── domain/
│   │   │   │   ├── entity/
│   │   │   │   ├── repository/
│   │   │   │   └── usecase/
│   │   │   ├── presentation/
│   │   │   │   ├── ui/
│   │   │   │   ├── viewmodel/
│   │   │   │   └── theme/
│   │   │   └── di/
│   │   └── res/
│   ├── test/
│   └── androidTest/
```

---

*This document describes the technologies used in the project and how they're configured.* 