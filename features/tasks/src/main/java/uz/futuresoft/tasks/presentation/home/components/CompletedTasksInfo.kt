package uz.futuresoft.tasks.presentation.home.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.futuresoft.ui.icons.AppIcons
import uz.futuresoft.ui.icons.Eye
import uz.futuresoft.ui.icons.EyeSlash
import uz.futuresoft.ui.theme.ToDoAppTheme

@Composable
fun CompletedTasksInfo(
    showCompletedTasks: Boolean,
    onShowCompletedTasksClick: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "Выполнено — 5",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        IconButton(
            onClick = { onShowCompletedTasksClick(!showCompletedTasks) }
        ) {
            Icon(
                imageVector = if (showCompletedTasks) AppIcons.EyeSlash else AppIcons.Eye,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CompletedTasksInfoPreview() {
    ToDoAppTheme {
        CompletedTasksInfo(showCompletedTasks = false, onShowCompletedTasksClick = {})
    }
}