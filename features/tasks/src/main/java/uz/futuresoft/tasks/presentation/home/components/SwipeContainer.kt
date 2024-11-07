package uz.futuresoft.tasks.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import uz.futuresoft.tasks.domain.models.ToDoItem

@Composable
fun SwipeContainer(
    modifier: Modifier = Modifier,
    item: ToDoItem,
    onMarkAsCompleted: (ToDoItem) -> Unit,
    onDelete: (ToDoItem) -> Unit,
    content: @Composable (ToDoItem) -> Unit,
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