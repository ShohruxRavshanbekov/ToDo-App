@file:OptIn(ExperimentalMaterial3Api::class)

package uz.futuresoft.tasks.presentation.home.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import uz.futuresoft.core.ui.components.HorizontalSpacer
import uz.futuresoft.core.ui.components.VerticalSpacer
import uz.futuresoft.core.ui.icons.AppIcons
import uz.futuresoft.core.ui.icons.Eye
import uz.futuresoft.core.ui.icons.EyeSlash
import uz.futuresoft.core.ui.icons.Moon
import uz.futuresoft.core.ui.icons.Sun
import uz.futuresoft.core.ui.theme.TodoAppTheme

@Composable
fun HomeScreenTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    darkTheme: Boolean,
    showCompletedTasksDetailsBar: Boolean,
    completedTasksCount: Int,
    onChangeTheme: () -> Unit,
    showCompletedTasks: Boolean = false,
    onShowCompletedTasksClick: () -> Unit = {},
) {
    var appDarkTheme by remember { mutableStateOf(darkTheme) }

    MediumTopAppBar(
        scrollBehavior = scrollBehavior,
        expandedHeight = if (showCompletedTasksDetailsBar) 150.dp else 112.dp,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
        ),
        title = {
            TitleContent(
                scrollBehavior = scrollBehavior,
                showCompletedTasksDetailsBar = showCompletedTasksDetailsBar,
                completedTasksCount = completedTasksCount,
                showCompletedTasks = showCompletedTasks,
                onShowCompletedTasksClick = onShowCompletedTasksClick
            )
        },
        actions = {
            if (showCompletedTasksDetailsBar && scrollBehavior.state.collapsedFraction == 1.0f) {
                IconButton(
                    onClick = onShowCompletedTasksClick,
                    enabled = completedTasksCount > 0,
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = MaterialTheme.colorScheme.secondary,
                        disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                ) {
                    Icon(
                        imageVector = if (showCompletedTasks) AppIcons.EyeSlash else AppIcons.Eye,
                        contentDescription = null,
                    )
                }
            }
            IconButton(
                onClick = {
                    onChangeTheme()
                    appDarkTheme = !appDarkTheme
                }
            ) {
                AnimatedContent(
                    targetState = appDarkTheme,
                    label = "theme icon"
                ) { darkTheme ->
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = if (darkTheme) AppIcons.Sun else AppIcons.Moon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surfaceTint,
                    )
                }
            }
        },
    )
}

@Composable
fun TitleContent(
    scrollBehavior: TopAppBarScrollBehavior,
    showCompletedTasksDetailsBar: Boolean,
    completedTasksCount: Int,
    showCompletedTasks: Boolean = false,
    onShowCompletedTasksClick: () -> Unit = {},
) {
    Column {
        Text(
            text = "Мои дела",
            style = if (scrollBehavior.state.collapsedFraction == 0.0f) {
                MaterialTheme.typography.displayMedium
            } else {
                MaterialTheme.typography.titleLarge
            }
        )
        if (showCompletedTasksDetailsBar && scrollBehavior.state.collapsedFraction == 0.0f) {
            VerticalSpacer(height = 8.dp)
            CompletedTasksInfo(
                completedTasksCount = completedTasksCount,
                showCompletedTasks = showCompletedTasks,
                onShowCompletedTasksClick = onShowCompletedTasksClick
            )
        }
    }
}

@PreviewLightDark
@Composable
private fun HomeScreenTopBarPreview() {
    TodoAppTheme {
        HomeScreenTopBar(
            scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
                rememberTopAppBarState()
            ),
            darkTheme = false,
            showCompletedTasksDetailsBar = true,
            completedTasksCount = 0,
            onChangeTheme = {},
            showCompletedTasks = false,
            onShowCompletedTasksClick = {},
        )
    }
}