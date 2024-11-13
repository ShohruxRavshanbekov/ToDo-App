package uz.futuresoft.tasks.presentation.home.components.todoitem

import androidx.compose.animation.animateContentSize
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import uz.futuresoft.data.models.ToDoItem

@Composable
fun SwipeTodoItemContainer(
    modifier: Modifier = Modifier,
    item: ToDoItem,
    onMarkAsCompleted: (ToDoItem) -> Unit,
    onDelete: (ToDoItem) -> Unit,
    content: @Composable (ToDoItem) -> Unit,
) {
    val dismissState = rememberSwipeToDismissBoxState()

    LaunchedEffect(key1 = dismissState.currentValue) {
        when (dismissState.currentValue) {
            SwipeToDismissBoxValue.StartToEnd -> {
                onMarkAsCompleted(item)
                dismissState.snapTo(SwipeToDismissBoxValue.Settled)
            }

            SwipeToDismissBoxValue.EndToStart -> {
                onDelete(item)
                dismissState.snapTo(SwipeToDismissBoxValue.Settled)
            }

            SwipeToDismissBoxValue.Settled -> {
                dismissState.snapTo(SwipeToDismissBoxValue.Settled)
            }
        }
    }
    SwipeToDismissBox(
        modifier = modifier.animateContentSize(),
        state = dismissState,
        backgroundContent = { TodoItemViewSwipeBackground(dismissState = dismissState) },
        content = { content(item) },
    )
}