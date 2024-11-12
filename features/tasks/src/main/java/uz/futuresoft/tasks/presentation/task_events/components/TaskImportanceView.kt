package uz.futuresoft.tasks.presentation.task_events.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.tasks.utils.TodoItemImportance

@Composable
fun TaskImportanceView(
    importance: TodoItemImportance,
    onImportanceChange: (TodoItemImportance) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Важность",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        TodoItemImportanceView(
            importance = importance,
            onImportanceChange = onImportanceChange,
        )
    }
}

@PreviewLightDark
@Composable
private fun TaskImportanceViewPreview() {
    TodoAppTheme {
        TaskImportanceView(
            importance = TodoItemImportance.NORMAL,
            onImportanceChange = {},
        )
    }
}