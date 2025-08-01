# Progress: Lutando
*Version: 1.2*
*Created: 2025-01-27*
*Last Updated: 2025-01-27*

## 🏁 Resumo das Entregas Concluídas

- **Foundation:** Clean Architecture, MVVM, Jetpack Compose, Room, Koin, Material Design 3.
- **Core Features:** Modelos de dados (User, MartialArt, Technique), DAOs, repositórios, use cases, ViewModels reativos, dados iniciais.
- **UI Components:** HomeScreen, MartialArtDetailScreen, TechniqueDetailScreen, TechniqueFormScreen, MartialArtCard, Previews.
- **Navegação:** Navigation Compose, rotas, argumentos, callbacks, MainActivity, NavRoutes, LutandoNavigation, telas e ViewModels integrados.
- **CRUD:** Criar, ler, atualizar e deletar técnicas.
- **Funcionalidades de Mídia:** Captura de foto/vídeo/áudio, armazenamento local, visualização, permissões, componentes reutilizáveis, integração com formulários, preview, remoção, ícones customizados, MediaRecorder, seleção de áudio.
- **Checklist de mídia:** Todos os itens de implementação e funcionalidades de mídia marcados como concluídos.

## ✅ O que está funcionando

### Foundation (100% Concluído)
- ✅ Clean Architecture implementada
- ✅ MVVM pattern implementado
- ✅ Jetpack Compose configurado
- ✅ Room Database configurado
- ✅ Koin para injeção de dependência
- ✅ Material Design 3 implementado

### Core Features (100% Concluído)
- ✅ Entidades de dados (User, MartialArt, Technique)
- ✅ DAOs para acesso a dados
- ✅ Repositórios implementados
- ✅ Use Cases criados
- ✅ ViewModels com estado reativo
- ✅ Dados iniciais (8 modalidades de artes marciais)

### UI Components (100% Concluído)
- ✅ HomeScreen - Lista de modalidades
- ✅ MartialArtDetailScreen - Lista de técnicas da modalidade
- ✅ TechniqueDetailScreen - Detalhes da técnica
- ✅ TechniqueFormScreen - Formulário para adicionar/editar técnicas
- ✅ MartialArtCard - Componente reutilizável
- ✅ Previews do Jetpack Compose implementados

### Navegação (100% Concluído)
- ✅ Navigation Compose 2.7.7 implementado
- ✅ Rotas definidas (HOME, MARTIAL_ART_DETAIL, TECHNIQUE_DETAIL, TECHNIQUE_FORM, TECHNIQUE_EDIT)
- ✅ Argumentos de navegação (martialArtId, techniqueId)
- ✅ Callbacks de navegação implementados
- ✅ Navegação entre todas as telas funcionando
- ✅ MainActivity atualizada para usar Navigation Compose
- ✅ NavRoutes.kt - Definição centralizada de rotas
- ✅ LutandoNavigation.kt - Componente principal de navegação
- ✅ Telas atualizadas para aceitar parâmetros de navegação
- ✅ ViewModels atualizados para carregar dados por ID

### CRUD Operations (100% Concluído)
- ✅ Criar técnica
- ✅ Ler técnicas por modalidade
- ✅ Atualizar técnica
- ✅ Deletar técnica (estrutura preparada)

### Funcionalidades de Mídia (100% Concluído)
- ✅ Captura de foto (câmera e galeria)
- ✅ Gravação de vídeo (câmera e galeria)
- ✅ Gravação de áudio (microfone nativo + galeria)
- ✅ Armazenamento de mídia local
- ✅ Visualização de mídia (foto, vídeo, áudio)
- ✅ Gerenciamento de permissões
- ✅ Componentes de mídia reutilizáveis
- ✅ Integração com formulário de técnicas
- ✅ Preview de mídia capturada
- ✅ Remoção de mídia
- ✅ Ícones customizados para mídia
- ✅ Gravação de áudio com MediaRecorder nativo
- ✅ Seleção de arquivos de áudio da galeria

## 🔄 O que está em desenvolvimento

### Testes (30% Concluído)
- [x] Testes unitários (modelos, use cases, conversores, ViewModels principais)
- [ ] Testes de UI
- [ ] Testes de navegação
- [ ] Testes de integração

## 📋 Backlog

### Funcionalidades Avançadas
- [ ] Busca de técnicas (1 dia)
- [ ] Filtros por modalidade (1 dia)
- [ ] Ordenação de técnicas (1 dia)
- [ ] Exportação de dados (2 dias)
- [ ] Backup e restauração (2 dias)

### Melhorias de UX
- [ ] Animações de transição (1 dia)
- [ ] Feedback tátil (0,5 dia)
- [ ] Suporte a temas escuro/claro (1 dia)
- [ ] Acessibilidade (1 dia)
- [ ] Internacionalização (2 dias)

### Funcionalidades Futuras

- [ ] **Possibilidade de comentar dentro de uma técnica criada** (2 dias)
    - Cada técnica terá uma seção de comentários, armazenados localmente.
    - Campos: texto do comentário, autor (multiusuário), data/hora.
    - UI: exibição e adição de comentários na tela de detalhes da técnica.
    - Permitir editar/deletar comentários próprios.
    - (Futuro) Notificações locais para novos comentários.

- [ ] **Opções de tags para separar por faixa, por estilo, por grupos de técnicas** (2 dias)
    - Técnicas podem ter múltiplas tags (ex: “faixa azul”, “projeção”, “finalização”).
    - Tags pré-definidas e/ou customizáveis.
    - UI: seleção/adicionar tags no formulário e filtros na listagem.
    - Sugestão automática de tags mais usadas.
    - Cores/ícones para tipos de tags.
    - Estatísticas de uso por tag.

- [ ] **Marcar o nível de domínio da técnica e lembrar o usuário quando fizer muito tempo sem usar para revisar** (2 dias)
    - Campo “nível de domínio” (iniciante, intermediário, avançado, mestre) por técnica/usuário.
    - Data da última revisão/uso.
    - Notificações locais para lembrar de revisar técnicas esquecidas.
    - Configuração de periodicidade dos lembretes.
    - Histórico de evolução do domínio.
    - Sugestão de técnicas para revisar baseado no tempo sem uso.

- [ ] **Timeline para registrar quando informou ao app que usou a técnica e nível de domínio** (2 dias)
    - Cada vez que o usuário marca que usou uma técnica, salva-se data/hora e nível de domínio.
    - Exibir na tela de detalhes: “Última vez usada: data (nível: X)”.
    - Timeline simples mostrando datas de uso.
    - Gráfico de frequência de uso por técnica.
    - Badge para técnicas “recentemente usadas” ou “há muito tempo sem uso”.

- [ ] **Favoritar técnicas** (1 dia)
    - Marcar técnicas favoritas para acesso rápido.
    - Ícone de estrela/coração nas listas e detalhes.
    - Filtro/aba de “Favoritas”.
    - Ordenar lista mostrando favoritas primeiro.

- [ ] **Busca inteligente** (2 dias)
    - Busca por nome, tag, faixa, estilo, nível de domínio, etc.
    - Filtros avançados e sugestões automáticas.
    - Busca por voz e histórico de buscas recentes.

- [ ] **Sugestão de técnicas para treinar** (2 dias)
    - Sugestão baseada em tempo sem uso, nível de domínio ou tags.
    - Seção “Sugestões para hoje” e notificações.
    - Configuração de critérios e quantidade de sugestões.

- [ ] **Exportação/backup dos dados** (2 dias)
    - Exportação do banco para arquivo (.json, .csv, .db).
    - Botão de exportar/backup nas configurações.
    - Opção de restaurar backup e backup automático na nuvem.

- [ ] **Integração com vídeo/aula externa** (1 dia)
    - Permitir anexar links de vídeos externos ou anotações extras a uma técnica.
    - Campo para adicionar link/nota na edição/criação.
    - Preview do vídeo e suporte a múltiplos links.

- [ ] **Gamificação** (4 dias)
    - Conquistas, badges e tela de progressos.
    - Notificações ao desbloquear conquistas.
    - Exemplos: revisar X técnicas em uma semana, manter frequência, revisar todas de uma faixa.

- [ ] **Modo “revisão aleatória”** (1 dia)
    - Botão para sugerir técnica aleatória para revisão.
    - Configuração para incluir/excluir favoritas, por faixa, etc.
    - Histórico das técnicas revisadas aleatoriamente.

- [ ] **Funcionalidades Sociais e Gamificação**
    - **Registro de Treino Diário** (2 dias)
        - Usuário pode marcar que treinou em determinado dia.
        - Adição de local do treino (nome da academia, endereço, etc).
        - Informar equipe (nome, filiação, etc).
        - Marcar colegas de treino presentes, mesmo que não tenham conta no app.
        - Marcar o professor do treino, mesmo que não tenha conta no app.
        - Ao criar conta, se o nome do usuário já foi marcado em treinos anteriores, sugerir vinculação desses registros ao novo usuário.
    - **Detalhamento do Treino** (2 dias)
        - Registrar quantas finalizações sofreu e quantas aplicou.
        - Contra/faixa de qual graduação (ex: sofreu finalização de faixa branca, roxa, preta, etc).
        - Em qual graduação aplicou finalização.
        - Tipo de finalização (ex: armlock, mata-leão, etc).
        - Quem era o professor.
        - Observações livres.
    - **Gamificação e Desafios** (4 dias)
        - Basear-se no Gymrats para criar desafios mensais entre amigos (ex: quem treinou mais vezes, quem finalizou mais, etc).
        - Sistema de conquistas e badges por frequência, desempenho, participação em desafios.
        - Ranking entre amigos e histórico de desempenho.
    - **Vinculação Retroativa de Usuários** (2 dias)
        - Quando um novo usuário cria conta, se já foi marcado em treinos por outros, perguntar se aquele é ele e vincular registros.
    - **Integração e Testes** (2 dias)
        - Integração das funcionalidades sociais com o restante do app.
        - Testes de usabilidade e fluxo.
    - **Total estimado:** 14 dias

- [ ] **Sistema de Gestão para Professores**
    - **Controle de Presença com QR Code** (3 dias)
        - QR Code único para cada academia/local de treino.
        - Scanner de QR Code integrado ao app para alunos confirmarem presença.
        - Validação de localização GPS para garantir que o aluno está no local correto.
        - Timestamp automático da presença.
        - Notificação para o professor quando aluno confirma presença.
    - **Painel do Professor** (4 dias)
        - Dashboard com visão geral de todos os alunos.
        - Lista de presenças por dia com filtros por data, modalidade, faixa.
        - Estatísticas de frequência por aluno (porcentagem de presença, dias consecutivos, etc).
        - Alertas para alunos com baixa frequência.
        - Gráficos de evolução da frequência ao longo do tempo.
    - **Perfil Completo do Aluno** (3 dias)
        - Informações pessoais (nome, foto, contato, data de início).
        - Histórico de faixas e progressão.
        - Estatísticas de treino (frequência, técnicas aprendidas, tempo de prática).
        - Histórico de presenças detalhado.
        - Notas e comentários do professor sobre o desenvolvimento.
    - **Sistema de Comentários e Avaliação** (2 dias)
        - Professores podem adicionar comentários sobre o desenvolvimento de cada aluno.
        - Avaliação de progresso (excelente, bom, regular, precisa melhorar).
        - Histórico de comentários e avaliações.
        - Notificações para alunos sobre novos comentários.
        - Sistema de tags para categorizar comentários (técnica, comportamento, evolução).
    - **Gestão de Academias e Locais** (2 dias)
        - Cadastro de academias com informações completas.
        - Múltiplos locais de treino por academia.
        - Geração e gerenciamento de QR Codes por local.
        - Configurações de horários de treino por local.
        - Mapa de locais de treino.
    - **Relatórios e Analytics** (3 dias)
        - Relatórios de frequência por período (semanal, mensal, anual).
        - Comparação de desempenho entre alunos.
        - Estatísticas de evolução por faixa e modalidade.
        - Exportação de relatórios em PDF/Excel.
        - Dashboard com métricas principais da academia.
    - **Sistema de Notificações** (2 dias)
        - Notificações push para professores sobre presenças.
        - Alertas de baixa frequência de alunos.
        - Lembretes de avaliações pendentes.
        - Notificações para alunos sobre comentários do professor.
        - Configurações personalizáveis de notificações.
    - **Integração e Segurança** (3 dias)
        - Sistema de permissões (professor, aluno, administrador).
        - Autenticação segura e validação de identidade.
        - Backup e sincronização de dados.
        - Integração com o sistema existente de técnicas e modalidades.
        - Testes de segurança e validação de dados.
    - **Total estimado:** 22 dias

- [ ] **Desenvolvimento Multiplataforma**
    - **Versão Web com Kotlin Multiplatform** (8 dias)
        - Configuração do Kotlin Multiplatform para Android e Web.
        - Compartilhamento de código de negócio (domain, data, use cases).
        - Adaptação da UI para Compose for Web.
        - Implementação de navegação web (URLs, browser history).
        - Adaptação dos componentes de mídia para web (File API, WebRTC).
        - Sistema de autenticação web (OAuth, JWT).
        - Responsividade e design adaptativo para diferentes tamanhos de tela.
        - PWA (Progressive Web App) com funcionalidades offline.
        - Integração com APIs web (REST, GraphQL).
        - Testes específicos para plataforma web.
    - **Otimizações e Performance Web** (3 dias)
        - Lazy loading de imagens e mídia.
        - Cache inteligente para dados e assets.
        - Compressão e otimização de assets.
        - SEO e meta tags para melhor indexação.
        - Analytics web (Google Analytics, eventos customizados).
        - Monitoramento de performance (Core Web Vitals).
    - **Sincronização Multiplataforma** (4 dias)
        - Sistema de sincronização entre Android e Web.
        - Resolução de conflitos de dados.
        - Backup na nuvem (Firebase, AWS, Google Cloud).
        - Sincronização em tempo real (WebSockets, Server-Sent Events).
        - Versionamento de dados e rollback.
        - Indicadores de status de sincronização.
    - **Total estimado:** 15 dias

- [ ] **Versão iOS Nativa** (Futuro Distante)
    - **Desenvolvimento iOS com Swift/SwiftUI** (12 dias)
        - Reescrita completa da UI usando SwiftUI.
        - Adaptação da arquitetura para padrões iOS.
        - Implementação de navegação iOS (NavigationView, TabView).
        - Componentes nativos iOS (Core Data, AVFoundation).
        - Integração com serviços Apple (iCloud, HealthKit).
        - Notificações push nativas (APNs).
        - Widgets para iOS (iOS 14+).
        - Suporte a Apple Watch (opcional).
    - **Integração com Ecossistema Apple** (4 dias)
        - Sincronização com iCloud.
        - Integração com HealthKit para métricas de treino.
        - Suporte a Apple Fitness+.
        - AirDrop para compartilhamento de técnicas.
        - Siri Shortcuts para comandos de voz.
        - Spotlight Search para técnicas.
    - **Otimizações iOS** (3 dias)
        - Performance nativa e otimizações de bateria.
        - Suporte a diferentes tamanhos de iPhone e iPad.
        - Modo escuro nativo.
        - Haptic feedback e animações fluidas.
        - Acessibilidade iOS (VoiceOver, Dynamic Type).
    - **Total estimado:** 19 dias

## 🐛 Problemas conhecidos

### Menores
- [ ] Warnings de deprecação do ArrowBack (não crítico)
- [ ] Variáveis não utilizadas nos previews (não crítico)
- [ ] Shadowing de variáveis na navegação (corrigido)

### Maiores
- ✅ Gravação de áudio não funcionava (corrigido - implementado MediaRecorder nativo)

## 📊 Métricas de Progresso

- **Foundation**: 100% ✅
- **Core Features**: 100% ✅
- **UI Components**: 100% ✅
- **Navegação**: 100% ✅
- **CRUD Operations**: 100% ✅
- **Funcionalidades de Mídia**: 100% ✅
- **Testes**: 0% ⏳
- **Funcionalidades Avançadas**: 0% ⏳
- **Funcionalidades Sociais**: 0% ⏳
- **Sistema de Gestão para Professores**: 0% ⏳
- **Desenvolvimento Multiplataforma**: 0% ⏳
- **Versão iOS**: 0% ⏳

**Progresso Geral**: 75% ✅

## 🎯 Próximos Milestones

### Milestone 1: Testes (3 dias)
- Implementar testes unitários básicos
- Implementar testes de UI
- Implementar testes de navegação

### Milestone 2: Polimento (2 dias)
- Corrigir warnings
- Melhorar UX
- Finalizar documentação

## 📝 Notas Técnicas

### Arquitetura
- Clean Architecture + MVVM
- Jetpack Compose para UI
- Room para persistência
- Koin para DI
- Navigation Compose para navegação

### Performance
- LazyColumn para listas grandes
- ViewModels para gerenciamento de estado
- Flow para reatividade
- Coroutines para operações assíncronas

### Qualidade
- Código limpo e bem documentado
- Separação de responsabilidades
- Componentes reutilizáveis
- Previews para documentação visual

### Navegação
- Navigation Compose 2.7.7
- Rotas type-safe com argumentos
- NavHost centralizado
- Callbacks de navegação
- Padrões do Google seguidos

### Mídia
- Captura de foto/vídeo/áudio
- Armazenamento local seguro
- Gerenciamento de permissões
- Componentes reutilizáveis
- Preview em tempo real
- ExoPlayer para reprodução
- Coil para carregamento de imagens

## 🔧 Arquivos de Mídia Criados

### Componentes de Mídia
- `MediaCapture.kt` - Captura de mídia completa
- `MediaCaptureButton.kt` - Botão de captura
- `MediaDisplay.kt` - Exibição de mídia
- `MediaImageView.kt` - Exibição de imagens
- `MediaPlayer.kt` - Reprodução de vídeo/áudio

### Gerenciadores de Mídia
- `MediaManager.kt` - Gerenciamento de arquivos
- `PermissionManager.kt` - Gerenciamento de permissões

### Repositórios e Use Cases
- `MediaRepository.kt` - Interface do repositório
- `MediaRepositoryImpl.kt` - Implementação do repositório
- `SaveMediaFileUseCase.kt` - Salvar arquivos
- `DeleteMediaFileUseCase.kt` - Excluir arquivos
- `GetMediaUriUseCase.kt` - Obter URIs

### Drawables
- `ic_camera_alt.xml` - Ícone de câmera
- `ic_photo_library.xml` - Ícone de galeria de fotos
- `ic_videocam.xml` - Ícone de câmera de vídeo
- `ic_video_library.xml` - Ícone de galeria de vídeos
- `ic_audio_file.xml` - Ícone de arquivo de áudio

### Telas Atualizadas
- `TechniqueFormScreen.kt` - Integração com captura de mídia
- `TechniqueDetailScreen.kt` - Exibição de mídia
- `TechniqueFormViewModel.kt` - Gerenciamento de estado de mídia
- `TechniqueDetailViewModel.kt` - Carregamento de URIs de mídia

## 📋 Checklist de Mídia

### Implementação
- [x] Componentes de captura de mídia
- [x] Gerenciamento de permissões
- [x] Armazenamento local de arquivos
- [x] Reprodução de mídia
- [x] Preview de mídia capturada
- [x] Integração com formulários
- [x] Ícones customizados
- [x] Tratamento de erros

### Funcionalidades
- [x] Captura de foto via câmera
- [x] Captura de foto via galeria
- [x] Captura de vídeo via câmera
- [x] Captura de vídeo via galeria
- [x] Gravação de áudio via microfone
- [x] Seleção de áudio via galeria
- [x] Visualização de fotos
- [x] Reprodução de vídeos
- [x] Reprodução de áudio
- [x] Remoção de mídia
- [x] Gerenciamento de permissões

---

*Este documento rastreia o progresso geral do projeto Lutando.* 