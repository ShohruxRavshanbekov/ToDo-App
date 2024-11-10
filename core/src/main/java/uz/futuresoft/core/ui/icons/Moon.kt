package uz.futuresoft.core.ui.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val AppIcons.Moon: ImageVector
    get() {
        if (_moon != null) {
            return _moon!!
        }
        _moon = Builder(
            name = "Moon",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.084221337784054.dp,
            viewportWidth = 23.367f,
            viewportHeight = 23.449f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f,
                strokeAlpha = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(23.367f)
                verticalLineToRelative(23.449f)
                horizontalLineToRelative(-23.367f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(18.129f, 15.902f)
                curveTo(11.789f, 15.902f, 7.734f, 11.93f, 7.734f, 5.578f)
                curveTo(7.734f, 4.125f, 8.051f, 2.355f, 8.473f, 1.383f)
                curveTo(8.578f, 1.113f, 8.602f, 0.938f, 8.602f, 0.832f)
                curveTo(8.602f, 0.527f, 8.367f, 0.176f, 7.898f, 0.176f)
                curveTo(7.77f, 0.176f, 7.488f, 0.199f, 7.219f, 0.305f)
                curveTo(2.906f, 2.027f, 0.0f, 6.574f, 0.0f, 11.332f)
                curveTo(0.0f, 18.023f, 5.426f, 23.449f, 12.117f, 23.449f)
                curveTo(17.168f, 23.449f, 21.398f, 20.391f, 22.887f, 16.594f)
                curveTo(22.992f, 16.313f, 23.016f, 16.02f, 23.016f, 15.914f)
                curveTo(23.016f, 15.469f, 22.664f, 15.188f, 22.336f, 15.188f)
                curveTo(22.184f, 15.188f, 22.055f, 15.223f, 21.832f, 15.293f)
                curveTo(20.918f, 15.586f, 19.57f, 15.902f, 18.129f, 15.902f)
                close()
            }
        }
            .build()
        return _moon!!
    }

private var _moon: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Moon, contentDescription = null)
    }
}
