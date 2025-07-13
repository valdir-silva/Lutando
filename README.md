# 🥋 Lutando - App de Artes Marciais

## 📱 Sobre o Projeto

O **Lutando** é um aplicativo Android para praticantes de artes marciais registrarem e organizarem as técnicas aprendidas em cada aula. O app permite salvar detalhes das técnicas usando texto, voz, foto ou vídeo, organizadas por modalidades de artes marciais, com interface moderna e navegação intuitiva usando os padrões mais recentes do Google.

<p align="center">
  <img src="docs/screenshots/home_screen.png" alt="Home Screen" width="200" />
</p>

## 🚀 Funcionalidades

- **Perfil de Usuário**: Gerenciamento de perfil pessoal
- **CRUD Completo de Técnicas**: Criar, visualizar, editar e deletar técnicas
- **Mídia nas Técnicas**: Suporte para texto, foto, vídeo e áudio (campos prontos para integração)
- **Organização por Modalidades**: Técnicas separadas por tipo de arte marcial
- **Tela de Detalhes**: Visualização detalhada de cada técnica e modalidade
- **Navegação Completa**: Navigation Compose com rotas e argumentos tipados
- **Dados Iniciais**: 8 modalidades cadastradas automaticamente
- **Arquitetura Limpa**: MVVM, Use Cases, Repositórios, Room, Koin
- **Interface Moderna**: Jetpack Compose + Material Design 3

## 🛠️ Tecnologias Utilizadas

- **Kotlin**
- **Jetpack Compose** (UI)
- **Navigation Compose** (navegação)
- **Room Database** (persistência local)
- **Koin** (injeção de dependência)
- **MVVM** (arquitetura)
- **Clean Architecture**
- **Material Design 3**
- **Cursor IDE + Framework RIPER**

## 🏗️ Estrutura do Projeto

```
app/
  └─ src/main/java/com/example/lutando/
      ├─ data/         # DAOs, Database, Repositórios
      ├─ domain/       # Modelos, Repositórios, Use Cases
      ├─ presentation/ # Telas, ViewModels, Componentes, Navegação
      ├─ di/           # Módulo Koin
      └─ ui/           # Temas
```

## 🔄 Fluxo de Navegação

O app possui navegação completa entre todas as telas:

```
HomeScreen → MartialArtDetailScreen → TechniqueDetailScreen
     ↓              ↓                        ↓
     ↓         TechniqueFormScreen    TechniqueFormScreen
     ↓              ↓                        ↓
     ↓              ↓                        ↓
  Voltar ←      Voltar ←              Voltar ←
```

### Rotas Implementadas
- `HOME` - Tela inicial com lista de modalidades
- `MARTIAL_ART_DETAIL/{martialArtId}` - Detalhes da modalidade
- `TECHNIQUE_DETAIL/{techniqueId}` - Detalhes da técnica
- `TECHNIQUE_FORM/{martialArtId}` - Formulário de nova técnica
- `TECHNIQUE_EDIT/{techniqueId}` - Edição de técnica existente

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

## 🎯 Como Usar

1. **Tela Inicial**: Visualize todas as modalidades de artes marciais
2. **Selecionar Modalidade**: Toque em uma modalidade para ver suas técnicas
3. **Adicionar Técnica**: Use o botão flutuante (+) para adicionar nova técnica
4. **Ver Detalhes**: Toque em uma técnica para ver seus detalhes
5. **Editar/Deletar**: Use os botões na tela de detalhes da técnica

## 💡 Como Contribuir

- Crie uma branch a partir da `main`
- Faça suas alterações
- Envie um Pull Request com uma descrição clara
- Siga o padrão de arquitetura e boas práticas do projeto

## 📊 Status do Projeto

- **Fase:** POC (Prova de Conceito) — 85% concluída
- **Funcionalidades:** CRUD, telas, navegação completa, dados iniciais, arquitetura limpa
- **Progresso:** Foundation (100%), Core Features (100%), UI (100%), Navegação (100%)
- **Próximos passos:** Funcionalidades de mídia, testes automatizados, melhorias de UX

## 🎨 Padrões de Design

- **Material Design 3**: Interface moderna e acessível
- **Navigation Compose**: Navegação type-safe e eficiente
- **Clean Architecture**: Separação clara de responsabilidades
- **MVVM**: Gerenciamento de estado reativo
- **Jetpack Compose**: UI declarativa e performática

## 👨‍💻 Desenvolvedor

- **Valdir Silva** — Desenvolvedor e Usuário Primário

## 📞 Contato

Para dúvidas ou sugestões, abra uma issue ou envie um pull request.

---

*Desenvolvido com ❤️ para a comunidade de artes marciais* 