@file:OptIn(ExperimentalMaterial3Api::class)

package uz.futuresoft.tasks.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.collectAsState
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
import uz.futuresoft.tasks.domain.models.TodoItem
import uz.futuresoft.tasks.domain.repository.TodoItemsRepository
import uz.futuresoft.tasks.presentation.home.components.HomeScreenTopBar
import uz.futuresoft.tasks.presentation.home.components.TaskList
import java.util.Calendar
import java.util.UUID

@Composable
fun HomeScreen(
    navHostController: NavHostController,
    todoItemsRepository: TodoItemsRepository,
) {
    val viewModel by remember { mutableStateOf(HomeViewModel(toDoItemsRepository = todoItemsRepository)) }
    val tasks by viewModel.tasks.collectAsState()

    HomeScreenContent(
        tasks = tasks,
        onAddNewTaskClicked = { navHostController.navigate(Routes.TaskDetails()) },
        onMarkItemAsCompleted = { viewModel.markAsCompleted(task = it) },
        onDeleteItem = { viewModel.removeTask(task = it) }
    )
}

@Composable
private fun HomeScreenContent(
    tasks: List<TodoItem>,
    onAddNewTaskClicked: () -> Unit,
    onMarkItemAsCompleted: (TodoItem) -> Unit,
    onDeleteItem: (TodoItem) -> Unit,
) {
    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    var showCompletedTasks by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeScreenTopBar(
                scrollBehavior = scrollBehavior,
                onChangeTheme = {}
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp),
                onClick = onAddNewTaskClicked,
            ) {
                Icon(imageVector = AppIcons.Plus, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        TaskList(
            tasks = tasks,
            modifier = Modifier.padding(innerPadding),
            showCompletedTasks = showCompletedTasks,
            onShowCompletedTaskClick = { showCompletedTasks = it },
            onAddItemClick = onAddNewTaskClicked,
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
                TodoItem(
                    id = UUID.randomUUID().toString(),
                    text = "Делать уроки",
                    importance = TodoItemImportance.NORMAL,
                    isCompleted = false,
                    createdAt = Calendar.getInstance().time
                ),
                TodoItem(
                    id = UUID.randomUUID().toString(),
                    text = "Играть футбол",
                    importance = TodoItemImportance.LOW,
                    isCompleted = false,
                    createdAt = Calendar.getInstance().time
                ),
                TodoItem(
                    id = UUID.randomUUID().toString(),
                    text = "Посещать лекцию Яндекса :)",
                    importance = TodoItemImportance.HIGH,
                    isCompleted = false,
                    createdAt = Calendar.getInstance().time
                ),
            ),
            onAddNewTaskClicked = {},
            onMarkItemAsCompleted = {},
            onDeleteItem = {},
        )
    }
}