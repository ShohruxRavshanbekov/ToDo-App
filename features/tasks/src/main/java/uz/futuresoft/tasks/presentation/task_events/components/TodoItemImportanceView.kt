package uz.futuresoft.tasks.presentation.task_events.components

import androidx.compose.animation.core.animateIntOffsetAsState
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.PriorityHigh
import uz.futuresoft.core.ui.icons.PriorityLow
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.tasks.common.models.TodoItemImportance

@Composable
fun TodoItemImportanceView(
    onSelectImportance: (TodoItemImportance) -> Unit,
) {
    var taskImportance by remember { mutableStateOf(TodoItemImportance.NORMAL) }
//    val offsetTarget = when (taskImportance) {
//        Importance.LOW -> IntOffset(x = -90, y = 0)
//        Importance.HIGH -> IntOffset(x = 90, y = 0)
//        Importance.NORMAL -> IntOffset(x = 0, y = 0)
//    }
    val itemHeight = 32.dp
    val itemWidth = 48.dp
    val offsetTarget = remember {
        when (taskImportance) {
            TodoItemImportance.LOW -> IntOffset(x = -125, y = 0)
            TodoItemImportance.NORMAL -> IntOffset(x = 0, y = 0)
            TodoItemImportance.HIGH -> IntOffset(x = 125, y = 0)
        }
    }
    val offset by animateIntOffsetAsState(
        targetValue = offsetTarget,
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
                    .size(height = itemHeight, width = itemWidth)
                    .padding(2.dp)
                    .offset { offset }
                    .clip(shape = RoundedCornerShape(7.dp))
                    .background(color = MaterialTheme.colorScheme.surface),
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    modifier = Modifier.size(height = itemHeight, width = itemWidth),
                    onClick = {
                        taskImportance = TodoItemImportance.LOW
                        onSelectImportance(taskImportance)
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
                    modifier = Modifier.size(height = itemHeight, width = itemWidth),
                    onClick = {
                        taskImportance = TodoItemImportance.NORMAL
                        onSelectImportance(taskImportance)
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
                    modifier = Modifier.size(height = itemHeight, width = itemWidth),
                    onClick = {
                        taskImportance = TodoItemImportance.HIGH
                        onSelectImportance(taskImportance)
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
    /*Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(9.dp),
    ) {
        Box(contentAlignment = Alignment.Center) {
            Box(
                modifier = Modifier
                    .height(35.dp)
                    .width(48.dp)
                    .padding(2.dp)
                    .offset { offset }
                    .clip(shape = RoundedCornerShape(7.dp))
                    .background(color = MaterialTheme.colorScheme.surface),
            )
            Row(
                modifier = Modifier.padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                IconButton(
                    modifier = Modifier.size(20.dp),
                    onClick = {
                        taskImportance = Importance.LOW
                        onSelectImportance(taskImportance)
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
//                    modifier = Modifier.size(20.dp),
                    onClick = {
                        taskImportance = Importance.NORMAL
                        onSelectImportance(taskImportance)
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSurface
                    )
                ) {
                    Text(
                        text = "нет",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W500),
                    )
                }
//                Text(
//                    modifier = Modifier.clickable {
//                        taskImportance = Importance.NORMAL
//                        onSelectImportance(taskImportance)
//                    },
//                    text = "нет",
//                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W500),
//                    color = MaterialTheme.colorScheme.onSurface,
//                )
                VerticalDivider(
                    modifier = Modifier.height(18.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outline,
                )
                IconButton(
                    modifier = Modifier.size(20.dp),
                    onClick = {
                        taskImportance = Importance.HIGH
                        onSelectImportance(taskImportance)
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
    }*/
}

@PreviewLightDark
@Composable
private fun ImportancePreview2() {
    TodoAppTheme {
        val itemHeight = 32.dp
        val itemWidth = 48.dp
        Box(
            modifier = Modifier
                .size(height = itemHeight, width = itemWidth)
//                    .padding(2.dp)
                .clip(shape = RoundedCornerShape(7.dp))
                .background(color = MaterialTheme.colorScheme.surface),
        )
    }
}

@PreviewLightDark
@Composable
private fun ToDoItemImportanceViewPreview() {
    TodoAppTheme {
        TodoItemImportanceView(onSelectImportance = {})
    }
}