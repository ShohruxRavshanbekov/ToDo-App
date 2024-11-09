package uz.futuresoft.tasks.presentation.task_events.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.tasks.common.models.TodoItemImportance

@Composable
fun TaskPropertiesCard(
    onImportanceChange: (TodoItemImportance) -> Unit,
    onDateSelected: (Long?) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        shape = RoundedCornerShape(16.dp),
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            TaskImportanceView(
                onImportanceChange = onImportanceChange
            )
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 12.dp),
                thickness = 0.5.dp,
            )
            TaskDueDateView(onDateSelected = onDateSelected)
        }
    }
}

@PreviewLightDark
@Composable
private fun TaskPropertiesCardPreview() {
    TodoAppTheme {
        TaskPropertiesCard(
            onImportanceChange = {},
            onDateSelected = {},
        )
    }
}