package uz.futuresoft.tasks.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.tasks.common.models.TodoItemImportance
import uz.futuresoft.tasks.domain.models.TodoItem
import uz.futuresoft.tasks.presentation.home.components.todoitem.AddNewTaskItem
import uz.futuresoft.tasks.presentation.home.components.todoitem.SwipeTodoItemContainer
import uz.futuresoft.tasks.presentation.home.components.todoitem.TodoItemView
import java.util.Calendar
import java.util.UUID

@Composable
fun TaskList(
    state: LazyListState,
    tasks: List<TodoItem>,
    modifier: Modifier = Modifier,
    onAddNewTaskClick: () -> Unit = {},
    onEditTaskClick: (String) -> Unit = {},
    onMarkItemAsCompleted: (TodoItem) -> Unit = {},
    onDeleteItem: (TodoItem) -> Unit = {},
) {
    LazyColumn(
        state = state,
        modifier = modifier
            .background(color = Color.Transparent)
            .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
            .clip(shape = RoundedCornerShape(16.dp)),
    ) {
        items(items = tasks, key = { it.id }) { task ->
            SwipeTodoItemContainer(
                modifier = Modifier.animateItem(),
                item = task,
                onMarkAsCompleted = onMarkItemAsCompleted,
                onDelete = onDeleteItem,
            ) {
                TodoItemView(
                    task = task,
                    onInfoClick = onEditTaskClick
                )
            }
        }

        if (tasks.isNotEmpty()) {
            item {
                AddNewTaskItem(
                    modifier = Modifier.animateItem(),
                    onAddNewTaskClick = onAddNewTaskClick
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
            state = rememberLazyListState(),
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
        )
    }
}