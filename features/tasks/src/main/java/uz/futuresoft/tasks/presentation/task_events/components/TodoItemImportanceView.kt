package uz.futuresoft.tasks.presentation.task_events.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.PriorityHigh
import uz.futuresoft.core.ui.icons.PriorityLow
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.tasks.utils.TodoItemImportance

@Composable
fun TodoItemImportanceView(
    importance: String,
    onImportanceChange: (String) -> Unit,
) {
    val importanceItemHeight = 32.dp
    val importanceItemWidth = 48.dp

    var taskImportance by remember { mutableStateOf(importance) }
    val offsetX = animateDpAsState(
        targetValue = when (taskImportance) {
            TodoItemImportance.LOW.value -> -importanceItemWidth
            TodoItemImportance.NORMAL.value -> 0.dp
            TodoItemImportance.HIGH.value -> importanceItemWidth
            else -> 0.dp
        },
        animationSpec = tween(durationMillis = 300),
        label = "offset"
    )

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(8.dp),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .offset(x = offsetX.value, y = 0.dp)
                    .size(height = importanceItemHeight, width = importanceItemWidth)
                    .padding(2.dp)
                    .clip(shape = RoundedCornerShape(7.dp))
                    .background(color = MaterialTheme.colorScheme.surface),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    modifier = Modifier.size(
                        height = importanceItemHeight,
                        width = importanceItemWidth
                    ),
                    onClick = {
                        taskImportance = TodoItemImportance.LOW.value
                        onImportanceChange(taskImportance)
                    }
                ) {
                    Icon(
                        imageVector = AppIcons.PriorityLow,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                }
                VerticalDivider(
                    modifier = Modifier.height(18.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outline,
                )
                TextButton(
                    modifier = Modifier.size(
                        height = importanceItemHeight,
                        width = importanceItemWidth
                    ),
                    onClick = {
                        taskImportance = TodoItemImportance.NORMAL.value
                        onImportanceChange(taskImportance)
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(
                        text = "нет",
                        style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.W500),
                    )
                }
                VerticalDivider(
                    modifier = Modifier.height(18.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outline,
                )
                IconButton(
                    modifier = Modifier.size(
                        height = importanceItemHeight,
                        width = importanceItemWidth
                    ),
                    onClick = {
                        taskImportance = TodoItemImportance.HIGH.value
                        onImportanceChange(taskImportance)
                    }
                ) {
                    Icon(
                        imageVector = AppIcons.PriorityHigh,
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }
            }
        }
    }
}

@PreviewLightDark
@Composable
private fun ToDoItemImportanceViewPreview() {
    TodoAppTheme {
        TodoItemImportanceView(
            importance = TodoItemImportance.NORMAL.value,
            onImportanceChange = {},
        )
    }
}