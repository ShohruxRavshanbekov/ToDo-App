package uz.futuresoft.tasks.presentation.home.components.todoitem

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.components.HorizontalSpacer
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.Completed
import uz.futuresoft.core.ui.icons.ImportanceHigh
import uz.futuresoft.core.ui.icons.ImportanceNormal
import uz.futuresoft.core.ui.icons.PriorityHigh
import uz.futuresoft.core.ui.icons.PriorityLow
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.tasks.utils.TodoItemImportance
import uz.futuresoft.tasks.common.models.ToDoItemState
import java.util.Calendar

@Composable
fun TodoItemLeadingContent(
    task: ToDoItemState,
) {
    val taskStateIcon by remember {
        mutableStateOf(
            when {
                task.isCompleted -> AppIcons.Completed
                task.importance == TodoItemImportance.HIGH -> AppIcons.ImportanceHigh
                else -> AppIcons.ImportanceNormal
            }
        )
    }

    val importanceIcon by remember {
        mutableStateOf(
            if (task.importance == TodoItemImportance.LOW) {
                AppIcons.PriorityLow
            } else {
                AppIcons.PriorityHigh
            }
        )
    }

    val taskStateIconTint = when {
        task.isCompleted -> Color.Unspecified
        task.importance == TodoItemImportance.NORMAL || task.importance == TodoItemImportance.LOW -> MaterialTheme.colorScheme.outline
        else -> Color.Unspecified
    }

    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(24.dp),
            imageVector = taskStateIcon,
            contentDescription = null,
            tint = taskStateIconTint
        )
        if (!task.isCompleted && task.importance != TodoItemImportance.NORMAL) {
            HorizontalSpacer(width = 5.dp)
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = importanceIcon,
                contentDescription = null,
                tint = taskStateIconTint
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun TodoItemLeadingContentPreview() {
    TodoAppTheme {
        TodoItemLeadingContent(
            task = ToDoItemState(
                id = "t0",
                text = "Посещать лекцию Яндекса :)",
                importance = TodoItemImportance.NORMAL,
                isCompleted = false,
                createdAt = Calendar.getInstance().time
            ),
        )
    }
}