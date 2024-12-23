package uz.futuresoft.todoapp

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewLightDark
import uz.futuresoft.core.ui.theme.TodoAppTheme
import uz.futuresoft.core.utils.AppSharedPreferences
import uz.futuresoft.todoapp.navigation.AppNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppSharedPreferences.create(context = this)
        setContent {
            var darkTheme by remember {
                mutableStateOf(
                    AppSharedPreferences.getBoolean(
                        AppSharedPreferences.KEY_THEME
                    )
                )
            }

            TodoAppTheme(darkTheme = darkTheme) {
                Content(
                    context = this,
                    darkTheme = darkTheme,
                    onChangeTheme = {
                        darkTheme = !darkTheme
                        AppSharedPreferences.write(
                            key = AppSharedPreferences.KEY_THEME,
                            value = darkTheme,
                        )
                    },
                )
            }
        }
    }
}

@Composable
private fun Content(
    context: Context,
    darkTheme: Boolean,
    onChangeTheme: () -> Unit,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        AppNavHost(
            context = context,
            darkTheme = darkTheme,
            onChangeTheme = onChangeTheme,
        )
    }
}

@PreviewLightDark
@Composable
private fun ContentPreview() {
    TodoAppTheme {
        Content(
            context = MainActivity(),
            darkTheme = true,
            onChangeTheme = {},
        )
    }
}