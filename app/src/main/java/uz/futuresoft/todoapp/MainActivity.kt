package uz.futuresoft.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.tasks.domain.repository.TodoItemsRepository
import uz.futuresoft.todoapp.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val todoItemsRepository by remember { mutableStateOf(TodoItemsRepository()) }

            TodoAppTheme {
                Content(todoItemsRepository = todoItemsRepository)
            }
        }
    }
}

@Composable
private fun Content(
    todoItemsRepository: TodoItemsRepository,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        AppNavHost(todoItemsRepository = todoItemsRepository)
    }
}

@PreviewLightDark
@Composable
private fun ContentPreview() {
    TodoAppTheme {
        Content(todoItemsRepository = TodoItemsRepository())
    }
}