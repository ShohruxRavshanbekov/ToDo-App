package uz.futuresoft.tasks.presentation.task_events.components

import android.content.res.Configuration
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import uz.futuresoft.tasks.common.models.Importance
import uz.futuresoft.ui.icons.AppIcons
import uz.futuresoft.ui.icons.PriorityHigh
import uz.futuresoft.ui.icons.PriorityLow
import uz.futuresoft.ui.theme.ToDoAppTheme

@Composable
fun Importance(
    importance: Importance = Importance.NORMAL,
    onSelectImportance: (Importance) -> Unit,
) {
    var taskImportance by remember { mutableStateOf(importance) }
    val offsetTarget = when (taskImportance) {
        Importance.LOW -> IntOffset(x = -90, y = 0)
        Importance.HIGH -> IntOffset(x = 90, y = 0)
        Importance.NORMAL -> IntOffset(x = 0, y = 0)
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
                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            taskImportance = Importance.LOW
                            onSelectImportance(taskImportance)
                        },
                    imageVector = AppIcons.PriorityLow,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surfaceTint
                )
                VerticalDivider(
                    modifier = Modifier.height(18.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outline,
                )
                Text(
                    modifier = Modifier.clickable {
                        taskImportance = Importance.NORMAL
                        onSelectImportance(taskImportance)
                    },
                    text = "нет",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W500),
                    color = MaterialTheme.colorScheme.onSurface,
                )
                VerticalDivider(
                    modifier = Modifier.height(18.dp),
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outline,
                )
                Icon(
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            taskImportance = Importance.HIGH
                            onSelectImportance(taskImportance)
                        },
                    imageVector = AppIcons.PriorityHigh,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ImportancePreview() {
    ToDoAppTheme {
        Importance(onSelectImportance = {})
    }
}