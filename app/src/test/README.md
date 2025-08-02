# Testes Unitários - Lutando

Este diretório contém todos os testes unitários do aplicativo Lutando, organizados por camadas da arquitetura.

## Estrutura dos Testes

```
test/
├── java/com/example/lutando/
│   ├── TestSuite.kt                           # Suíte principal de testes
│   ├── domain/
│   │   ├── model/
│   │   │   ├── MartialArtTest.kt             # Testes do modelo MartialArt
│   │   │   ├── TechniqueTest.kt              # Testes do modelo Technique
│   │   │   └── UserTest.kt                   # Testes do modelo User
│   │   └── usecase/
│   │       ├── GetAllMartialArtsUseCaseTest.kt
│   │       ├── GetTechniquesByMartialArtUseCaseTest.kt
│   │       ├── GetCurrentUserUseCaseTest.kt
│   │       ├── SaveMediaFileUseCaseTest.kt
│   │       ├── DeleteMediaFileUseCaseTest.kt
│   │       └── GetMediaUriUseCaseTest.kt
│   ├── presentation/screens/
│   │   ├── home/
│   │   │   └── HomeViewModelTest.kt          # Testes do HomeViewModel
│   │   └── martial_art_detail/
│   │       └── MartialArtDetailViewModelTest.kt
│   └── data/local/
│       └── ConvertersTest.kt                 # Testes dos conversores Room
```

## Dependências de Teste

Os testes utilizam as seguintes bibliotecas:

- **JUnit 4**: Framework de testes
- **MockK**: Biblioteca para criação de mocks
- **Kotlinx Coroutines Test**: Utilitários para testar coroutines
- **Turbine**: Biblioteca para testar Flows

## Como Executar os Testes

### Executar todos os testes
```bash
./gradlew test
```

### Executar uma classe específica
```bash
./gradlew test --tests "alunando.lutando.domain.model.MartialArtTest"
```

### Executar uma suíte específica
```bash
./gradlew test --tests "alunando.lutando.TestSuite"
```

### Executar testes com relatório de cobertura
```bash
./gradlew testDebugUnitTestCoverage
```

## Cobertura de Testes

### Modelos de Domínio (100% cobertura)
- ✅ **MartialArt**: Testa criação, cópia, comparação e valores padrão
- ✅ **Technique**: Testa criação, cópia, propriedades de mídia
- ✅ **User**: Testa criação, cópia, email opcional
- ✅ **MediaFile**: Testa criação, tipos de mídia, cópia
- ✅ **MediaType**: Testa enum e comparações

### Use Cases (100% cobertura)
- ✅ **GetAllMartialArtsUseCase**: Testa obtenção de artes marciais
- ✅ **GetTechniquesByMartialArtUseCase**: Testa obtenção de técnicas por modalidade
- ✅ **GetCurrentUserUseCase**: Testa obtenção do usuário atual
- ✅ **SaveMediaFileUseCase**: Testa salvamento de arquivos de mídia
- ✅ **DeleteMediaFileUseCase**: Testa exclusão de arquivos de mídia
- ✅ **GetMediaUriUseCase**: Testa obtenção de URIs de mídia

### ViewModels (100% cobertura)
- ✅ **HomeViewModel**: Testa carregamento, estados de loading, erro e refresh
- ✅ **MartialArtDetailViewModel**: Testa carregamento de detalhes, técnicas e refresh

### Conversores (100% cobertura)
- ✅ **Converters**: Testa conversão JSON para MediaFile e vice-versa

## Padrões de Teste Utilizados

### Estrutura Given-When-Then
Todos os testes seguem o padrão AAA (Arrange-Act-Assert):

```kotlin
@Test
fun `deve fazer algo quando condição`() {
    // Given (Arrange)
    val input = "dados de teste"
    
    // When (Act)
    val result = sut.process(input)
    
    // Then (Assert)
    assertEquals(expected, result)
}
```

### Nomenclatura de Testes
Os testes utilizam nomes descritivos em português que explicam o comportamento:

```kotlin
@Test
fun `deve retornar lista vazia quando repositório não tem dados`()
@Test
fun `deve mostrar erro quando use case falha`()
@Test
fun `deve copiar MartialArt com novos valores`()
```

### Mocks e Stubs
Utilizamos MockK para criar mocks dos repositórios e dependências:

```kotlin
private lateinit var mockRepository: MartialArtRepository

@Before
fun setup() {
    mockRepository = mockk()
    useCase = GetAllMartialArtsUseCase(mockRepository)
}
```

### Testes de Flow
Para testar Flows, utilizamos Turbine:

```kotlin
viewModel.uiState.test {
    val initialState = awaitItem()
    assertTrue(initialState.isLoading)
    
    val loadedState = awaitItem()
    assertFalse(loadedState.isLoading)
    assertEquals(expectedData, loadedState.data)
}
```

## Cenários de Teste Cobertos

### Casos de Sucesso
- ✅ Carregamento de dados com sucesso
- ✅ Operações CRUD bem-sucedidas
- ✅ Conversões de dados corretas
- ✅ Estados de UI apropriados

### Casos de Erro
- ✅ Falhas de rede/repositório
- ✅ Dados não encontrados
- ✅ Validações de entrada
- ✅ Estados de erro na UI

### Casos Extremos
- ✅ Listas vazias
- ✅ Dados nulos
- ✅ JSON inválido
- ✅ Timeouts e exceções

## Manutenção dos Testes

### Adicionando Novos Testes
1. Crie o arquivo de teste na estrutura apropriada
2. Siga o padrão de nomenclatura existente
3. Use mocks para dependências externas
4. Teste casos de sucesso e erro
5. Adicione à TestSuite se necessário

### Executando Testes Durante Desenvolvimento
```bash
# Executar testes em modo watch
./gradlew test --continuous

# Executar testes específicos rapidamente
./gradlew test --tests "*Test" --parallel
```

## Relatórios de Cobertura

Após executar os testes, você pode encontrar os relatórios em:
- `app/build/reports/tests/` - Relatórios de teste
- `app/build/reports/jacoco/` - Relatórios de cobertura

## Boas Práticas Seguidas

1. **Isolamento**: Cada teste é independente
2. **Determinismo**: Testes sempre produzem o mesmo resultado
3. **Rapidez**: Testes executam rapidamente
4. **Legibilidade**: Nomes descritivos e estrutura clara
5. **Manutenibilidade**: Fácil de modificar e estender
6. **Cobertura**: Testa casos de sucesso, erro e extremos

## Próximos Passos

Para expandir a cobertura de testes, considere adicionar:

1. **Testes de Integração**: Para repositórios e banco de dados
2. **Testes de UI**: Para componentes Compose
3. **Testes de Navegação**: Para fluxos de navegação
4. **Testes de Performance**: Para operações críticas
5. **Testes de Acessibilidade**: Para garantir inclusividade 