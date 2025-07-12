# 🥋 Lutando - App de Artes Marciais

## 📱 Sobre o Projeto

O **Lutando** é um aplicativo Android desenvolvido para ajudar praticantes de artes marciais a registrar e organizar as técnicas aprendidas durante suas aulas. O app permite salvar detalhes das técnicas usando texto, voz, foto ou vídeo, organizadas por modalidades de artes marciais.

## 🎯 Funcionalidades

- **Perfil de Usuário**: Gerenciamento de perfil pessoal
- **Registro de Técnicas**: CRUD completo para técnicas de artes marciais
- **Múltiplos Formatos**: Suporte para texto, voz, foto e vídeo
- **Organização por Modalidades**: Separação clara por tipos de artes marciais
- **Interface Moderna**: Desenvolvido com Jetpack Compose

## 🛠️ Tecnologias

- **Linguagem**: Kotlin
- **UI Framework**: Jetpack Compose
- **Arquitetura**: Clean Architecture + MVVM
- **Database**: Room Database
- **Injeção de Dependência**: Hilt
- **Testes**: JUnit, Mockito, Espresso, Compose Testing

## 📋 Pré-requisitos

- Android Studio Hedgehog ou superior
- Android SDK API 24+ (Android 7.0)
- Kotlin 1.9+
- JDK 11

## 🚀 Como Executar

1. Clone o repositório:
```bash
git clone [URL_DO_REPOSITORIO]
cd Lutando
```

2. Abra o projeto no Android Studio

3. Sincronize o Gradle e aguarde o download das dependências

4. Execute o app em um emulador ou dispositivo físico

## 🏗️ Estrutura do Projeto

```
app/
├── src/
│   ├── main/
│   │   ├── java/com/example/lutando/
│   │   │   ├── data/           # Camada de dados
│   │   │   ├── domain/         # Camada de domínio
│   │   │   ├── presentation/   # Camada de apresentação
│   │   │   └── di/            # Injeção de dependência
│   │   └── res/               # Recursos
│   ├── test/                  # Testes unitários
│   └── androidTest/           # Testes de UI
```

## 📊 Status do Projeto

- **Fase**: POC (Prova de Conceito)
- **Prazo**: 2 semanas
- **Progresso**: 5% (Setup inicial completo)

## 🧪 Testes

```bash
# Executar testes unitários
./gradlew test

# Executar testes de UI
./gradlew connectedAndroidTest

# Executar todos os testes
./gradlew check
```

## 📝 Licença

Este projeto é desenvolvido para fins educacionais e pessoais.

## 👨‍💻 Desenvolvedor

- **Valdir Silva** - Desenvolvedor e Usuário Primário

## 📞 Contato

Para dúvidas ou sugestões, entre em contato através do repositório.

---

*Desenvolvido com ❤️ para a comunidade de artes marciais* 