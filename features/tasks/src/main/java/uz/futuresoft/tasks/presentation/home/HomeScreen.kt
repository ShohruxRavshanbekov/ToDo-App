@file:OptIn(ExperimentalMaterial3Api::class)

package uz.futuresoft.tasks.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.Plus
import uz.futuresoft.core.ui.images.AppImages
import uz.futuresoft.core.ui.images.NoDataFound
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.data.repositories.TodoItemsRepository
import uz.futuresoft.navigation.Routes
import uz.futuresoft.tasks.presentation.home.components.HomeScreenTopBar
import uz.futuresoft.tasks.presentation.home.components.NoDataFoundContent
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
    val viewModel by remember { mutableStateOf(HomeViewModel(todoItemsRepository = todoItemsRepository)) }
    val loading by viewModel.loading.collectAsState()
    val error by viewModel.error.collectAsState()
    val tasks by viewModel.tasks.collectAsState()
    var refreshData by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        viewModel.getTasks()
    }

//    LaunchedEffect(key1 = refreshData) {
//        if (refreshData) {
//            viewModel.getTasks()
//            refreshData = loading
//        }
//    }

    HomeScreenContent(
        tasks = tasks,
        darkTheme = darkTheme,
        error = error,
        isRefreshing = loading,
        onAddNewTaskClicked = { navHostController.navigate(Routes.TaskDetails(taskId = null)) },
        onEditTaskClick = { navHostController.navigate(Routes.TaskDetails(taskId = it)) },
        onMarkItemAsCompleted = { viewModel.markAsCompleted(taskId = it.id!!, task = it) },
        onDeleteItem = { viewModel.removeTask(taskId = it.id!!) },
        onChangeTheme = onChangeTheme,
        onRefresh = { /*refreshData = true*/
            scope.launch {
                viewModel.getTasks()
            }
        }
    )
}

@Composable
private fun HomeScreenContent(
    tasks: List<ToDoItem>,
    darkTheme: Boolean,
    error: Throwable?,
    isRefreshing: Boolean,
    onRefresh: () -> Unit,
    onAddNewTaskClicked: () -> Unit,
    onEditTaskClick: (String) -> Unit,
    onMarkItemAsCompleted: (ToDoItem) -> Unit,
    onDeleteItem: (ToDoItem) -> Unit,
    onChangeTheme: () -> Unit,
//    onRetry: () -> Unit,
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
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
        ) {
            if (tasks.isNotEmpty()) {
                TaskList(
                    state = lazyListState,
                    tasks = if (!showCompletedTasks) tasks.filter { it.isCompleted == false } else tasks,
                    onAddNewTaskClick = onAddNewTaskClicked,
                    onEditTaskClick = onEditTaskClick,
                    onMarkItemAsCompleted = onMarkItemAsCompleted,
                    onDeleteItem = onDeleteItem,
                )
            } else {
                NoDataFoundContent(onRetry = onRefresh)
            }
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
            isRefreshing = false,
            onRefresh = {},
            onAddNewTaskClicked = {},
            onEditTaskClick = {},
            onMarkItemAsCompleted = {},
            onDeleteItem = {},
            onChangeTheme = {},
        )
    }
}