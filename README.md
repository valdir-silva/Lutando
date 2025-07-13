# 🥋 Lutando - App de Artes Marciais

## 📱 Sobre o Projeto

O **Lutando** é um aplicativo Android para praticantes de artes marciais registrarem e organizarem as técnicas aprendidas em cada aula. O app permite salvar detalhes das técnicas usando texto, voz, foto ou vídeo, organizadas por modalidades de artes marciais, com interface moderna e navegação intuitiva.

<p align="center">
  <img src="docs/screenshots/home_screen.png" alt="Home Screen" width="320" />
</p>

## 🚀 Funcionalidades

- **Perfil de Usuário**: Gerenciamento de perfil pessoal
- **CRUD Completo de Técnicas**: Criar, visualizar, editar e deletar técnicas
- **Mídia nas Técnicas**: Suporte para texto, foto, vídeo e áudio (campos prontos para integração)
- **Organização por Modalidades**: Técnicas separadas por tipo de arte marcial
- **Tela de Detalhes**: Visualização detalhada de cada técnica e modalidade
- **Dados Iniciais**: 8 modalidades cadastradas automaticamente
- **Arquitetura Limpa**: MVVM, Use Cases, Repositórios, Room, Koin
- **Interface Moderna**: Jetpack Compose

## 🛠️ Tecnologias Utilizadas

- **Kotlin**
- **Jetpack Compose** (UI)
- **Room Database** (persistência local)
- **Koin** (injeção de dependência)
- **MVVM** (arquitetura)
- **Clean Architecture**
- **Cursor IDE + Framework RIPER**

## 🏗️ Estrutura do Projeto

```
app/
  └─ src/main/java/com/example/lutando/
      ├─ data/         # DAOs, Database, Repositórios
      ├─ domain/       # Modelos, Repositórios, Use Cases
      ├─ presentation/ # Telas, ViewModels, Componentes
      ├─ di/           # Módulo Koin
      └─ ui/           # Temas
```

## ▶️ Como Rodar o Projeto

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/valdir-silva/Lutando.git
   cd Lutando
   ```
2. **Abra no Android Studio ou Cursor IDE**
3. **Compile e rode em um emulador ou dispositivo físico:**
   ```bash
   ./gradlew installDebug
   # Ou use o botão "Run" do Android Studio
   ```
4. **Pronto!** O app já vem com dados iniciais para testar.

## 💡 Como Contribuir

- Crie uma branch a partir da `main`
- Faça suas alterações
- Envie um Pull Request com uma descrição clara
- Siga o padrão de arquitetura e boas práticas do projeto

## 📊 Status do Projeto

- **Fase:** POC (Prova de Conceito) — 100% concluída
- **Funcionalidades:** CRUD, telas, navegação, dados iniciais, arquitetura limpa
- **Próximos passos:** Integração de captura de mídia, melhorias de UX, testes automatizados

## 👨‍💻 Desenvolvedor

- **Valdir Silva** — Desenvolvedor e Usuário Primário

## 📞 Contato

Para dúvidas ou sugestões, abra uma issue ou envie um pull request.

---

*Desenvolvido com ❤️ para a comunidade de artes marciais* 