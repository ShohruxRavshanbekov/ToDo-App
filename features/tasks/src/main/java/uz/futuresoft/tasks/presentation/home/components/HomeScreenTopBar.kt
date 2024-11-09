@file:OptIn(ExperimentalMaterial3Api::class)

package uz.futuresoft.tasks.presentation.home.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.Moon
import uz.futuresoft.core.ui.theme.TodoAppTheme

@Composable
fun HomeScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onChangeTheme: () -> Unit,
) {
    MediumTopAppBar(
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        title = {
            Text(
                text = "Мои дела",
                style = if (scrollBehavior.state.collapsedFraction == 0.0f) {
                    MaterialTheme.typography.displayMedium
                } else {
                    MaterialTheme.typography.titleLarge
                }
            )
        },
        actions = {
            IconButton(onClick = onChangeTheme) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = AppIcons.Moon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.surfaceTint,
                )
            }
        },
    )
}

@PreviewLightDark
@Composable
private fun HomeScreenTopBarPreview() {
    TodoAppTheme {
        HomeScreenTopBar(
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
                rememberTopAppBarState()
            ),
            onChangeTheme = {}
        )
    }
}