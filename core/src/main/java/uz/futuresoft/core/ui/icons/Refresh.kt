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

val AppIcons.Refresh: ImageVector
    get() {
        if (_refresh != null) {
            return _refresh!!
        }
        _refresh = Builder(
            name = "Refresh",
            defaultWidth = 21.047.dp,
            defaultHeight = 28.043.dp,
            viewportWidth = 21.047f,
            viewportHeight = 28.043f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f,
                strokeAlpha = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(21.047f)
                verticalLineToRelative(28.043f)
                horizontalLineToRelative(-21.047f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 14.883f)
                curveTo(0.0f, 20.602f, 4.629f, 25.23f, 10.348f, 25.23f)
                curveTo(16.066f, 25.23f, 20.695f, 20.602f, 20.695f, 14.883f)
                curveTo(20.695f, 14.309f, 20.297f, 13.898f, 19.723f, 13.898f)
                curveTo(19.172f, 13.898f, 18.809f, 14.309f, 18.809f, 14.871f)
                curveTo(18.809f, 19.547f, 15.023f, 23.332f, 10.348f, 23.332f)
                curveTo(5.672f, 23.332f, 1.887f, 19.547f, 1.887f, 14.871f)
                curveTo(1.887f, 10.195f, 5.672f, 6.41f, 10.348f, 6.41f)
                curveTo(11.25f, 6.41f, 12.07f, 6.469f, 12.762f, 6.633f)
                lineTo(9.27f, 10.102f)
                curveTo(9.07f, 10.277f, 9.0f, 10.523f, 9.0f, 10.769f)
                curveTo(9.0f, 11.309f, 9.398f, 11.707f, 9.914f, 11.707f)
                curveTo(10.207f, 11.707f, 10.418f, 11.602f, 10.594f, 11.438f)
                lineTo(15.422f, 6.586f)
                curveTo(15.633f, 6.387f, 15.727f, 6.152f, 15.727f, 5.883f)
                curveTo(15.727f, 5.625f, 15.621f, 5.367f, 15.422f, 5.18f)
                lineTo(10.606f, 0.281f)
                curveTo(10.43f, 0.094f, 10.207f, 0.0f, 9.914f, 0.0f)
                curveTo(9.398f, 0.0f, 9.0f, 0.422f, 9.0f, 0.961f)
                curveTo(9.0f, 1.207f, 9.082f, 1.441f, 9.258f, 1.629f)
                lineTo(12.363f, 4.734f)
                curveTo(11.766f, 4.617f, 11.063f, 4.535f, 10.348f, 4.535f)
                curveTo(4.629f, 4.535f, 0.0f, 9.164f, 0.0f, 14.883f)
                close()
            }
        }
            .build()
        return _refresh!!
    }

private var _refresh: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Refresh, contentDescription = null)
    }
}
