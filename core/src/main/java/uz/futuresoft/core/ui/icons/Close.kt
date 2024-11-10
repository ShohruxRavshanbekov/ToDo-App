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

val AppIcons.Close: ImageVector
    get() {
        if (_close != null) {
            return _close!!
        }
        _close = Builder(
            name = "Close",
            defaultWidth = 24.0.dp,
            defaultHeight = 23.569052020068657.dp,
            viewportWidth = 18.935f,
            viewportHeight = 18.595f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f,
                strokeAlpha = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(18.935f)
                verticalLineToRelative(18.595f)
                horizontalLineToRelative(-18.935f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.303f, 18.292f)
                curveTo(0.713f, 18.69f, 1.393f, 18.69f, 1.792f, 18.292f)
                lineTo(9.292f, 10.792f)
                lineTo(16.792f, 18.292f)
                curveTo(17.19f, 18.69f, 17.881f, 18.702f, 18.28f, 18.292f)
                curveTo(18.678f, 17.881f, 18.678f, 17.213f, 18.28f, 16.815f)
                lineTo(10.78f, 9.303f)
                lineTo(18.28f, 1.803f)
                curveTo(18.678f, 1.405f, 18.69f, 0.725f, 18.28f, 0.327f)
                curveTo(17.87f, -0.083f, 17.19f, -0.083f, 16.792f, 0.327f)
                lineTo(9.292f, 7.827f)
                lineTo(1.792f, 0.327f)
                curveTo(1.393f, -0.083f, 0.702f, -0.095f, 0.303f, 0.327f)
                curveTo(-0.095f, 0.737f, -0.095f, 1.405f, 0.303f, 1.803f)
                lineTo(7.803f, 9.303f)
                lineTo(0.303f, 16.815f)
                curveTo(-0.095f, 17.213f, -0.107f, 17.893f, 0.303f, 18.292f)
                close()
            }
        }
            .build()
        return _close!!
    }

private var _close: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Close, contentDescription = null)
    }
}
