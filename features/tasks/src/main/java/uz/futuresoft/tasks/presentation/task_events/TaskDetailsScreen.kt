@file:OptIn(ExperimentalMaterial3Api::class)

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
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
    val scope = rememberCoroutineScope()
    val lastRevision = AppSharedPreferences.getInt(key = AppSharedPreferences.KEY_REVISION)
    val viewModel by remember { mutableStateOf(TaskDetailsViewModel(todoItemsRepository = todoItemsRepository)) }
    val task by viewModel.task.collectAsState()
    val gettingTaskInProgress by viewModel.gettingTaskInProgress.collectAsState()
    val createTaskInProgress by viewModel.createTaskInProgress.collectAsState()
    val updateTaskInProgress by viewModel.updateTaskInProgress.collectAsState()
    val deletingTaskInProgress by viewModel.deletingTaskInProgress.collectAsState()
    val error by viewModel.error.collectAsState()
    var taskText by remember { mutableStateOf("") }
    var importance by remember {
        mutableStateOf(TodoItemImportance.NORMAL.value)
    }
    var deadline: Long? by remember { mutableStateOf(null) }
    var initialSelectedDateMillis: Long? by remember { mutableStateOf(null) }
    val showCalendar by remember { mutableStateOf(deadline != null) }

    LaunchedEffect(key1 = Unit) {
        if (taskId != null) {
            viewModel.getTaskById(id = taskId)
        }/* else {
            taskText = ""
            importance = TodoItemImportance.NORMAL.value
            deadline = null
            initialSelectedDateMillis = null
        }*/
    }

    LaunchedEffect(key1 = task) {
        taskText = task.text ?: ""
        importance = task.importance ?: TodoItemImportance.NORMAL.value
        deadline = task.deadline
        initialSelectedDateMillis = task.deadline
    }

    TaskDetailsScreenContent(
        taskId = taskId,
        taskText = taskText,
        importance = importance,
        showCalendar = showCalendar,
        gettingTaskInProgress = gettingTaskInProgress,
        createTaskInProgress = createTaskInProgress,
        updateTaskInProgress = updateTaskInProgress,
        deleteTaskInProgress = deletingTaskInProgress,
        error = error,
        onTaskTextChanged = { taskText = it },
        onBackClicked = { navHostController.popBackStack() },
        onImportanceChanged = { importance = it },
        onDateSelected = { deadline = it },
        onSaveClicked = {
            scope.launch {
                if (taskId == null) {
                    viewModel.createTask(
                        revision = lastRevision,
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
                        revision = lastRevision,
                        taskId = task.id!!,
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
            }
            navHostController.popBackStack()
        },
        onDeleteTask = {
            scope.launch {
                viewModel.removeTask(revision = lastRevision, taskId = task.id!!)
            }
//            navHostController.popBackStack()
        },
        initialSelectedDateMillis = if (task.deadline != 0L) task.deadline else null,
    )
}

@Composable
private fun TaskDetailsScreenContent(
    taskId: String?,
    taskText: String,
    importance: String,
    showCalendar: Boolean,
    gettingTaskInProgress: Boolean,
    createTaskInProgress: Boolean,
    updateTaskInProgress: Boolean,
    deleteTaskInProgress: Boolean,
    error: Throwable?,
    initialSelectedDateMillis: Long? = null,
    onTaskTextChanged: (String) -> Unit,
    onBackClicked: () -> Unit,
    onImportanceChanged: (String) -> Unit,
    onDateSelected: (Long?) -> Unit,
    onSaveClicked: () -> Unit,
    onDeleteTask: () -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    var showAlertDialog by remember { mutableStateOf(false) }

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
                loading = if (taskId != null) updateTaskInProgress else createTaskInProgress,
                onBackClicked = onBackClicked,
                onSaveClicked = onSaveClicked
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            isRefreshing = gettingTaskInProgress,
            onRefresh = {},
        ) {
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
                        if (deleteTaskInProgress) {
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
            gettingTaskInProgress = false,
            createTaskInProgress = false,
            updateTaskInProgress = false,
            deleteTaskInProgress = false,
            error = null,
            onTaskTextChanged = {},
            onBackClicked = {},
            onSaveClicked = {},
            onImportanceChanged = {},
            onDateSelected = {},
            onDeleteTask = {},
        )
    }
}