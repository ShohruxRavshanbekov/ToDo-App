@file:OptIn(ExperimentalMaterial3Api::class)

package uz.futuresoft.tasks.presentation.task_events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import uz.futuresoft.core.ui.components.AppAlertDialog
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository
import uz.futuresoft.tasks.presentation.task_events.components.TaskDetailsScreenTopBar
import uz.futuresoft.tasks.presentation.task_events.components.TaskPropertiesCard
import uz.futuresoft.tasks.presentation.task_events.components.TextInputCard
import uz.futuresoft.tasks.utils.TodoItemImportance
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
    val isTaskLoading by viewModel.isTaskLoading.collectAsState()
    val isTaskCreatingInProgress by viewModel.isTaskCreatingInProgress.collectAsState()
    val isTaskModifyInProgress by viewModel.isTaskModifyInProgress.collectAsState()
    val error by viewModel.error.collectAsState()

    LaunchedEffect(key1 = Unit) {
        if (taskId != null) {
            viewModel.getTaskById(id = taskId)
        }
    }

    TaskDetailsScreenContent(
        taskId = taskId,
        task = task,
        isTaskLoading = isTaskLoading,
        isTaskCreatingInProgress = isTaskCreatingInProgress,
        isTaskModifyInProgress = isTaskModifyInProgress,
        error = error,
        onBackClicked = { navHostController.popBackStack() },
        onDeleteTask = {
            viewModel.removeTask(taskId = task.id)
            navHostController.popBackStack()
        },
        onCreateTaskClicked = {
            viewModel.createTask(task = it)
            navHostController.popBackStack()
        },
        onUpdateTaskClicked = {
            viewModel.updateTask(taskId = task.id, task = it)
            navHostController.popBackStack()
        },
    )
}

@Composable
private fun TaskDetailsScreenContent(
    taskId: String?,
    task: ToDoItem,
    isTaskLoading: Boolean,
    isTaskCreatingInProgress: Boolean,
    isTaskModifyInProgress: Boolean,
    error: Throwable?,
    onBackClicked: () -> Unit,
    onDeleteTask: () -> Unit,
    onCreateTaskClicked: (ToDoItem) -> Unit,
    onUpdateTaskClicked: (ToDoItem) -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    var showAlertDialog by remember { mutableStateOf(false) }
    var taskText by remember { mutableStateOf("") }
    var importance by remember { mutableStateOf(TodoItemImportance.NORMAL.value) }
    var deadline: Long? by remember { mutableStateOf(null) }
    var initialSelectedDateMillis: Long? by remember { mutableStateOf(deadline) }
    val showCalendar by remember { mutableStateOf(deadline != null) }
    val pullToRefreshState = rememberPullToRefreshState()
    val isRefreshing = isTaskLoading || isTaskCreatingInProgress || isTaskModifyInProgress

    LaunchedEffect(key1 = task) {
        taskText = task.text
        importance = task.importance
        deadline = task.deadline
        initialSelectedDateMillis = task.deadline
    }

    LaunchedEffect(key1 = error) {
        if (error != null) {
            snackBarHostState.showSnackbar(message = "Что-то пошло не так. Пожалуйста, попробуйте заново.")
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        topBar = {
            TaskDetailsScreenTopBar(
                taskText = taskText,
                onBackClicked = onBackClicked,
                onSaveClicked = {
                    if (taskId == null) {
                        onCreateTaskClicked(
                            ToDoItem(
                                id = UUID.randomUUID().toString(),
                                text = taskText,
                                importance = importance.ifEmpty { TodoItemImportance.NORMAL.value },
                                deadline = deadline,
                                isCompleted = false,
                                createdAt = Calendar.getInstance().timeInMillis,
                                modifiedAt = Calendar.getInstance().timeInMillis,
                            )
                        )
                    } else {
                        onUpdateTaskClicked(
                            ToDoItem(
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
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
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
                onValueChanged = { taskText = it },
            )
            TaskPropertiesCard(
                importance = importance,
                showCalendar = showCalendar,
                initialSelectedDateMillis = initialSelectedDateMillis,
                onImportanceChange = { importance = it },
                onDateSelected = { deadline = it },
            )

            if (taskId != null) {
                DeleteTaskButton(onClick = { showAlertDialog = true })
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

@Composable
private fun DeleteTaskButton(
    onClick: () -> Unit,
) {
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
        onClick = onClick,
    ) {
        Text(
            text = "Удалить",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@PreviewLightDark
@Composable
private fun TaskDetailsScreenPreview() {
    TodoAppTheme {
        TaskDetailsScreenContent(
            taskId = "",
            task = ToDoItem(
                id = "",
                text = "",
                importance = "",
                isCompleted = false,
                createdAt = 0L,
                modifiedAt = 0L
            ),
            isTaskLoading = false,
            isTaskCreatingInProgress = false,
            isTaskModifyInProgress = false,
            error = null,
            onBackClicked = {},
            onCreateTaskClicked = {},
            onUpdateTaskClicked = {},
            onDeleteTask = {},
        )
    }
}