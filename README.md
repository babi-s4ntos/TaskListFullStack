# TaskListApp - Aplicação Full Stack

Aplicação completa de Lista de Tarefas com backend Ktor (Kotlin) e aplicativo Android nativo com Jetpack Compose.

## 📋 Estrutura do Projeto

```
TaskListApp/
├── backend/          # Servidor Ktor
│   ├── src/main/kotlin/com/tasklist/
│   │   └── Application.kt
│   └── build.gradle.kts
└── android/          # Aplicativo Android
    ├── app/src/main/java/com/tasklist/
    │   ├── MainActivity.kt
    │   ├── TaskListContent.kt
    │   ├── TaskViewModel.kt
    │   ├── TaskApi.kt
    │   └── Task.kt
    └── build.gradle.kts
```

## 🔧 Backend (Ktor)

### Tecnologias
- Kotlin 1.9.22
- Ktor 2.3.7
- Kotlinx Serialization
- Netty Server

### Endpoints
- `GET /api/tasks` - Retorna todas as tarefas
- `POST /api/tasks` - Cria uma nova tarefa

### Como Executar o Backend

1. Navegue até o diretório do backend:
```bash
cd C:\Users\User\CascadeProjects\TaskListApp\backend
```

2. Execute o servidor com Gradle:
```bash
gradlew run
```

Ou se você tiver o Gradle instalado globalmente:
```bash
gradle run
```

O servidor iniciará em `http://localhost:8080`

### Testar os Endpoints

**GET /api/tasks**
```bash
curl http://localhost:8080/api/tasks
```

**POST /api/tasks**
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{"id": 0, "title": "Minha Tarefa", "description": "Descrição da tarefa", "completed": false}'
```

## 📱 Aplicativo Android (Jetpack Compose)

### Tecnologias
- Kotlin 1.9.22
- Jetpack Compose
- Material3
- Retrofit 2.9.0
- Coroutines
- ViewModel

### Funcionalidades
- Listagem de tarefas do backend
- Formulário para adicionar novas tarefas
- Feedback visual (mensagens de sucesso/erro)
- Atualização automática da lista
- Interface moderna com Material3

### Como Executar o App Android

1. Abra o projeto no Android Studio:
```bash
C:\Users\User\CascadeProjects\TaskListApp\android
```

2. Certifique-se de que o backend está rodando em `http://localhost:8080`

3. Conecte um dispositivo Android ou inicie um emulador

4. Clique em "Run" (▶️) no Android Studio ou pressione `Shift + F10`

### Configuração de Rede

O app está configurado para usar `http://10.0.2.2:8080` como URL base, que é o endereço especial do emulador Android para acessar `localhost` da máquina host.

Se estiver testando em um dispositivo físico, altere a URL em `TaskApi.kt`:
```kotlin
private const val BASE_URL = "http://SEU_IP_LOCAL:8080/"
```

## 📝 Estrutura dos Dados

### Task (Tarefa)
```kotlin
data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val completed: Boolean = false
)
```

## 🎨 Interface do Usuário

### Tela Principal
- Lista de tarefas com cards
- Botão de atualização (refresh)
- Botão flutuante para adicionar tarefas
- Indicador de status (Pendente/Concluída)

### Formulário de Adição
- Campo para título
- Campo para descrição
- Validação de campos obrigatórios
- Feedback visual de sucesso/erro

## 🔒 Permissões

O app requer a permissão de internet:
```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## 📦 Dependências

### Backend
- `io.ktor:ktor-server-core`
- `io.ktor:ktor-server-netty`
- `io.ktor:ktor-server-content-negotiation`
- `io.ktor:ktor-serialization-kotlinx-json`
- `org.jetbrains.kotlinx:kotlinx-serialization-json`

### Android
- `androidx.compose.ui:ui`
- `androidx.compose.material3:material3`
- `com.squareup.retrofit2:retrofit`
- `com.squareup.retrofit2:converter-gson`
- `org.jetbrains.kotlinx:kotlinx-coroutines-android`

## 🚀 Fluxo de Uso

1. Inicie o servidor Ktor backend
2. Execute o aplicativo Android
3. O app carregará automaticamente a lista de tarefas
4. Clique no botão "+" para adicionar uma nova tarefa
5. Preencha título e descrição
6. Clique em "Adicionar"
7. A tarefa será enviada ao backend e a lista será atualizada
8. Mensagens de sucesso ou erro serão exibidas

## 🐛 Troubleshooting

### Backend não inicia
- Verifique se a porta 8080 está disponível
- Certifique-se de ter o JDK 17 instalado

### App não conecta ao backend
- Verifique se o backend está rodando
- Em emulador: use `10.0.2.2` para localhost
- Em dispositivo físico: use o IP da sua máquina
- Verifique se o firewall não está bloqueando a conexão

### Erro de compilação Android
- Verifique se o SDK Android está instalado
- Certifique-se de ter o Android Studio atualizado
- Sincronize o Gradle: File > Sync Project with Gradle Files

## 📄 Licença

Este projeto foi criado para fins educacionais.
