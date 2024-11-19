@file:OptIn(ExperimentalMaterial3Api::class)

package uz.futuresoft.tasks.presentation.home

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import uz.futuresoft.core.receivers.NetworkChangeReceiver
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.Plus
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.core.ui.theme.White
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository
import uz.futuresoft.navigation.Routes
import uz.futuresoft.tasks.presentation.home.components.HomeScreenTopBar
import uz.futuresoft.tasks.presentation.home.components.TaskList
import uz.futuresoft.tasks.utils.NetworkChangeHandler
import uz.futuresoft.tasks.utils.TodoItemImportance
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
    val isTasksLoading by viewModel.isTasksLoading.collectAsState()
    val isTaskModifyInProgress by viewModel.isTaskModifyInProgress.collectAsState()
    val error by viewModel.error.collectAsState()
    val tasks by viewModel.tasks.collectAsState()
    var isNetworkAvailable: Boolean? by remember { mutableStateOf(null) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getTasks()
    }

    NetworkChangeHandler(
        lifecycleOwner = lifecycleOwner,
        onNetworkAvailable = {
            isNetworkAvailable = true
            scope.launch {
                viewModel.getTasks()
            }
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
        isTasksLoading = isTasksLoading,
        isTaskModifyInProgress = isTaskModifyInProgress,
        onAddNewTaskClicked = { navHostController.navigate(Routes.TaskDetails(taskId = null)) },
        onEditTaskClick = { navHostController.navigate(Routes.TaskDetails(taskId = it)) },
        onChangeTheme = onChangeTheme,
        onMarkItemAsCompleted = { viewModel.markAsCompleted(taskId = it.id, task = it) },
        onDeleteItem = { viewModel.removeTask(taskId = it.id) },
        onRefresh = {
            if (isNetworkAvailable == true) {
                scope.launch {
                    viewModel.getTasks()
                }
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
    isTasksLoading: Boolean,
    isTaskModifyInProgress: Boolean,
    onRefresh: () -> Unit,
    onAddNewTaskClicked: () -> Unit,
    onEditTaskClick: (String) -> Unit,
    onMarkItemAsCompleted: (ToDoItem) -> Unit,
    onDeleteItem: (ToDoItem) -> Unit,
    onChangeTheme: () -> Unit,
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    val lazyListState = rememberLazyListState()
    val lazyListFirstVisibleItemScrollOffset by
    remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }
    val snackBarHostState = remember { SnackbarHostState() }
    var showCompletedTasks by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = error) {
        if (error != null) {
            snackBarHostState.showSnackbar(message = "Что-то пошло не так. Пожалуйста, попробуйте заново.")
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
                showCompletedTasksDetailsBar = tasks.isNotEmpty(),
                completedTasksCount = tasks.filter { it.isCompleted }.size,
                onChangeTheme = onChangeTheme,
                showCompletedTasks = showCompletedTasks,
                onShowCompletedTasksClick = { showCompletedTasks = !showCompletedTasks },
            )
        },
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
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        PullToRefreshBox(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            isRefreshing = isTasksLoading || isTaskModifyInProgress,
            onRefresh = onRefresh,
        ) {
            Column {
                NetworkStateIndicator(
                    isNetworkAvailable = isNetworkAvailable,
                    modifier = Modifier.fillMaxWidth()
                )
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
}

@Composable
private fun NetworkStateIndicator(isNetworkAvailable: Boolean?, modifier: Modifier) {
    AnimatedVisibility(
        visible = isNetworkAvailable == false,
        enter = expandIn(),
        exit = shrinkOut(),
    ) {
        Box(
            modifier = modifier.background(color = MaterialTheme.colorScheme.error),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = "Нет связи с интернетом, данные могут быть неактуальным!",
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.onError,
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
            isTasksLoading = false,
            isTaskModifyInProgress = false,
            onRefresh = {},
            onAddNewTaskClicked = {},
            onEditTaskClick = {},
            onMarkItemAsCompleted = {},
            onDeleteItem = {},
            onChangeTheme = {},
        )
    }
}