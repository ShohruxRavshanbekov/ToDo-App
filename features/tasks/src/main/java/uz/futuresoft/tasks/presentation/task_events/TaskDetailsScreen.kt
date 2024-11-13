package uz.futuresoft.tasks.presentation.task_events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import uz.futuresoft.core.ui.components.AppAlertDialog
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.core.utils.AppSharedPreferences
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.tasks.utils.TodoItemImportance
import uz.futuresoft.data.repositories.TodoItemsRepository
import uz.futuresoft.tasks.presentation.task_events.components.TaskDetailsScreenTopBar
import uz.futuresoft.tasks.presentation.task_events.components.TaskPropertiesCard
import uz.futuresoft.tasks.presentation.task_events.components.TextInputCard
import java.util.Calendar
import java.util.UUID

@Composable
fun TaskDetailsScreen(
    taskId: String? = null,
    navHostController: NavHostController,
    todoItemsRepository: TodoItemsRepository,
) {
    val viewModel by remember { mutableStateOf(TaskDetailsViewModel(todoItemsRepository = todoItemsRepository)) }
    val task by viewModel.task.collectAsState()
    val loading by viewModel.loading.collectAsState()
    var taskText by remember { mutableStateOf(task.text) }
    var importance by remember { mutableStateOf(task.importance) }
    var deadline by remember { mutableStateOf(task.deadline) }
    val initialSelectedDateMillis by remember { mutableStateOf(task.deadline) }
    val showCalendar by remember { mutableStateOf(deadline != null) }

    LaunchedEffect(key1 = taskId) {
        if (taskId != null) {
            viewModel.getTaskById(id = taskId)
        }
    }

    TaskDetailsScreenContent(
        taskId = taskId,
        taskText = taskText,
        importance = importance,
        showCalendar = showCalendar,
        loading = loading,
        onTaskTextChanged = { taskText = it },
        onBackClicked = { navHostController.popBackStack() },
        onImportanceChanged = { importance = it },
        onDateSelected = { deadline = it },
        onSaveClicked = {
            if (taskId == null) {
                val revision =
                    AppSharedPreferences.getInt(key = AppSharedPreferences.KEY_REVISION) + 1
                viewModel.createTask(
                    revision = revision,
                    task = ToDoItem(
                        id = UUID.randomUUID().toString(),
                        text = taskText,
                        importance = importance,
                        deadline = deadline,
                        isCompleted = false,
                        createdAt = Calendar.getInstance().timeInMillis,
                    )
                )
            } else {
                viewModel.updateTask(
                    taskId = task.id,
                    task = ToDoItem(
                        id = task.id,
                        text = taskText,
                        importance = importance,
                        deadline = deadline,
                        isCompleted = task.isCompleted,
                        createdAt = task.createdAt,
                        modifiedAt = Calendar.getInstance().timeInMillis,
                    )
                )
            }
            navHostController.popBackStack()
        },
        onDeleteTask = {
//            navHostController.popBackStack()
            viewModel.removeTask(taskId = task.id)
        },
        initialSelectedDateMillis = if (initialSelectedDateMillis != 0L) {
            initialSelectedDateMillis
        } else {
            null
        },
    )
}

@Composable
private fun TaskDetailsScreenContent(
    taskId: String?,
    taskText: String,
    importance: String,
    showCalendar: Boolean,
    loading: Boolean,
    initialSelectedDateMillis: Long? = null,
    onTaskTextChanged: (String) -> Unit,
    onBackClicked: () -> Unit,
    onImportanceChanged: (String) -> Unit,
    onDateSelected: (Long?) -> Unit,
    onSaveClicked: () -> Unit,
    onDeleteTask: () -> Unit,
) {
    var showAlertDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        topBar = {
            TaskDetailsScreenTopBar(
                taskText = taskText,
                loading = loading,
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
                importance = importance,
                showCalendar = showCalendar,
                initialSelectedDateMillis = initialSelectedDateMillis,
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
                    onClick = { showAlertDialog = true },
                ) {
                    if (loading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.outline,
                        )
                    } else {
                        Text(
                            text = "Удалить",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }

        if (showAlertDialog) {
            AppAlertDialog(
                messageText = "Вы действительно хотите удалить \"$taskText\"?",
                confirmText = "Да",
                dismissText = "Нет",
                confirmTextColor = MaterialTheme.colorScheme.error,
                dismissTextColor = MaterialTheme.colorScheme.primary,
                onConfirm = {
                    onDeleteTask()
                    showAlertDialog = false
                },
                onDismiss = { showAlertDialog = false }
            )
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
            importance = TodoItemImportance.NORMAL.value,
            showCalendar = false,
            loading = false,
            onTaskTextChanged = {},
            onBackClicked = {},
            onSaveClicked = {},
            onImportanceChanged = {},
            onDateSelected = {},
            onDeleteTask = {},
        )
    }
}