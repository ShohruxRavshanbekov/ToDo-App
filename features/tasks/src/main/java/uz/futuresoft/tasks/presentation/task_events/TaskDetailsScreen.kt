package uz.futuresoft.tasks.presentation.task_events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.tasks.common.models.TodoItemImportance
import uz.futuresoft.tasks.domain.models.TodoItem
import uz.futuresoft.tasks.domain.repository.TodoItemsRepository
import uz.futuresoft.tasks.presentation.task_events.components.TaskDetailsScreenTopBar
import uz.futuresoft.tasks.presentation.task_events.components.TaskPropertiesCard
import uz.futuresoft.tasks.presentation.task_events.components.TextInputCard
import uz.futuresoft.tasks.utils.convertMillisToDate
import java.util.Calendar
import java.util.UUID

@Composable
fun TaskDetailsScreen(
    taskId: String? = null,
    navHostController: NavHostController,
    todoItemsRepository: TodoItemsRepository,
) {
    val viewModel = TaskDetailsViewModel(toDoItemsRepository = todoItemsRepository)
    var taskText by remember { mutableStateOf("") }
    var importance by remember { mutableStateOf(TodoItemImportance.NORMAL) }
    var deadline by remember { mutableLongStateOf(0L) }

    TaskDetailsScreenContent(
        taskId = taskId,
        taskText = taskText,
        onTaskTextChanged = { taskText = it },
        onBackClicked = { navHostController.popBackStack() },
        onImportanceChanged = { importance = it },
        onDateSelected = { deadline = it ?: 0L },
        onSaveClicked = {
            viewModel.addTask(
                task = TodoItem(
                    id = UUID.randomUUID().toString(),
                    text = taskText,
                    importance = importance,
                    deadline = deadline.convertMillisToDate(),
                    isCompleted = false,
                    createdAt = Calendar.getInstance().time,
                )
            )
            navHostController.popBackStack()
        },
    )
}

@Composable
private fun TaskDetailsScreenContent(
    taskId: String?,
    taskText: String,
    onTaskTextChanged: (String) -> Unit,
    onBackClicked: () -> Unit,
    onImportanceChanged: (TodoItemImportance) -> Unit,
    onDateSelected: (Long?) -> Unit,
    onSaveClicked: () -> Unit,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        topBar = {
            TaskDetailsScreenTopBar(
                taskText = taskText,
                onBackClicked = onBackClicked,
                onSaveClicked = onSaveClicked
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TextInputCard(
                taskText = taskText,
                onValueChanged = onTaskTextChanged,
            )
            TaskPropertiesCard(
                onImportanceChange = onImportanceChanged,
                onDateSelected = onDateSelected
            )

            if (taskId != null) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.error,
                        disabledContainerColor = MaterialTheme.colorScheme.surface,
                        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
                    shape = RoundedCornerShape(16.dp),
                    contentPadding = PaddingValues(16.dp),
                    onClick = {},
                ) {
                    Text(
                        text = "Удалить",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun TaskDetailsScreenPreview() {
    TodoAppTheme {
        TaskDetailsScreenContent(
            taskId = "",
            taskText = "",
            onTaskTextChanged = {},
            onBackClicked = {},
            onSaveClicked = {},
            onImportanceChanged = {},
            onDateSelected = {},
        )
    }
}