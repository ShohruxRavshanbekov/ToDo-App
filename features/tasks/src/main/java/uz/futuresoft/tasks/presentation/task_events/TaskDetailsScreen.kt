@file:OptIn(ExperimentalMaterial3Api::class)

package uz.futuresoft.tasks.presentation.task_events

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import uz.futuresoft.core.ui.components.AppAlertDialog
import uz.futuresoft.core.ui.components.LoadingDialog
import uz.futuresoft.core.ui.components.NetworkStateIndicator
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository
import uz.futuresoft.tasks.presentation.task_events.components.TaskDetailsScreenTopBar
import uz.futuresoft.tasks.presentation.task_events.components.TaskPropertiesCard
import uz.futuresoft.tasks.presentation.task_events.components.TextInputCard
import uz.futuresoft.tasks.utils.NetworkChangeHandler
import uz.futuresoft.tasks.utils.TodoItemImportance
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.Calendar
import java.util.UUID

@Composable
fun TaskDetailsScreen(
    taskId: String? = null,
    navHostController: NavHostController,
    todoItemsRepository: TodoItemsRepository,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()
    val viewModel by remember { mutableStateOf(TaskDetailsViewModel(todoItemsRepository = todoItemsRepository)) }
    val uiState by viewModel.uiState.collectAsState()
    val loading by remember { derivedStateOf { uiState.loading } }
    val error by remember { derivedStateOf { uiState.error } }
    val task by remember { derivedStateOf { uiState.task } }
    var isNetworkAvailable: Boolean? by remember { mutableStateOf(null) }

    LaunchedEffect(key1 = Unit) {
        if (taskId != null) {
            viewModel.getTaskById(id = taskId)
        }
    }

    NetworkChangeHandler(
        lifecycleOwner = lifecycleOwner,
        onNetworkAvailable = {
            isNetworkAvailable = true
        },
        onNetworkUnavailable = {
            isNetworkAvailable = false
        }
    )

    TaskDetailsScreenContent(
        taskId = taskId,
        task = task,
        loading = loading,
        error = error,
        isNetworkAvailable = isNetworkAvailable,
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
    loading: Boolean,
    error: Throwable?,
    isNetworkAvailable: Boolean?,
    onBackClicked: () -> Unit,
    onDeleteTask: () -> Unit,
    onCreateTaskClicked: (ToDoItem) -> Unit,
    onUpdateTaskClicked: (ToDoItem) -> Unit,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val snackBarHostState = remember { SnackbarHostState() }
    var showAlertDialog by remember { mutableStateOf(false) }
    var taskText by remember { mutableStateOf("") }
    var importance by remember { mutableStateOf(TodoItemImportance.NORMAL.value) }
    var deadline: Long? by remember { mutableStateOf(null) }
    var initialSelectedDateMillis: Long? by remember { mutableStateOf(deadline) }
    var showCalendar by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = task) {
        taskText = task.text
        importance = task.importance
        deadline = task.deadline
        initialSelectedDateMillis = task.deadline
        showCalendar = task.deadline != null
        Log.d("AAAAA", "TaskDetailsScreenContent: $task")
        Log.d("AAAAA", "TaskDetailsScreenContent: showCalendar=$showCalendar")
    }

    LaunchedEffect(key1 = Unit) {
        if (isNetworkAvailable != false && error != null) {
            when (error) {
                is SocketTimeoutException -> {
                    snackBarHostState.showSnackbar(message = "Операция не выполнена! Отсутствует соединение с интернетом")
                }

                is UnknownHostException -> {
                    snackBarHostState.showSnackbar(message = "Не удалось соедениться с сервером! Пожалуйста, попробуйте позже")
                }

                else -> {
                    snackBarHostState.showSnackbar(message = "Что-то пошло не так. Пожалуйста, попробуйте заново")
                }
            }
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        topBar = {
            TaskDetailsScreenTopBar(
                taskText = taskText,
                isNetworkAvailable = isNetworkAvailable,
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
                .padding(vertical = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            TextInputCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                taskText = taskText,
                onValueChanged = { taskText = it },
            )
            TaskPropertiesCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                keyboardController = keyboardController,
                importance = importance,
                showCalendar = showCalendar,
                initialSelectedDateMillis = initialSelectedDateMillis,
                onImportanceChange = {
                    importance = it
                    keyboardController?.hide()
                },
                onDateSelected = { deadline = it },
            )

            if (taskId != null) {
                DeleteTaskButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = { showAlertDialog = true }
                )
            }
        }

        if (loading) {
            LoadingDialog()
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
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier,
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
            loading = false,
            error = null,
            isNetworkAvailable = false,
            onBackClicked = {},
            onCreateTaskClicked = {},
            onUpdateTaskClicked = {},
            onDeleteTask = {},
        )
    }
}