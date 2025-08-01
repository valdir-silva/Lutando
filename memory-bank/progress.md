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
- [ ] **Sistema de Backup e Restore** (3 dias)
    - Backup completo do banco de dados (técnicas, comentários, mídia, configurações).
    - Múltiplos formatos de exportação (.json, .csv, .db, .zip).
    - Backup automático programado (diário, semanal, mensal).
    - Backup manual com opções de compressão.
    - Restauração de backup com validação de integridade.
    - Backup na nuvem (Google Drive, Dropbox, OneDrive).
    - Histórico de backups com informações de data/tamanho.
    - Configurações de backup (frequência, local, compressão).
    - Notificações de backup bem-sucedido/falhado.
    - Backup incremental para economizar espaço.

### Melhorias de UX
- [ ] Animações de transição (1 dia)
- [ ] Feedback tátil (0,5 dia)
- [ ] Suporte a temas escuro/claro (1 dia)
- [ ] Acessibilidade (1 dia)
- [ ] Internacionalização (2 dias)

### Funcionalidades Futuras

- [ ] **Possibilidade de compartilhar sua técnica** (2 dias)
    - comparar suas anotacoes de uma tecnica com as anotacoes de outra pessoa da mesma
    - comentar na postagem da tecnica de outra pessoa, dar like
    - puxar comentario, video, foto... de uma tecnica de outra pessoa pra vc (manter a informacao do usuario original)

- [x] **Possibilidade de comentar dentro de uma técnica criada** (2 dias) ✅
    - Cada técnica terá uma seção de comentários, armazenados localmente.
    - Campos: texto do comentário, autor (multiusuário), data/hora.
    - UI: exibição e adição de comentários na tela de detalhes da técnica.
    - Permitir editar/deletar comentários próprios.
    - (Futuro) Notificações locais para novos comentários.

- [ ] **Mídia nos comentários (fotos, vídeos e áudios)** (3 dias)
    - Permitir anexar fotos, vídeos e áudios aos comentários.
    - Captura de mídia via câmera/microfone ou seleção da galeria.
    - Visualização de mídia nos comentários (fotos, reprodução de vídeo/áudio).
    - Armazenamento local de arquivos de mídia dos comentários.
    - Gerenciamento de permissões para captura de mídia.
    - Componentes reutilizáveis para captura e exibição de mídia nos comentários.
    - Preview de mídia antes de enviar o comentário.
    - Remoção de mídia dos comentários.
    - Limite de tamanho de arquivo e compressão automática.
    - Ícones indicativos do tipo de mídia anexada.

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

- [ ] **Exportação de dados para compartilhamento** (1 dia)
    - Exportação de técnicas específicas para compartilhamento.
    - Formato de arquivo otimizado para transferência (.lutando).
    - Compressão de mídia para reduzir tamanho do arquivo.
    - Validação de integridade do arquivo exportado.
    - Importação de técnicas de outros usuários.
    - Preview do conteúdo antes da importação.

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
- ✅ **BUG CRÍTICO: Botão de adicionar modalidade não funciona** - Corrigido: implementada tela de formulário para adicionar modalidades, use case, ViewModel e navegação
- ✅ **BUG CRÍTICO: Comentários não estão sendo salvos nem mostrados** - Corrigido: Migração do banco de dados ajustada e chave estrangeira (ForeignKey) implementada para garantir a integridade dos dados.
- ✅ **BUG CRÍTICO: App travava em "loading" ao salvar** - Corrigido: Refatorada a inicialização do banco de dados no Koin para eliminar condição de corrida que causava deadlock.
- ✅ **BUG CRÍTICO: App quebrava ao abrir detalhes da técnica** - Corrigido: Layout da tela de detalhes refatorado para usar um único LazyColumn, resolvendo conflito de scroll aninhado (`IllegalStateException`).

## 📊 Métricas de Progresso

- **Foundation**: 100% ✅
- **Core Features**: 100% ✅
- **UI Components**: 100% ✅
- **Navegação**: 100% ✅
- **CRUD Operations**: 100% ✅
- **Funcionalidades de Mídia**: 100% ✅
- **Mídia nos Comentários**: 0% ⏳
- **Sistema de Backup e Restore**: 0% ⏳
- **Testes**: 0% ⏳
- **Funcionalidades Avançadas**: 10% ⏳
- **Funcionalidades Sociais**: 0% ⏳
- **Sistema de Gestão para Professores**: 0% ⏳
- **Desenvolvimento Multiplataforma**: 0% ⏳
- **Versão iOS**: 0% ⏳

**Progresso Geral**: 77% ✅

## 🎯 Próximos Milestones

### Milestone 1: Migrar para Firebase (5-7 dias)
- [ ] **Migrar persistência de dados do Room para Firebase**
    - Configurar projeto Firebase e adicionar SDKs ao app.
    - Implementar autenticação de usuários com Firebase Auth.
    - Refatorar camada de dados (Repositórios) para usar Firestore em vez de Room.
    - Migrar modelos de dados para serem compatíveis com Firestore.
    - Implementar upload/download de arquivos de mídia para o Firebase Storage.
    - Adaptar Use Cases e ViewModels para a nova fonte de dados assíncrona.
    - Criar regras de segurança para Firestore e Storage.
    - (Opcional) Criar um script de migração para mover dados existentes do Room para o Firestore.

### Milestone 2: Mídia nos Comentários (3 dias)
- Implementar captura de mídia nos comentários
- Integrar componentes de mídia existentes com comentários
- Atualizar modelo de dados e banco de dados
- Implementar visualização de mídia nos comentários

### Milestone 3: Sistema de Backup e Restore (3 dias)
- Implementar backup completo do banco de dados
- Criar sistema de backup automático
- Implementar restauração de backup
- Integrar backup na nuvem (Google Drive)
  - Configurar Google Drive API v3
  - Implementar autenticação OAuth 2.0
  - Criar sistema de upload/download automático
  - Implementar sincronização bidirecional
  - Adicionar detecção e resolução de conflitos

### Milestone 4: Testes (3 dias)
- Implementar testes unitários básicos
- Implementar testes de UI
- Implementar testes de navegação

### Milestone 5: Polimento (2 dias)
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

## 🔧 Sistema de Backup e Restore (Planejado)

### Componentes a Serem Criados
- `BackupManager.kt` - Gerenciador principal de backup
- `BackupRepository.kt` - Interface do repositório de backup
- `BackupRepositoryImpl.kt` - Implementação do repositório de backup
- `CreateBackupUseCase.kt` - Criar backup completo
- `RestoreBackupUseCase.kt` - Restaurar backup
- `ValidateBackupUseCase.kt` - Validar integridade do backup
- `ScheduleBackupUseCase.kt` - Agendar backup automático
- `CloudBackupManager.kt` - Gerenciamento de backup na nuvem
- `GoogleDriveBackupManager.kt` - Gerenciamento específico do Google Drive
- `GoogleDriveAuthManager.kt` - Autenticação com Google Drive
- `GoogleDriveSyncUseCase.kt` - Sincronização com Google Drive
- `GoogleDriveUploadUseCase.kt` - Upload de backup para Google Drive
- `GoogleDriveDownloadUseCase.kt` - Download de backup do Google Drive
- `GoogleDriveConflictResolver.kt` - Resolução de conflitos no Google Drive

### Componentes de UI a Serem Criados
- `BackupSettingsScreen.kt` - Tela de configurações de backup
- `BackupHistoryScreen.kt` - Histórico de backups
- `BackupProgressDialog.kt` - Progresso do backup/restore
- `RestoreConfirmationDialog.kt` - Confirmação de restauração
- `CloudBackupSetupScreen.kt` - Configuração de backup na nuvem
- `GoogleDriveSetupScreen.kt` - Configuração específica do Google Drive
- `GoogleDriveAuthScreen.kt` - Tela de autenticação com Google
- `GoogleDriveBackupListScreen.kt` - Lista de backups no Google Drive
- `GoogleDriveSyncStatusCard.kt` - Status de sincronização com Google Drive
- `GoogleDriveConflictDialog.kt` - Diálogo para resolver conflitos
- `GoogleDriveSettingsCard.kt` - Configurações do Google Drive

### Integração Planejada
- Banco de dados atualizado com tabela de histórico de backups
- Koin atualizado com dependências de backup
- WorkManager para backup automático em background
- Integração com Google Drive API, Dropbox API, OneDrive API
- Permissões de armazenamento e internet
- Notificações para status de backup

### Integração Google Drive (Detalhada)
- **Google Drive API v3** para upload/download de arquivos
- **Google Sign-In** para autenticação do usuário
- **Pasta dedicada "Lutando Backups"** no Google Drive do usuário
- **Versionamento automático** de backups (backup_YYYY-MM-DD_HH-MM.zip)
- **Sincronização bidirecional** entre app e Google Drive
- **Detecção de conflitos** e resolução automática
- **Backup incremental** para economizar espaço na nuvem
- **Configuração de frequência** (diário, semanal, mensal)
- **Notificações push** sobre status de backup na nuvem
- **Restauração seletiva** de backups específicos do Google Drive
- **Limpeza automática** de backups antigos na nuvem
- **Criptografia opcional** de backups sensíveis
- **Compressão inteligente** para reduzir tamanho de upload
- **Retry automático** em caso de falha de conexão
- **Indicador de status** de sincronização com Google Drive

### Funcionalidades Planejadas
- [ ] Backup completo do banco de dados
- [ ] Backup de arquivos de mídia (fotos, vídeos, áudios)
- [ ] Backup de configurações do usuário
- [ ] Múltiplos formatos de exportação (.json, .csv, .db, .zip)
- [ ] Backup automático programado (diário, semanal, mensal)
- [ ] Backup manual com opções de compressão
- [ ] Restauração de backup com validação de integridade
- [ ] Backup na nuvem (Google Drive, Dropbox, OneDrive)
- [ ] Histórico de backups com informações de data/tamanho
- [ ] Configurações de backup (frequência, local, compressão)
- [ ] Notificações de backup bem-sucedido/falhado
- [ ] Backup incremental para economizar espaço
- [ ] Criptografia de backups sensíveis
- [ ] Limpeza automática de backups antigos

### Funcionalidades Específicas Google Drive
- [ ] Autenticação OAuth 2.0 com Google Sign-In
- [ ] Criação automática da pasta "Lutando Backups" no Google Drive
- [ ] Upload automático de backups para Google Drive
- [ ] Download de backups do Google Drive para restauração
- [ ] Sincronização bidirecional em tempo real
- [ ] Detecção e resolução de conflitos de versão
- [ ] Backup incremental para economizar espaço na nuvem
- [ ] Configuração de frequência de backup (diário, semanal, mensal)
- [ ] Notificações push sobre status de sincronização
- [ ] Restauração seletiva de backups específicos
- [ ] Limpeza automática de backups antigos na nuvem
- [ ] Criptografia opcional de backups sensíveis
- [ ] Compressão inteligente para reduzir tamanho de upload
- [ ] Retry automático em caso de falha de conexão
- [ ] Indicador visual de status de sincronização
- [ ] Configuração de backup apenas via Wi-Fi
- [ ] Backup em background com WorkManager
- [ ] Histórico detalhado de sincronizações
- [ ] Configuração de tamanho máximo de backup
- [ ] Backup seletivo (apenas dados, dados + mídia, completo)

### Formatos de Backup
- **JSON**: Estrutura de dados legível e portável
- **CSV**: Compatível com planilhas e análise de dados
- **DB**: Cópia direta do banco SQLite
- **ZIP**: Arquivo compactado com dados + mídia
- **LUTANDO**: Formato proprietário otimizado

### Estratégias de Backup
- **Completo**: Todos os dados + mídia
- **Incremental**: Apenas mudanças desde último backup
- **Diferencial**: Mudanças desde backup completo
- **Seletivo**: Apenas dados específicos (técnicas, comentários, etc.)

### Configuração Google Drive
- **Google Drive API v3** - Biblioteca oficial do Google
- **Google Sign-In** - Autenticação OAuth 2.0
- **WorkManager** - Backup em background
- **Room Database** - Armazenamento local de metadados
- **Koin** - Injeção de dependência
- **Coroutines** - Operações assíncronas
- **Flow** - Reatividade para status de sincronização

### Permissões Necessárias
- `INTERNET` - Conexão com Google Drive API
- `ACCESS_NETWORK_STATE` - Verificar conectividade
- `WRITE_EXTERNAL_STORAGE` - Backup local (Android < 11)
- `READ_EXTERNAL_STORAGE` - Restauração local (Android < 11)
- `FOREGROUND_SERVICE` - Backup em background
- `RECEIVE_BOOT_COMPLETED` - Backup automático após reinicialização

## 🔧 Funcionalidade de Mídia nos Comentários (Planejada)

### Componentes a Serem Criados
- `CommentMedia.kt` - Modelo de dados para mídia de comentários
- `CommentMediaDao.kt` - DAO para operações de mídia de comentários
- `CommentMediaRepository.kt` - Interface do repositório de mídia
- `CommentMediaRepositoryImpl.kt` - Implementação do repositório de mídia
- `AddCommentMediaUseCase.kt` - Adicionar mídia ao comentário
- `DeleteCommentMediaUseCase.kt` - Remover mídia do comentário
- `GetCommentMediaUseCase.kt` - Buscar mídia de comentários

### Componentes de UI a Serem Criados
- `CommentMediaCapture.kt` - Captura de mídia para comentários
- `CommentMediaDisplay.kt` - Exibição de mídia nos comentários
- `CommentMediaPreview.kt` - Preview de mídia antes de enviar
- `CommentMediaButton.kt` - Botão para adicionar mídia ao comentário

### Integração Planejada
- Banco de dados atualizado com tabela de mídia de comentários
- Koin atualizado com dependências de mídia de comentários
- CommentCard atualizado para exibir mídia
- CommentInput atualizado com opção de captura de mídia
- CommentRepository atualizado para gerenciar mídia

### Funcionalidades Planejadas
- [ ] Anexar fotos aos comentários
- [ ] Anexar vídeos aos comentários
- [ ] Anexar áudios aos comentários
- [ ] Visualizar mídia nos comentários
- [ ] Preview de mídia antes de enviar
- [ ] Remover mídia dos comentários
- [ ] Compressão automática de arquivos
- [ ] Limite de tamanho de arquivo
- [ ] Ícones indicativos do tipo de mídia

## 🔧 Funcionalidade de Comentários Implementada

### Componentes Criados
- `Comment.kt` - Modelo de dados para comentários
- `CommentDao.kt` - DAO para operações de banco de dados
- `CommentRepository.kt` - Interface do repositório
- `CommentRepositoryImpl.kt` - Implementação do repositório
- `GetCommentsByTechniqueUseCase.kt` - Buscar comentários
- `AddCommentUseCase.kt` - Adicionar comentário
- `UpdateCommentUseCase.kt` - Atualizar comentário
- `DeleteCommentUseCase.kt` - Deletar comentário

### Componentes de UI
- `CommentCard.kt` - Exibição de comentário individual
- `CommentInput.kt` - Campo para adicionar novo comentário
- `EditCommentDialog.kt` - Diálogo para editar comentário
- `DeleteCommentDialog.kt` - Diálogo de confirmação para deletar

### Integração
- Banco de dados atualizado (versão 2) com tabela de comentários
- Koin atualizado com todas as dependências
- TechniqueDetailViewModel atualizado com funcionalidades de comentários
- TechniqueDetailScreen atualizada com seção de comentários
- Teste unitário criado para AddCommentUseCase

### Funcionalidades
- ✅ Adicionar comentários a técnicas
- ✅ Visualizar comentários ordenados por data (mais recentes primeiro)
- ✅ Editar comentários próprios
- ✅ Deletar comentários próprios
- ✅ Contagem de comentários
- ✅ Formatação de data em português
- ✅ Interface responsiva e intuitiva

---

*Este documento rastreia o progresso geral do projeto Lutando.* 