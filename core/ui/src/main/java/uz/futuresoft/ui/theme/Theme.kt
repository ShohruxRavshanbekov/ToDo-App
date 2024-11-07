package uz.futuresoft.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Green_Light,
    onPrimary = White,
    secondary = Blue_Light,
    onSecondary = White,
    tertiary = Gray_Light_Light,
    onTertiary = White,
    background = Background_Primary_Light,
    onBackground = Background_Secondary_Light,
    surface = Background_Secondary_Light,
    onSurface = Label_Primary_Black,
    onSurfaceVariant = Label_Tertiary_Black30,
    surfaceVariant = Overlay_Black6,
    surfaceTint = Gray_Light,
    error = Red_Light,
    onError = White,
    outline = Separator_Black20,
)

private val DarkColorScheme = darkColorScheme(
    primary = Green_Dark,
    onPrimary = White,
    secondary = Blue_Dark,
    onSecondary = White,
    tertiary = Gray_Light_Dark,
    onTertiary = White,
    background = Background_Primary_Dark,
    onBackground = Background_Secondary_Dark,
    surface = Background_Secondary_Dark,
    onSurface = Label_Primary_White,
    onSurfaceVariant = Label_Tertiary_White40,
    surfaceTint = Gray_Dark,
    surfaceVariant = Overlay_Black32,
    error = Red_Dark,
    onError = White,
    outline = Separator_White20,
)

@Composable
fun ToDoAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}