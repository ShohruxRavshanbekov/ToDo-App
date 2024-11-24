@file:OptIn(ExperimentalMaterial3Api::class)

package uz.futuresoft.tasks.presentation.task_events.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.components.NetworkStateIndicator
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.ChevronLeft
import uz.futuresoft.core.ui.theme.TodoAppTheme

@Composable
fun TaskDetailsScreenTopBar(
    taskText: String,
    isNetworkAvailable: Boolean?,
    onBackClicked: () -> Unit = {},
    onSaveClicked: () -> Unit = {},
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        NetworkStateIndicator(
            isNetworkAvailable = isNetworkAvailable,
            modifier = Modifier.fillMaxWidth()
        )
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
            ),
            navigationIcon = {
                IconButton(onClick = onBackClicked) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = AppIcons.ChevronLeft,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surfaceTint,
                    )
                }
            },
            title = {},
            actions = {
                TextButton(
                    enabled = taskText.isNotEmpty(),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    onClick = onSaveClicked
                ) {
                    Text(
                        text = "Сохранить",
                        style = MaterialTheme.typography.headlineMedium,
                    )
                }
            }
        )
    }
}

@PreviewLightDark
@Composable
private fun TaskDetailsScreenTopBarPreview() {
    TodoAppTheme {
        TaskDetailsScreenTopBar(taskText = "", isNetworkAvailable = false)
    }
}