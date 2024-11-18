package uz.futuresoft.tasks.presentation.home.components.todoitem

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.Calendar
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.core.utils.formatTo
import uz.futuresoft.data.models.ToDoItem
import uz.futuresoft.tasks.utils.TodoItemImportance
import java.util.Calendar
import java.util.Date

@Composable
fun TodoItemSupportingContent(
    task: ToDoItem,
) {
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
                text = Date(task.deadline!!).formatTo(pattern = "dd MMM"),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


@PreviewLightDark
@Composable
private fun TodoItemSupportingContentPreview() {
    TodoAppTheme {
        TodoItemSupportingContent(
            task = ToDoItem(
                id = "t0",
                text = "Посещать лекцию Яндекса :)",
                importance = TodoItemImportance.NORMAL.value,
                isCompleted = false,
                createdAt = Calendar.getInstance().timeInMillis,
                modifiedAt = Calendar.getInstance().timeInMillis,
            )
        )
    }
}