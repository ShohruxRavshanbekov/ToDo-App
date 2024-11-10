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

val AppIcons.PlusCircle: ImageVector
    get() {
        if (_plusCircle != null) {
            return _plusCircle!!
        }
        _plusCircle = Builder(
            name = "PlusCircle",
            defaultWidth = 24.0.dp,
            defaultHeight = 23.663616126638637.dp,
            viewportWidth = 24.258f,
            viewportHeight = 23.918f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f,
                strokeAlpha = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(24.258f)
                verticalLineToRelative(23.918f)
                horizontalLineToRelative(-24.258f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(23.906f, 11.953f)
                curveTo(23.906f, 18.539f, 18.551f, 23.906f, 11.953f, 23.906f)
                curveTo(5.367f, 23.906f, 0.0f, 18.539f, 0.0f, 11.953f)
                curveTo(0.0f, 5.355f, 5.367f, 0.0f, 11.953f, 0.0f)
                curveTo(18.551f, 0.0f, 23.906f, 5.355f, 23.906f, 11.953f)
                close()
                moveTo(10.934f, 7.266f)
                lineTo(10.934f, 10.934f)
                lineTo(7.266f, 10.934f)
                curveTo(6.656f, 10.934f, 6.234f, 11.356f, 6.234f, 11.965f)
                curveTo(6.234f, 12.563f, 6.656f, 12.961f, 7.266f, 12.961f)
                lineTo(10.934f, 12.961f)
                lineTo(10.934f, 16.641f)
                curveTo(10.934f, 17.238f, 11.344f, 17.672f, 11.941f, 17.672f)
                curveTo(12.551f, 17.672f, 12.973f, 17.25f, 12.973f, 16.641f)
                lineTo(12.973f, 12.961f)
                lineTo(16.652f, 12.961f)
                curveTo(17.25f, 12.961f, 17.684f, 12.563f, 17.684f, 11.965f)
                curveTo(17.684f, 11.356f, 17.25f, 10.934f, 16.652f, 10.934f)
                lineTo(12.973f, 10.934f)
                lineTo(12.973f, 7.266f)
                curveTo(12.973f, 6.656f, 12.551f, 6.223f, 11.941f, 6.223f)
                curveTo(11.344f, 6.223f, 10.934f, 6.656f, 10.934f, 7.266f)
                close()
            }
        }
            .build()
        return _plusCircle!!
    }

private var _plusCircle: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.PlusCircle, contentDescription = null)
    }
}
