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

val AppIcons.Info: ImageVector
    get() {
        if (_info != null) {
            return _info!!
        }
        _info = Builder(
            name = "Info",
            defaultWidth = 24.0.dp,
            defaultHeight = 23.58467682295518.dp,
            viewportWidth = 20.283f,
            viewportHeight = 19.932f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f,
                strokeAlpha = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(20.283f)
                verticalLineToRelative(19.932f)
                horizontalLineToRelative(-20.283f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(19.922f, 9.961f)
                curveTo(19.922f, 15.449f, 15.459f, 19.922f, 9.961f, 19.922f)
                curveTo(4.473f, 19.922f, 0.0f, 15.449f, 0.0f, 9.961f)
                curveTo(0.0f, 4.463f, 4.473f, 0.0f, 9.961f, 0.0f)
                curveTo(15.459f, 0.0f, 19.922f, 4.463f, 19.922f, 9.961f)
                close()
                moveTo(8.369f, 8.154f)
                curveTo(7.959f, 8.154f, 7.637f, 8.477f, 7.637f, 8.867f)
                curveTo(7.637f, 9.287f, 7.959f, 9.59f, 8.369f, 9.59f)
                lineTo(9.443f, 9.59f)
                lineTo(9.443f, 14.18f)
                lineTo(8.203f, 14.18f)
                curveTo(7.783f, 14.18f, 7.461f, 14.502f, 7.461f, 14.893f)
                curveTo(7.461f, 15.313f, 7.783f, 15.615f, 8.203f, 15.615f)
                lineTo(12.295f, 15.615f)
                curveTo(12.715f, 15.615f, 13.037f, 15.313f, 13.037f, 14.893f)
                curveTo(13.037f, 14.502f, 12.715f, 14.18f, 12.295f, 14.18f)
                lineTo(11.055f, 14.18f)
                lineTo(11.055f, 9.072f)
                curveTo(11.055f, 8.525f, 10.781f, 8.154f, 10.264f, 8.154f)
                close()
                moveTo(8.555f, 5.166f)
                curveTo(8.555f, 5.908f, 9.141f, 6.494f, 9.873f, 6.494f)
                curveTo(10.606f, 6.494f, 11.182f, 5.908f, 11.182f, 5.166f)
                curveTo(11.182f, 4.424f, 10.606f, 3.838f, 9.873f, 3.838f)
                curveTo(9.141f, 3.838f, 8.555f, 4.424f, 8.555f, 5.166f)
                close()
            }
        }
            .build()
        return _info!!
    }

private var _info: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Info, contentDescription = null)
    }
}
