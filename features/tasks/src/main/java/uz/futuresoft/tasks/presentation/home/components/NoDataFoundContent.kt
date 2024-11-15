package uz.futuresoft.tasks.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.components.VerticalSpacer
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.Plus
import uz.futuresoft.core.ui.icons.Refresh
import uz.futuresoft.core.ui.images.AppImages
import uz.futuresoft.core.ui.images.NoDataFound
import uz.futuresoft.core.ui.theme.TodoAppTheme

@Composable
fun NoDataFoundContent(
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = AppImages.NoDataFound,
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(300.dp)
        )
        VerticalSpacer(height = 16.dp)
        Text(
            text = "Данные не найдены.",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )
        VerticalSpacer(height = 16.dp)
        IconButton(onClick = onRetry) {
            Icon(
                imageVector = AppIcons.Refresh,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.secondary,
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun NoDataFoundContentPreview() {
    TodoAppTheme {
        NoDataFoundContent(onRetry = {})
    }
}