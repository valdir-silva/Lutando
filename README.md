# 🥋 Lutando - App de Artes Marciais

## 📱 Sobre o Projeto

O **Lutando** é um aplicativo Android para praticantes de artes marciais registrarem e organizarem as técnicas aprendidas. O app permite salvar detalhes das técnicas usando texto, mídia (fotos, vídeos, áudios) e comentários, com uma interface moderna e arquitetura robusta baseada nas melhores práticas do Google.

<p align="center">
  <img src="docs/screenshots/home_screen.png" alt="Home Screen" width="250" />
</p>

## ✨ Funcionalidades Principais

- **Tela Inicial (Super App)**: Acesso rápido a diversas funcionalidades, incluindo a gestão de modalidades e técnicas.
- **CRUD Completo**: Crie, visualize, edite e delete modalidades e técnicas.
- **Gestão de Mídia**: Anexe fotos, vídeos e áudios a cada técnica, com players integrados para visualização.
- **Sistema de Comentários**: Adicione, edite e delete comentários em cada técnica para anotações adicionais.
- **Arquitetura Sólida**: Construído com Clean Architecture, MVVM, e injeção de dependência com Koin.
- **100% Offline**: Todos os dados são armazenados localmente usando o Room Database.
- **Interface Moderna**: UI totalmente feita em Jetpack Compose e Material Design 3.
- **Navegação Segura**: Utiliza Navigation Compose com rotas e argumentos tipados.

## 🛠️ Tecnologias Utilizadas

- **Linguagem**: [Kotlin](https://kotlinlang.org/)
- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Arquitetura**: Clean Architecture + MVVM
- **Navegação**: [Navigation Compose](https://developer.android.com/jetpack/compose/navigation)
- **Persistência de Dados**: [Room](https://developer.android.com/training/data-storage/room) (em migração para [Firebase Firestore](https://firebase.google.com/docs/firestore))
- **Injeção de Dependência**: [Koin](https://insert-koin.io/)
- **Programação Assíncrona**: Kotlin Coroutines & Flow
- **Desenvolvimento Assistido**: Cursor IDE + Framework RIPER

## 🏗️ Estrutura do Projeto

O projeto segue uma estrutura de Clean Architecture, separando as responsabilidades em três camadas principais:

```
app/
  └─ src/main/java/com/alunando/lutando/
      ├─ data/         # Implementação de repositórios, DAOs, Database, MediaManager
      ├─ domain/       # Modelos de negócio, interfaces de repositório e Use Cases
      └─ presentation/ # UI (Telas, ViewModels, Componentes) e Navegação
```

## ▶️ Como Rodar o Projeto

### Pré-requisitos
- Android Studio Hedgehog ou superior
- Emulador ou dispositivo físico com Android 7.0 (API 24) ou superior

### Passos
1. **Clone o repositório:**
   ```bash
   git clone https://github.com/valdir-silva/Lutando.git
   cd Lutando
   ```
2. **Abra no Android Studio.**
3. **Compile e rode o aplicativo.** O Gradle cuidará de baixar todas as dependências necessárias.

O aplicativo já vem com dados iniciais (modalidades e técnicas) para facilitar os testes.

## 📊 Status do Projeto

- **Fase:** Prova de Conceito (POC)
- **Status:** Estável e funcional.
- **Próximos Passos:** Migração para Firebase (em andamento) e início do desenvolvimento multiplataforma (Web).

## 💡 Como Contribuir

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma *issue* ou enviar um *pull request*.

1.  Faça um *fork* do projeto.
2.  Crie uma nova *branch* (`git checkout -b feature/nova-feature`).
3.  Faça o *commit* das suas alterações (`git commit -m 'feat: Adiciona nova feature'`).
4.  Faça o *push* para a *branch* (`git push origin feature/nova-feature`).
5.  Abra um *Pull Request*.

## 👨‍💻 Desenvolvedor

- **Valdir Silva**

---

*Desenvolvido com ❤️ para a comunidade de artes marciais.* 