package uz.futuresoft.tasks.presentation.home.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.futuresoft.ui.icons.AppIcons
import uz.futuresoft.ui.icons.Checkmark
import uz.futuresoft.ui.icons.Trash
import uz.futuresoft.ui.theme.ToDoAppTheme

@Composable
fun SwipeBackground(
    dismissState: SwipeToDismissBoxState,
) {
    val color by animateColorAsState(
        targetValue = when (dismissState.targetValue) {
            SwipeToDismissBoxValue.Settled -> Color.Transparent
            SwipeToDismissBoxValue.StartToEnd -> MaterialTheme.colorScheme.primary
            SwipeToDismissBoxValue.EndToStart -> MaterialTheme.colorScheme.error
        },
        label = ""
    )
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color)
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = AppIcons.Checkmark,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
        )
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = AppIcons.Trash,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onError,
        )
    }
}

@Preview
@Composable
private fun SwipeBackgroundPreview() {
    ToDoAppTheme {
        SwipeBackground(dismissState = rememberSwipeToDismissBoxState())
    }
}