package uz.futuresoft.tasks.presentation.home.components.todoitem

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.PlusCircle
import uz.futuresoft.core.ui.theme.TodoAppTheme

@Composable
fun AddNewTaskItem(
    modifier: Modifier = Modifier,
    onAddNewTaskClick: () -> Unit,
) {
    ListItem(
        modifier = modifier
            .clickable(onClick = onAddNewTaskClick),
        leadingContent = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = AppIcons.PlusCircle,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary
            )
        },
        headlineContent = {
            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = "Новое",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
    )
}


@PreviewLightDark
@Composable
private fun AddNewTaskItemPreview() {
    TodoAppTheme {
        AddNewTaskItem(
            modifier = Modifier,
            onAddNewTaskClick = {}
        )
    }
}