package uz.futuresoft.tasks.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.PlusCircle
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.tasks.domain.models.TodoItem
import uz.futuresoft.tasks.presentation.home.components.todoitem.SwipeTodoItemContainer
import uz.futuresoft.tasks.presentation.home.components.todoitem.TodoItemView

@Composable
fun TaskList(
    tasks: List<TodoItem>,
    modifier: Modifier = Modifier,
    showCompletedTasks: Boolean = false,
    onShowCompletedTaskClick: (Boolean) -> Unit = {},
    onAddItemClick: () -> Unit = {},
    onMarkItemAsCompleted: (TodoItem) -> Unit = {},
    onDeleteItem: (TodoItem) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        item {
            CompletedTasksInfo(
                showCompletedTasks = showCompletedTasks,
                onShowCompletedTasksClick = onShowCompletedTaskClick
            )
        }
        items(items = tasks, key = { it.id + "/" }) { task ->
            SwipeTodoItemContainer(
                modifier = Modifier.animateItem(),
                item = task,
                onMarkAsCompleted = onMarkItemAsCompleted,
                onDelete = onDeleteItem,
            ) {
                TodoItemView(
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
                        .clickable(onClick = onAddItemClick)
                        .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)),
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

@PreviewLightDark
@Composable
private fun TaskListPreview() {
    TodoAppTheme {
        TaskList(
            tasks = emptyList()
        )
    }
}