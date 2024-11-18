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
import androidx.compose.material3.rememberTopAppBarState
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
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.Plus
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.core.utils.AppSharedPreferences
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository
import uz.futuresoft.navigation.Routes
import uz.futuresoft.tasks.presentation.home.components.HomeScreenTopBar
import uz.futuresoft.tasks.presentation.home.components.TaskList
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
    val scope = rememberCoroutineScope()
    val lastRevision = AppSharedPreferences.getInt(key = AppSharedPreferences.KEY_REVISION)
    val viewModel by remember { mutableStateOf(HomeViewModel(todoItemsRepository = todoItemsRepository)) }
    val gettingTasksInProgress by viewModel.gettingTasksInProgress.collectAsState()
    val markingTaskAsCompletedInProgress by viewModel.markingTaskAsCompletedInProgress.collectAsState()
    val deletingTaskInProgress by viewModel.deletingTaskInProgress.collectAsState()
    val error by viewModel.error.collectAsState()
    val tasks by viewModel.tasks.collectAsState()
    var refreshData by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getTasks()
    }

    LaunchedEffect(key1 = refreshData) {
        if (refreshData) {
            viewModel.getTasks()
            refreshData = false
        }
    }

    HomeScreenContent(
        tasks = tasks,
        darkTheme = darkTheme,
        error = error,
        gettingTasksInProgress = gettingTasksInProgress,
        markingTaskAsCompletedInProgress = markingTaskAsCompletedInProgress,
        deletingTaskInProgress = deletingTaskInProgress,
        onAddNewTaskClicked = { navHostController.navigate(Routes.TaskDetails(taskId = null)) },
        onEditTaskClick = { navHostController.navigate(Routes.TaskDetails(taskId = it)) },
        onMarkItemAsCompleted = {
            scope.launch {
                viewModel.markAsCompleted(revision = lastRevision, taskId = it.id!!, task = it)
            }
        },
        onDeleteItem = {
            scope.launch {
                viewModel.removeTask(revision = lastRevision, taskId = it.id!!)
            }
        },
        onChangeTheme = onChangeTheme,
        onRefresh = { refreshData = true },
    )
}

@Composable
private fun HomeScreenContent(
    tasks: List<ToDoItem>,
    darkTheme: Boolean,
    error: Throwable?,
    gettingTasksInProgress: Boolean,
    markingTaskAsCompletedInProgress: Boolean,
    deletingTaskInProgress: Boolean,
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
                completedTasksCount = tasks.filter { it.isCompleted == true }.size,
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
            isRefreshing = gettingTasksInProgress || markingTaskAsCompletedInProgress || deletingTaskInProgress,
            onRefresh = onRefresh,
        ) {
            TaskList(
                state = lazyListState,
                tasks = if (!showCompletedTasks) tasks.filter { it.isCompleted == false } else tasks,
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
                    createdAt = Calendar.getInstance().timeInMillis
                ),
                ToDoItem(
                    id = UUID.randomUUID().toString(),
                    text = "Играть футбол",
                    importance = TodoItemImportance.LOW.value,
                    isCompleted = false,
                    createdAt = Calendar.getInstance().timeInMillis

                ),
                ToDoItem(
                    id = UUID.randomUUID().toString(),
                    text = "Посещать лекцию Яндекса :)",
                    importance = TodoItemImportance.HIGH.value,
                    isCompleted = false,
                    createdAt = Calendar.getInstance().timeInMillis

                ),
            ),
            darkTheme = false,
            error = Throwable(),
            gettingTasksInProgress = false,
            markingTaskAsCompletedInProgress = false,
            deletingTaskInProgress = false,
            onRefresh = {},
            onAddNewTaskClicked = {},
            onEditTaskClick = {},
            onMarkItemAsCompleted = {},
            onDeleteItem = {},
            onChangeTheme = {},
        )
    }
}