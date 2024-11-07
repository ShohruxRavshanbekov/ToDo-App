package uz.futuresoft.tasks.presentation.home.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.futuresoft.tasks.common.models.Importance
import uz.futuresoft.tasks.domain.models.ToDoItem
import uz.futuresoft.ui.components.HorizontalSpacer
import uz.futuresoft.ui.icons.AppIcons
import uz.futuresoft.ui.icons.Calendar
import uz.futuresoft.ui.icons.Completed
import uz.futuresoft.ui.icons.ImportanceHigh
import uz.futuresoft.ui.icons.ImportanceNormal
import uz.futuresoft.ui.icons.Info
import uz.futuresoft.ui.icons.PriorityHigh
import uz.futuresoft.ui.icons.PriorityLow
import uz.futuresoft.ui.theme.ToDoAppTheme
import uz.futuresoft.utils.formatTo
import java.util.Calendar

@Composable
fun ToDoItem(
    task: ToDoItem,
    modifier: Modifier = Modifier,
    showDivider: Boolean = true,
    onInfoClick: (String) -> Unit,
) {
    val taskStateIcon = if (task.isCompleted) {
        AppIcons.Completed
    } else if (task.importance == Importance.HIGH) {
        AppIcons.ImportanceHigh
    } else {
        AppIcons.ImportanceNormal
    }

    val taskStateIconTint = if (task.isCompleted) {
        Color.Unspecified
    } else {
        if (task.importance == Importance.NORMAL || task.importance == Importance.LOW) {
            MaterialTheme.colorScheme.outline
        } else {
            Color.Unspecified
        }
    }

    val taskTitleColor = if (task.isCompleted) {
        MaterialTheme.colorScheme.onSurfaceVariant
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    val importanceIcon = if (task.importance == Importance.LOW) {
        AppIcons.PriorityLow
    } else {
        AppIcons.PriorityHigh

    }

    Column(
        modifier = modifier.background(color = MaterialTheme.colorScheme.onBackground),
    ) {
        ListItem(
            leadingContent = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = taskStateIcon,
                        contentDescription = null,
                        tint = taskStateIconTint
                    )
                    if (!task.isCompleted && task.importance != Importance.NORMAL) {
                        HorizontalSpacer(width = 5.dp)
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = importanceIcon,
                            contentDescription = null,
                            tint = taskStateIconTint
                        )
                    }
                }
            },
            headlineContent = {
                Text(
                    modifier = Modifier.padding(bottom = 2.dp),
                    text = task.text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = taskTitleColor,
                    textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            },
            supportingContent = {
                if (task.deadline != null && !task.isCompleted) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            modifier = Modifier
                                .size(16.dp)
                                .padding(top = 2.dp, end = 2.dp, bottom = 2.dp),
                            imageVector = AppIcons.Calendar,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Text(
                            text = task.deadline.formatTo(pattern = "dd MMM"),
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            },
            trailingContent = {
                IconButton(
                    modifier = Modifier.size(20.dp),
                    onClick = {
                        onInfoClick(task.id)
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = AppIcons.Info,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surfaceTint,
                    )
                }
            },
        )
        if (showDivider) {
            HorizontalDivider(modifier = Modifier.padding(start = 50.dp))
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ToDoItemPreview() {
    ToDoAppTheme {
        ToDoItem(
            task = ToDoItem(
                id = "t0",
                text = "Посещать лекцию Яндекса :)",
                importance = Importance.NORMAL,
                isCompleted = false,
                createdAt = Calendar.getInstance().time
            ),
            onInfoClick = {}
        )
    }
}