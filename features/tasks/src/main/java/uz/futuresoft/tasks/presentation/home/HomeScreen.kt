@file:OptIn(ExperimentalMaterial3Api::class)

package uz.futuresoft.tasks.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.SwipeToDismissBoxValue.EndToStart
import androidx.compose.material3.SwipeToDismissBoxValue.Settled
import androidx.compose.material3.SwipeToDismissBoxValue.StartToEnd
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import uz.futuresoft.navigation.Routes
import uz.futuresoft.tasks.common.models.Importance
import uz.futuresoft.tasks.domain.models.ToDoItem
import uz.futuresoft.tasks.domain.repository.TodoItemsRepository
import uz.futuresoft.tasks.presentation.home.components.CompletedTasksInfo
import uz.futuresoft.tasks.presentation.home.components.SwipeBackground
import uz.futuresoft.tasks.presentation.home.components.SwipeContainer
import uz.futuresoft.tasks.presentation.home.components.ToDoItem
import uz.futuresoft.ui.components.HorizontalSpacer
import uz.futuresoft.ui.icons.AppIcons
import uz.futuresoft.ui.icons.Moon
import uz.futuresoft.ui.icons.Plus
import uz.futuresoft.ui.icons.PlusCircle
import uz.futuresoft.ui.theme.ToDoAppTheme
import java.util.Calendar

@Composable
fun HomeScreen(
    navHostController: NavHostController,
) {
    val todoItemsRepository = TodoItemsRepository()
    val viewModel = HomeViewModel(toDoItemsRepository = todoItemsRepository)
    val tasks = viewModel.getTasks().toMutableStateList()

    val scrollBehavior =
        TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
    var showCompletedTasks by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                title = {
                    Text(
                        text = "Мои дела",
                        style = if (scrollBehavior.state.collapsedFraction == 0.0f) MaterialTheme.typography.displayMedium
                        else MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            imageVector = AppIcons.Moon,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.surfaceTint,
                        )
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary,
                elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 8.dp),
                onClick = { navHostController.navigate(Routes.TaskEvents()) },
            ) {
                Icon(imageVector = AppIcons.Plus, contentDescription = null)
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        ) {
            item {
                CompletedTasksInfo(
                    showCompletedTasks = showCompletedTasks,
                    onShowCompletedTasksClick = { showCompletedTasks = it }
                )
            }
            items(items = tasks, key = { it.id + "/" }) { task ->
                SwipeContainer(
                    modifier = Modifier.animateItem(),
                    item = task,
                    onMarkAsCompleted = { viewModel.markAsCompleted(task = it) },
                    onDelete = { viewModel.removeTask(task = it) },
                ) {
                    ToDoItem(
                        task = task,
                        modifier = Modifier
                            .clip(
                                when (task) {
                                    tasks.first() -> RoundedCornerShape(
                                        topStart = 16.dp,
                                        topEnd = 16.dp
                                    )

                                    else -> RoundedCornerShape(0.dp)

                                }
                            ),
                        onInfoClick = {}
                    )
                }
            }
            if (tasks.isNotEmpty()) {
                item {
                    ListItem(
                        modifier = Modifier
                            .clickable { navHostController.navigate(Routes.TaskEvents()) }
                            .clip(
                                RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                            ),
                        leadingContent = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                imageVector = AppIcons.PlusCircle,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.secondary
                            )
                        },
                        headlineContent = {
                            Text(
                                modifier = Modifier.padding(bottom = 2.dp),
                                text = "Новое",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                            )
                        },
                    )
                }
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    ToDoAppTheme {
        HomeScreen(navHostController = NavHostController(LocalContext.current))
    }
}