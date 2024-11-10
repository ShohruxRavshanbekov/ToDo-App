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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.Plus
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.navigation.Routes
import uz.futuresoft.tasks.common.models.TodoItemImportance
import uz.futuresoft.tasks.domain.models.ToDoItem
import uz.futuresoft.tasks.domain.repository.TodoItemsRepository
import uz.futuresoft.tasks.presentation.home.components.HomeScreenTopBar
import uz.futuresoft.tasks.presentation.home.components.TaskList
import java.util.Calendar
import java.util.UUID

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    todoItemsRepository: TodoItemsRepository,
    darkTheme: Boolean,
    onChangeTheme: () -> Unit,
) {
    val viewModel by remember { mutableStateOf(HomeViewModel(todoItemsRepository = todoItemsRepository)) }
    viewModel.getTasks()
    val tasks by viewModel.tasks.collectAsState()

    HomeScreenContent(
        tasks = tasks,
        darkTheme = darkTheme,
        onAddNewTaskClicked = { navHostController.navigate(Routes.TaskDetails(taskId = null)) },
        onEditTaskClick = { navHostController.navigate(Routes.TaskDetails(taskId = it)) },
        onDeleteItem = { viewModel.removeTask(id = it.id) },
        onChangeTheme = onChangeTheme,
        onMarkItemAsCompleted = {
            viewModel.markAsCompleted(
                id = it.id,
                task = ToDoItem(
                    id = it.id,
                    text = it.text,
                    createdAt = it.createdAt,
                    importance = it.importance,
                    isCompleted = true,
                    deadline = it.deadline,
                    modifiedAt = it.modifiedAt,
                )
            )
        }
    )
}

@Composable
private fun HomeScreenContent(
    tasks: List<ToDoItem>,
    darkTheme: Boolean,
    onAddNewTaskClicked: () -> Unit,
    onEditTaskClick: (String) -> Unit,
    onMarkItemAsCompleted: (ToDoItem) -> Unit,
    onDeleteItem: (ToDoItem) -> Unit,
    onChangeTheme: () -> Unit,
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    var showCompletedTasks by remember { mutableStateOf(false) }
    val lazyListState = rememberLazyListState()
    val lazyListFirstVisibleItemScrollOffset by
    remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset } }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeScreenTopBar(
                scrollBehavior = scrollBehavior,
                darkTheme = darkTheme,
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
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        TaskList(
            state = lazyListState,
            tasks = if (!showCompletedTasks) tasks.filter { !it.isCompleted } else tasks,
            modifier = Modifier.padding(innerPadding),
            onAddNewTaskClick = onAddNewTaskClicked,
            onEditTaskClick = onEditTaskClick,
            onMarkItemAsCompleted = onMarkItemAsCompleted,
            onDeleteItem = onDeleteItem,
        )
    }
}

@PreviewLightDark
@Composable
private fun HomeScreenPreview() {
    TodoAppTheme {
        HomeScreenContent(
            tasks = listOf(
                ToDoItem(
                    id = UUID.randomUUID().toString(),
                    text = "Делать уроки",
                    importance = TodoItemImportance.NORMAL,
                    isCompleted = false,
                    createdAt = Calendar.getInstance().time
                ),
                ToDoItem(
                    id = UUID.randomUUID().toString(),
                    text = "Играть футбол",
                    importance = TodoItemImportance.LOW,
                    isCompleted = false,
                    createdAt = Calendar.getInstance().time
                ),
                ToDoItem(
                    id = UUID.randomUUID().toString(),
                    text = "Посещать лекцию Яндекса :)",
                    importance = TodoItemImportance.HIGH,
                    isCompleted = false,
                    createdAt = Calendar.getInstance().time
                ),
            ),
            darkTheme = false,
            onAddNewTaskClicked = {},
            onEditTaskClick = {},
            onMarkItemAsCompleted = {},
            onDeleteItem = {},
            onChangeTheme = {},
        )
    }
}