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

val AppIcons.EyeSlash: ImageVector
    get() {
        if (_eyeSlash != null) {
            return _eyeSlash!!
        }
        _eyeSlash = Builder(
            name = "EyeSlash",
            defaultWidth = 24.0.dp,
            defaultHeight = 18.43130662183618.dp,
            viewportWidth = 32.121f,
            viewportHeight = 24.668f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f,
                strokeAlpha = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(32.121f)
                verticalLineToRelative(24.668f)
                horizontalLineToRelative(-32.121f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(10.022f, 9.429f)
                curveTo(9.583f, 10.3f, 9.34f, 11.285f, 9.34f, 12.328f)
                curveTo(9.34f, 15.938f, 12.269f, 18.879f, 15.891f, 18.879f)
                curveTo(16.934f, 18.879f, 17.921f, 18.633f, 18.794f, 18.191f)
                lineTo(21.84f, 21.233f)
                curveTo(20.044f, 21.89f, 18.044f, 22.289f, 15.891f, 22.289f)
                curveTo(6.621f, 22.289f, 0.0f, 14.695f, 0.0f, 12.328f)
                curveTo(0.0f, 10.94f, 2.254f, 7.781f, 5.984f, 5.396f)
                close()
                moveTo(31.77f, 12.328f)
                curveTo(31.77f, 13.675f, 29.668f, 16.714f, 26.123f, 19.08f)
                lineTo(21.922f, 14.882f)
                curveTo(22.256f, 14.097f, 22.441f, 13.233f, 22.441f, 12.328f)
                curveTo(22.441f, 8.707f, 19.5f, 5.777f, 15.891f, 5.777f)
                curveTo(14.982f, 5.777f, 14.117f, 5.962f, 13.331f, 6.296f)
                lineTo(10.336f, 3.303f)
                curveTo(12.032f, 2.717f, 13.901f, 2.367f, 15.891f, 2.367f)
                curveTo(25.266f, 2.367f, 31.77f, 9.949f, 31.77f, 12.328f)
                close()
                moveTo(16.698f, 16.098f)
                curveTo(16.438f, 16.154f, 16.168f, 16.184f, 15.891f, 16.184f)
                curveTo(13.758f, 16.184f, 12.035f, 14.461f, 12.035f, 12.328f)
                curveTo(12.035f, 12.052f, 12.064f, 11.783f, 12.12f, 11.525f)
                close()
                moveTo(19.746f, 12.328f)
                lineTo(19.728f, 12.689f)
                lineTo(15.527f, 8.491f)
                curveTo(15.646f, 8.478f, 15.768f, 8.473f, 15.891f, 8.473f)
                curveTo(18.023f, 8.473f, 19.746f, 10.195f, 19.746f, 12.328f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(25.043f, 22.488f)
                curveTo(25.395f, 22.852f, 25.957f, 22.875f, 26.32f, 22.488f)
                curveTo(26.707f, 22.102f, 26.672f, 21.574f, 26.32f, 21.211f)
                lineTo(6.727f, 1.629f)
                curveTo(6.375f, 1.277f, 5.789f, 1.277f, 5.438f, 1.629f)
                curveTo(5.098f, 1.969f, 5.098f, 2.566f, 5.438f, 2.906f)
                close()
            }
        }
            .build()
        return _eyeSlash!!
    }

private var _eyeSlash: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.EyeSlash, contentDescription = null)
    }
}
