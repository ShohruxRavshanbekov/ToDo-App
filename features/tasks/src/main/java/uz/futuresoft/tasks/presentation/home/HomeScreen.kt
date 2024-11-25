@file:OptIn(ExperimentalMaterial3Api::class)

package uz.futuresoft.tasks.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.Plus
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository
import uz.futuresoft.navigation.Routes
import uz.futuresoft.tasks.presentation.home.components.HomeScreenTopBar
import uz.futuresoft.tasks.presentation.home.components.TaskList
import uz.futuresoft.tasks.utils.NetworkChangeHandler
import uz.futuresoft.tasks.utils.TodoItemImportance
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.Calendar
import java.util.UUID

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    todoItemsRepository: TodoItemsRepository,
    darkTheme: Boolean,
    onChangeTheme: () -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val scope = rememberCoroutineScope()
    val viewModel by remember { mutableStateOf(HomeViewModel(todoItemsRepository = todoItemsRepository)) }
    val uiState by viewModel.uiState.collectAsState()
    val loading by remember { derivedStateOf { uiState.loading } }
    val error by remember { derivedStateOf { uiState.error } }
    val tasks by remember { derivedStateOf { uiState.tasks } }
    val snackBarHostState = remember { SnackbarHostState() }
    var isNetworkAvailable: Boolean? by remember { mutableStateOf(null) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getTasks()
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

    HomeScreenContent(
        tasks = tasks,
        darkTheme = darkTheme,
        error = error,
        isNetworkAvailable = isNetworkAvailable,
        loading = loading,
        snackBarHostState = snackBarHostState,
        onAddNewTaskClicked = { navHostController.navigate(Routes.TaskDetails(taskId = null)) },
        onEditTaskClick = { navHostController.navigate(Routes.TaskDetails(taskId = it)) },
        onChangeTheme = onChangeTheme,
        onMarkItemAsCompleted = { viewModel.markAsCompleted(taskId = it.id, task = it) },
        onDeleteItem = { viewModel.removeTask(taskId = it.id) },
        onRefresh = {
            scope.launch {
                viewModel.getTasks()
            }
        },
    )
}

@Composable
private fun HomeScreenContent(
    tasks: List<ToDoItem>,
    darkTheme: Boolean,
    error: Throwable?,
    isNetworkAvailable: Boolean?,
    loading: Boolean,
    snackBarHostState: SnackbarHostState,
    onRefresh: () -> Unit,
    onAddNewTaskClicked: () -> Unit,
    onEditTaskClick: (String) -> Unit,
    onMarkItemAsCompleted: (ToDoItem) -> Unit,
    onDeleteItem: (ToDoItem) -> Unit,
    onChangeTheme: () -> Unit,
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    val pullToRefreshState = rememberPullToRefreshState()
    val lazyListState = rememberLazyListState()
    val lazyListFirstVisibleItemScrollOffset by
    remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    var showCompletedTasks by remember { mutableStateOf(false) }

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
            .background(color = MaterialTheme.colorScheme.background)
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeScreenTopBar(
                scrollBehavior = scrollBehavior,
                darkTheme = darkTheme,
                isNetworkAvailable = isNetworkAvailable,
                showCompletedTasksDetailsBar = tasks.isNotEmpty(),
                completedTasksCount = tasks.filter { it.isCompleted }.size,
                onChangeTheme = onChangeTheme,
                showCompletedTasks = showCompletedTasks,
                onShowCompletedTasksClick = { showCompletedTasks = !showCompletedTasks },
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        floatingActionButton = {
            AnimatedVisibility(
                visible = lazyListFirstVisibleItemScrollOffset <= 0,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                FloatingActionButton(
                    shape = CircleShape,
                    containerColor = MaterialTheme.colorScheme.secondary,
                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp),
                    onClick = onAddNewTaskClicked,
                ) {
                    Icon(imageVector = AppIcons.Plus, contentDescription = null)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = loading,
            onRefresh = onRefresh,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            state = pullToRefreshState,
        ) {
            TaskList(
                state = lazyListState,
                tasks = if (!showCompletedTasks) tasks.filter { !it.isCompleted } else tasks,
                onAddNewTaskClick = onAddNewTaskClicked,
                onEditTaskClick = onEditTaskClick,
                onMarkItemAsCompleted = onMarkItemAsCompleted,
                onDeleteItem = onDeleteItem,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun HomeScreenContentPreview() {
    TodoAppTheme {
        HomeScreenContent(
            tasks = listOf(
                ToDoItem(
                    id = UUID.randomUUID().toString(),
                    text = "Делать уроки",
                    importance = TodoItemImportance.NORMAL.value,
                    isCompleted = false,
                    createdAt = Calendar.getInstance().timeInMillis,
                    modifiedAt = Calendar.getInstance().timeInMillis,
                ),
                ToDoItem(
                    id = UUID.randomUUID().toString(),
                    text = "Играть футбол",
                    importance = TodoItemImportance.LOW.value,
                    isCompleted = false,
                    createdAt = Calendar.getInstance().timeInMillis,
                    modifiedAt = Calendar.getInstance().timeInMillis,
                ),
                ToDoItem(
                    id = UUID.randomUUID().toString(),
                    text = "Посещать лекцию Яндекса :)",
                    importance = TodoItemImportance.HIGH.value,
                    isCompleted = false,
                    createdAt = Calendar.getInstance().timeInMillis,
                    modifiedAt = Calendar.getInstance().timeInMillis,
                ),
            ),
            darkTheme = false,
            error = Throwable(),
            isNetworkAvailable = false,
            loading = false,
            snackBarHostState = SnackbarHostState(),
            onRefresh = {},
            onAddNewTaskClicked = {},
            onEditTaskClick = {},
            onMarkItemAsCompleted = {},
            onDeleteItem = {},
            onChangeTheme = {},
        )
    }
}