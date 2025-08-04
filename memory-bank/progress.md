# Progress: Lutando
*Version: 1.4*
*Created: 2025-01-27*
*Last Updated: 2025-08-01*

## 🏁 Resumo das Entregas Concluídas

- **Foundation:** Clean Architecture, MVVM, Jetpack Compose, Room, Koin, Material Design 3.
- **Core Features:** Modelos de dados (User, MartialArt, Technique), DAOs, repositórios, use cases, ViewModels reativos, dados iniciais.
- **UI Components:** HomeScreen, MartialArtDetailScreen, TechniqueDetailScreen, TechniqueFormScreen, MartialArtCard, Previews.
- **Navegação:** Navigation Compose, rotas, argumentos, callbacks, MainActivity, NavRoutes, LutandoNavigation, telas e ViewModels integrados.
- **CRUD:** Criar, ler, atualizar e deletar técnicas.
- **Funcionalidades de Mídia:** Captura de foto/vídeo/áudio, armazenamento local, visualização, permissões, componentes reutilizáveis, integração com formulários, preview, remoção, ícones customizados, MediaRecorder, seleção de áudio.
- **Funcionalidade de Comentários:** Adicionar, visualizar, editar e deletar comentários em técnicas.

## ✅ O que está funcionando

### Foundation (100% Concluído)
- ✅ Clean Architecture implementada
- ✅ MVVM pattern implementado
- ✅ Jetpack Compose configurado
- ✅ Room Database configurado
- ✅ Koin para injeção de dependência
- ✅ Material Design 3 implementado

### Core Features (100% Concluído)
- ✅ Entidades de dados (User, MartialArt, Technique, Comment)
- ✅ DAOs para acesso a dados
- ✅ Repositórios implementados
- ✅ Use Cases criados
- ✅ ViewModels com estado reativo
- ✅ Dados iniciais (8 modalidades de artes marciais)

### UI Components (100% Concluído)
- ✅ HomeScreen - Lista de modalidades
- ✅ MartialArtDetailScreen - Lista de técnicas da modalidade
- ✅ TechniqueDetailScreen - Detalhes da técnica com seção de comentários
- ✅ TechniqueFormScreen - Formulário para adicionar/editar técnicas
- ✅ Componentes de Comentários (Card, Input, Dialogs)
- ✅ MartialArtCard - Componente reutilizável
- ✅ Previews do Jetpack Compose implementados

### Navegação (100% Concluído)
- ✅ Navigation Compose 2.7.7 implementado
- ✅ Navegação completa entre todas as telas

### CRUD Operations (100% Concluído)
- ✅ CRUD completo de técnicas
- ✅ CRUD completo de comentários

### Funcionalidades de Mídia (100% Concluído)
- ✅ Captura, armazenamento e visualização de fotos, vídeos e áudios para técnicas.

## 🔄 O que está em desenvolvimento

### Testes (30% Concluído)
- [x] Testes unitários (modelos, use cases, conversores, ViewModels principais)
- [ ] Testes de UI
- [ ] Testes de navegação
- [ ] Testes de integração

## 📋 Backlog

### Funcionalidades Avançadas
- [ ] **Busca de técnicas** (1 dia)
- [ ] **Filtros por modalidade** (1 dia)
- [ ] **Ordenação de técnicas** (1 dia)
- [ ] **Exportação de dados** (2 dias)
- [ ] **Sistema de Backup e Restore** (3 dias)
    - **Resumo:** Backup completo do banco de dados (técnicas, comentários, mídia, configurações), múltiplos formatos de exportação, backup automático e manual, restauração com validação, backup na nuvem (Google Drive, etc.), histórico e notificações.
    - **Componentes a Serem Criados:** `BackupManager`, `BackupRepository`, `CreateBackupUseCase`, `RestoreBackupUseCase`, `CloudBackupManager`, `GoogleDriveBackupManager`, etc.
    - **Componentes de UI:** `BackupSettingsScreen`, `BackupHistoryScreen`, `CloudBackupSetupScreen`, etc.
    - **Integração:** WorkManager para jobs em background, Google Drive API, permissões.

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

- [ ] **Análise de Progresso com IA (Gemini/ChatGPT)** (5-7 dias)
    - **Resumo:** Integrar um modelo de linguagem (Gemini ou ChatGPT) para fornecer feedback inteligente ao usuário. A funcionalidade exigirá login (Google Sign-In) para associar o uso da API ao usuário.
    - **Análise de Anotações:** A IA analisará as anotações de uma técnica e fornecerá insights, corrigindo possíveis erros conceituais e sugerindo melhorias.
    - **Feedback de Evolução:** Com base no histórico de uso, nível de domínio e anotações, a IA fornecerá um relatório de progresso, destacando pontos fortes e áreas para focar.
    - **Componentes a Serem Criados:** `GeminiAuthManager`, `ChatGPTAuthManager`, `AnalysisRepository`, `AnalyzeTechniqueUseCase`, `GetProgressFeedbackUseCase`.
    - **Componentes de UI:** `AnalysisScreen`, `FeedbackCard`, `LoginScreen` (com opção de Google Sign-In).
    - **Integração:** Google Sign-In para autenticação, Gemini API e/ou ChatGPT API.

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

## 🐛 Problemas conhecidos
- [ ] Warnings de deprecação do ArrowBack (não crítico)
- [ ] Variáveis não utilizadas nos previews (não crítico)

## 📊 Métricas de Progresso
- **Foundation**: 100% ✅
- **Core Features**: 100% ✅
- **UI Components**: 100% ✅
- **Navegação**: 100% ✅
- **CRUD Operations**: 100% ✅
- **Funcionalidades de Mídia**: 100% ✅
- **Migração para Firebase**: 0% ⏳
- **Desenvolvimento Web**: 0% ⏳
- **Mídia nos Comentários**: 0% ⏳
- **Sistema de Backup e Restore**: 0% ⏳
- **Testes**: 30% ⏳
- **Funcionalidades Avançadas**: 0% ⏳
- **Funcionalidades Sociais**: 0% ⏳
- **Sistema de Gestão para Professores**: 0% ⏳
- **Versão iOS**: 0% ⏳

**Progresso Geral**: ~65% ✅

## 🎯 Próximos Milestones

### Milestone 1: Migrar para Firebase (5-7 dias)
- [x] **Etapa 1: Configuração do Projeto Firebase** ✅
    - [x] Adicionar as dependências do Firebase (Firestore, Storage, Auth) ao arquivo `app/build.gradle.kts`.
    - [x] Criar um projeto no console do Firebase e baixar o arquivo `google-services.json`, adicionando-o ao diretório `app/`.
    - [x] Inicializar o Firebase na classe `LutandoApplication`.
- [x] **Etapa 2: Implementar Autenticação de Usuários** ✅
    - [x] Configurar e implementar a autenticação anônima inicial.
    - [ ] (Futuro) Implementar tela de login/registro com E-mail e Senha.
    - [ ] (Futuro) Criar fluxo para vincular conta anônima a uma conta permanente.
- [ ] **Etapa 3: Refatorar a Camada de Dados (Repositórios)**
    - [ ] Criar novas implementações dos repositórios para usar o Firestore.
    - [ ] Adaptar `Flow` para `snapshotFlow()` do Firestore para manter a reatividade.
- [ ] **Etapa 4: Migrar Armazenamento de Mídia para o Firebase Storage**
    - [ ] Modificar o `MediaRepository` para fazer upload/download para o Firebase Storage.
    - [ ] Armazenar as URLs de download do Storage no Firestore.
- [ ] **Etapa 5: Atualizar a Injeção de Dependência (Koin)**
    - [ ] Substituir as injeções do Room pelas do Firebase.
- [ ] **Etapa 6: Definir Regras de Segurança**
    - [ ] Criar regras no console do Firebase para garantir que usuários só acessem seus próprios dados.
- [ ] **Etapa 7: (Opcional) Criar Script de Migração de Dados**
    - [ ] Desenvolver uma função para migrar dados do Room local para o Firestore.
- [ ] **Etapa 8: Limpeza do Código Antigo**
    - [ ] Remover dependências e classes do Room após a migração.

### Milestone 2: Desenvolvimento Multiplataforma (Web) (8-10 dias)
- [ ] **Etapa 1: Configuração do Projeto Kotlin Multiplatform**
    - [ ] Estruturar o projeto para compartilhar código (commonMain, androidMain, wasmJsMain).
    - [ ] Configurar o build Gradle para compilar para Android e Web (WASM).
- [ ] **Etapa 2: Adaptação da UI com Compose for Web**
    - [ ] Garantir que os componentes Compose sejam compatíveis com a web.
    - [ ] Implementar a navegação para a web (gerenciamento de rotas/URLs).
- [ ] **Etapa 3: Configuração do Domínio e Hospedagem**
    - [ ] Registrar o domínio `alunando.com`.
    - [ ] Configurar o GitHub Pages para hospedar o site.
    - [ ] Apontar o DNS do domínio para o GitHub Pages.
- [ ] **Etapa 4: Implementar CI/CD com GitHub Actions**
    - [ ] Criar um workflow para fazer o build e deploy automático da versão web a cada push.

### Milestone 3: Versão iOS Nativa (Futuro Distante)
- [ ] Reescrita da UI com SwiftUI.
- [ ] Adaptação da arquitetura para padrões iOS.
- [ ] Integração com serviços Apple (iCloud, etc.).

## 📝 Notas Técnicas

### Arquitetura
- Clean Architecture + MVVM
- Jetpack Compose para UI (será multiplataforma)
- Room para persistência (será migrado para Firebase)
- Koin para DI
- Navigation Compose para navegação

---
*Este documento rastreia o progresso geral do projeto Lutando.*
