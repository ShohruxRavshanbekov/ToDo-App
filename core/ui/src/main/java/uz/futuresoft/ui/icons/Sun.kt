package uz.futuresoft.ui.icons

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

val AppIcons.Sun: ImageVector
    get() {
        if (_sun != null) {
            return _sun!!
        }
        _sun = Builder(
            name = "Sun",
            defaultWidth = 24.0.dp,
            defaultHeight = 23.759056772637063.dp,
            viewportWidth = 25.699f,
            viewportHeight = 25.441f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f,
                strokeAlpha = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(25.699f)
                verticalLineToRelative(25.441f)
                horizontalLineToRelative(-25.699f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(12.68f, 4.559f)
                curveTo(13.231f, 4.559f, 13.688f, 4.102f, 13.688f, 3.539f)
                lineTo(13.688f, 1.02f)
                curveTo(13.688f, 0.457f, 13.231f, 0.0f, 12.68f, 0.0f)
                curveTo(12.117f, 0.0f, 11.66f, 0.457f, 11.66f, 1.02f)
                lineTo(11.66f, 3.539f)
                curveTo(11.66f, 4.102f, 12.117f, 4.559f, 12.68f, 4.559f)
                close()
                moveTo(18.422f, 6.961f)
                curveTo(18.82f, 7.348f, 19.465f, 7.359f, 19.863f, 6.961f)
                lineTo(21.656f, 5.168f)
                curveTo(22.043f, 4.781f, 22.043f, 4.125f, 21.656f, 3.727f)
                curveTo(21.27f, 3.34f, 20.613f, 3.34f, 20.227f, 3.727f)
                lineTo(18.422f, 5.531f)
                curveTo(18.035f, 5.918f, 18.035f, 6.574f, 18.422f, 6.961f)
                close()
                moveTo(20.801f, 12.715f)
                curveTo(20.801f, 13.266f, 21.27f, 13.723f, 21.82f, 13.723f)
                lineTo(24.34f, 13.723f)
                curveTo(24.891f, 13.723f, 25.348f, 13.266f, 25.348f, 12.715f)
                curveTo(25.348f, 12.164f, 24.891f, 11.695f, 24.34f, 11.695f)
                lineTo(21.82f, 11.695f)
                curveTo(21.27f, 11.695f, 20.801f, 12.164f, 20.801f, 12.715f)
                close()
                moveTo(18.422f, 18.469f)
                curveTo(18.035f, 18.867f, 18.035f, 19.512f, 18.422f, 19.898f)
                lineTo(20.227f, 21.715f)
                curveTo(20.613f, 22.102f, 21.27f, 22.078f, 21.656f, 21.703f)
                curveTo(22.043f, 21.305f, 22.043f, 20.66f, 21.656f, 20.273f)
                lineTo(19.852f, 18.469f)
                curveTo(19.465f, 18.082f, 18.82f, 18.094f, 18.422f, 18.469f)
                close()
                moveTo(12.68f, 20.871f)
                curveTo(12.117f, 20.871f, 11.66f, 21.328f, 11.66f, 21.879f)
                lineTo(11.66f, 24.41f)
                curveTo(11.66f, 24.961f, 12.117f, 25.418f, 12.68f, 25.418f)
                curveTo(13.231f, 25.418f, 13.688f, 24.961f, 13.688f, 24.41f)
                lineTo(13.688f, 21.879f)
                curveTo(13.688f, 21.328f, 13.231f, 20.871f, 12.68f, 20.871f)
                close()
                moveTo(6.926f, 18.469f)
                curveTo(6.527f, 18.094f, 5.871f, 18.082f, 5.484f, 18.469f)
                lineTo(3.691f, 20.262f)
                curveTo(3.305f, 20.648f, 3.305f, 21.293f, 3.68f, 21.691f)
                curveTo(4.066f, 22.066f, 4.734f, 22.09f, 5.121f, 21.703f)
                lineTo(6.914f, 19.898f)
                curveTo(7.301f, 19.512f, 7.301f, 18.867f, 6.926f, 18.469f)
                close()
                moveTo(4.547f, 12.715f)
                curveTo(4.547f, 12.164f, 4.078f, 11.695f, 3.527f, 11.695f)
                lineTo(1.008f, 11.695f)
                curveTo(0.457f, 11.695f, 0.0f, 12.164f, 0.0f, 12.715f)
                curveTo(0.0f, 13.266f, 0.457f, 13.723f, 1.008f, 13.723f)
                lineTo(3.527f, 13.723f)
                curveTo(4.078f, 13.723f, 4.547f, 13.266f, 4.547f, 12.715f)
                close()
                moveTo(6.914f, 6.961f)
                curveTo(7.301f, 6.586f, 7.301f, 5.906f, 6.926f, 5.531f)
                lineTo(5.133f, 3.727f)
                curveTo(4.758f, 3.352f, 4.09f, 3.34f, 3.703f, 3.727f)
                curveTo(3.316f, 4.125f, 3.316f, 4.781f, 3.691f, 5.156f)
                lineTo(5.484f, 6.961f)
                curveTo(5.871f, 7.348f, 6.516f, 7.348f, 6.914f, 6.961f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(12.668f, 18.68f)
                curveTo(15.961f, 18.68f, 18.633f, 16.008f, 18.633f, 12.715f)
                curveTo(18.633f, 9.422f, 15.961f, 6.738f, 12.668f, 6.738f)
                curveTo(9.375f, 6.738f, 6.703f, 9.422f, 6.703f, 12.715f)
                curveTo(6.703f, 16.008f, 9.375f, 18.68f, 12.668f, 18.68f)
                close()
            }
        }
            .build()
        return _sun!!
    }

private var _sun: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Sun, contentDescription = null)
    }
}
