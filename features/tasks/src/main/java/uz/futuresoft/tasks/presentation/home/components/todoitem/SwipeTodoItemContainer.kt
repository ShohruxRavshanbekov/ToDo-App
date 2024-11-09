package uz.futuresoft.tasks.presentation.home.components.todoitem

import androidx.compose.animation.animateContentSize
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import uz.futuresoft.tasks.domain.models.TodoItem

@Composable
fun SwipeTodoItemContainer(
    modifier: Modifier = Modifier,
    item: TodoItem,
    onMarkAsCompleted: (TodoItem) -> Unit,
    onDelete: (TodoItem) -> Unit,
    content: @Composable (TodoItem) -> Unit,
) {
    val state = rememberSwipeToDismissBoxState()

    LaunchedEffect(key1 = state.currentValue) {
        when (state.currentValue) {
            SwipeToDismissBoxValue.StartToEnd -> {
                onMarkAsCompleted(item)
            }

            SwipeToDismissBoxValue.EndToStart -> {
                onDelete(item)
            }

            SwipeToDismissBoxValue.Settled -> {}
        }
    }
    SwipeToDismissBox(
        modifier = modifier.animateContentSize(),
        state = state,
        backgroundContent = { SwipeBackground(dismissState = state) },
        content = { content(item) }
    )
}