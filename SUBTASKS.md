# Subtasks - Implementação Gradual de Bibliotecas

## Status Atual
✅ **ROLLBACK COMPLETO** - Projeto com dependências essenciais apenas (Core Android + Jetpack Compose)
✅ **REFERÊNCIAS KOIN REMOVIDAS** - Todas as referências ao Koin foram comentadas/removidas
✅ **MODELOS SIMPLIFICADOS** - Modelos sem anotações do Room
✅ **ROOM E GOOGLE COMENTADOS** - Todos os arquivos que dependem de Room e Gson foram comentados

## Subtasks para Implementação Gradual

### 1. Navigation Compose
**Prioridade:** ALTA
**Status:** ⏳ PENDENTE
**Descrição:** Implementar navegação entre telas usando Navigation Compose
**Dependências:**
- androidx.navigation.compose

**Tarefas:**
- [ ] Adicionar dependência no libs.versions.toml
- [ ] Adicionar dependência no build.gradle.kts
- [ ] Configurar NavHost no MainActivity
- [ ] Criar rotas para as telas principais
- [ ] Implementar navegação básica

---

### 2. ViewModel + Lifecycle
**Prioridade:** ALTA
**Status:** ✅ IMPLEMENTADO (versão simplificada)
**Descrição:** Implementar ViewModel para gerenciamento de estado
**Dependências:**
- androidx.lifecycle.viewmodel.compose
- kotlinx.coroutines.android

**Tarefas:**
- [x] Criar ViewModel base (HomeViewModel)
- [x] Implementar ViewModel para HomeScreen
- [x] Conectar ViewModel com UI
- [ ] Adicionar dependências no libs.versions.toml
- [ ] Adicionar dependências no build.gradle.kts
- [ ] Implementar injeção de dependência

---

### 3. Room Database
**Prioridade:** ALTA
**Status:** ⏳ PENDENTE (arquivos comentados)
**Descrição:** Implementar banco de dados local para persistência
**Dependências:**
- androidx.room.runtime
- androidx.room.ktx
- androidx.room.compiler (KSP)
- ksp plugin

**Tarefas:**
- [ ] Adicionar dependências no libs.versions.toml
- [ ] Adicionar plugin KSP no build.gradle.kts
- [ ] Adicionar dependências no build.gradle.kts
- [ ] Descomentar arquivos do Room
- [ ] Adicionar anotações @Entity nos modelos
- [ ] Criar DAOs
- [ ] Criar Database
- [ ] Implementar Repository pattern

---

### 4. Koin Dependency Injection
**Prioridade:** MÉDIA
**Status:** ⏳ PENDENTE (arquivo comentado)
**Descrição:** Implementar injeção de dependência com Koin
**Dependências:**
- koin-android
- koin-androidx-compose

**Tarefas:**
- [ ] Adicionar dependências no libs.versions.toml
- [ ] Adicionar dependências no build.gradle.kts
- [ ] Descomentar KoinModule.kt
- [ ] Configurar Koin no Application
- [ ] Criar módulos de injeção
- [ ] Injetar dependências nas telas

---

### 5. Coroutines para Operações Assíncronas
**Prioridade:** MÉDIA
**Status:** ✅ IMPLEMENTADO (básico)
**Descrição:** Implementar operações assíncronas com Coroutines
**Dependências:**
- kotlinx.coroutines.android (já incluído no ViewModel)

**Tarefas:**
- [x] Implementar operações assíncronas no ViewModel
- [x] Usar Coroutines no ViewModel
- [ ] Implementar operações assíncronas no Repository
- [ ] Tratar erros com try-catch
- [ ] Implementar loading states

---

### 6. Image Loading (Coil)
**Prioridade:** BAIXA
**Status:** ⏳ PENDENTE
**Descrição:** Implementar carregamento de imagens
**Dependências:**
- coil-compose

**Tarefas:**
- [ ] Adicionar dependência no libs.versions.toml
- [ ] Adicionar dependência no build.gradle.kts
- [ ] Implementar AsyncImage para carregar imagens
- [ ] Configurar placeholders e error states

---

### 7. Camera e Media
**Prioridade:** BAIXA
**Status:** ⏳ PENDENTE
**Descrição:** Implementar funcionalidades de câmera e mídia
**Dependências:**
- androidx.camera.camera2
- androidx.camera.lifecycle
- androidx.camera.view
- androidx.media3.exoplayer
- androidx.media3.ui

**Tarefas:**
- [ ] Adicionar dependências no libs.versions.toml
- [ ] Adicionar dependências no build.gradle.kts
- [ ] Implementar captura de fotos
- [ ] Implementar gravação de vídeos
- [ ] Implementar player de vídeo
- [ ] Solicitar permissões necessárias

---

### 8. DataStore para Preferências
**Prioridade:** BAIXA
**Status:** ⏳ PENDENTE
**Descrição:** Implementar armazenamento de preferências
**Dependências:**
- androidx.datastore.preferences

**Tarefas:**
- [ ] Adicionar dependência no libs.versions.toml
- [ ] Adicionar dependência no build.gradle.kts
- [ ] Criar DataStore para configurações
- [ ] Implementar persistência de preferências do usuário

---

### 9. JSON Serialization (Gson)
**Prioridade:** BAIXA
**Status:** ⏳ PENDENTE (arquivo comentado)
**Descrição:** Implementar serialização JSON
**Dependências:**
- gson

**Tarefas:**
- [ ] Adicionar dependência no libs.versions.toml
- [ ] Adicionar dependência no build.gradle.kts
- [ ] Descomentar Converters.kt
- [ ] Implementar serialização de objetos
- [ ] Implementar deserialização de JSON

---

### 10. Testes Avançados
**Prioridade:** BAIXA
**Status:** ⏳ PENDENTE
**Descrição:** Implementar testes unitários e de UI
**Dependências:**
- mockito-core
- mockito-kotlin
- turbine
- kotlinx.coroutines.test

**Tarefas:**
- [ ] Adicionar dependências no libs.versions.toml
- [ ] Adicionar dependências no build.gradle.kts
- [ ] Implementar testes unitários para ViewModels
- [ ] Implementar testes para Repositories
- [ ] Implementar testes de UI com Compose

---

## Ordem de Implementação Recomendada

1. **Navigation Compose** - Base para navegação
2. **ViewModel + Lifecycle** - ✅ JÁ IMPLEMENTADO (básico)
3. **Room Database** - Persistência de dados
4. **Koin DI** - Injeção de dependência
5. **Coroutines** - ✅ JÁ IMPLEMENTADO (básico)
6. **Image Loading** - Carregamento de imagens
7. **Camera e Media** - Funcionalidades multimídia
8. **DataStore** - Preferências
9. **JSON Serialization** - Serialização
10. **Testes Avançados** - Qualidade do código

## Comandos para Executar

Para implementar cada subtask, use:
```
/execute
```

E especifique qual subtask deseja implementar.

## Verificação de Build

Após cada implementação, execute:
```bash
./gradlew clean build
```

Para verificar se não há erros de compilação.

## Próximo Passo Recomendado

**Implementar Navigation Compose** - É a próxima dependência essencial para permitir navegação entre telas.

## Status do Rollback

✅ **COMPLETO** - O projeto agora está limpo e deve compilar sem erros. Todas as dependências problemáticas foram comentadas e podem ser reativadas gradualmente conforme as subtasks forem implementadas. 