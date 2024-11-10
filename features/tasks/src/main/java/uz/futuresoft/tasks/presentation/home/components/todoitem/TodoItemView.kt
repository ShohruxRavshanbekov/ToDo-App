package uz.futuresoft.tasks.presentation.home.components.todoitem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.Info
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.tasks.common.models.TodoItemImportance
import uz.futuresoft.tasks.domain.models.ToDoItem
import java.util.Calendar

@Composable
fun TodoItemView(
    task: ToDoItem,
    showDivider: Boolean = true,
    onInfoClick: (String) -> Unit,
) {
    val taskTitleColor = if (task.isCompleted) {
        MaterialTheme.colorScheme.onSurfaceVariant
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.onBackground),
    ) {
        ListItem(
            leadingContent = {
                TodoItemLeadingContent(task = task)
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
                TodoItemSupportingContent(task = task)
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

@PreviewLightDark
@Composable
private fun TodoItemPreview() {
    TodoAppTheme {
        TodoItemView(
            task = ToDoItem(
                id = "t0",
                text = "Посещать лекцию Яндекса :)",
                importance = TodoItemImportance.NORMAL,
                isCompleted = false,
                createdAt = Calendar.getInstance().time
            ),
            onInfoClick = {}
        )
    }
}