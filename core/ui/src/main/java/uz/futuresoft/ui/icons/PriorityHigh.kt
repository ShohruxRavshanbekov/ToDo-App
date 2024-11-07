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

val AppIcons.PriorityHigh: ImageVector
    get() {
        if (_priorityHigh != null) {
            return _priorityHigh!!
        }
        _priorityHigh = Builder(
            name = "PriorityHigh",
            defaultWidth = 18.285714285714285.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 16.0f,
            viewportHeight = 21.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFFF3B30)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(4.695f, 13.288f)
                curveTo(5.522f, 13.288f, 5.976f, 12.778f, 6.001f, 11.899f)
                lineTo(6.147f, 4.087f)
                curveTo(6.155f, 3.97f, 6.163f, 3.845f, 6.163f, 3.753f)
                curveTo(6.163f, 2.808f, 5.587f, 2.247f, 4.695f, 2.247f)
                curveTo(3.803f, 2.247f, 3.219f, 2.808f, 3.219f, 3.753f)
                curveTo(3.219f, 3.845f, 3.227f, 3.97f, 3.227f, 4.087f)
                lineTo(3.381f, 11.899f)
                curveTo(3.414f, 12.778f, 3.86f, 13.288f, 4.695f, 13.288f)
                close()
                moveTo(11.321f, 13.288f)
                curveTo(12.148f, 13.288f, 12.611f, 12.778f, 12.635f, 11.899f)
                lineTo(12.781f, 4.087f)
                curveTo(12.781f, 3.97f, 12.789f, 3.845f, 12.789f, 3.753f)
                curveTo(12.789f, 2.808f, 12.221f, 2.247f, 11.321f, 2.247f)
                curveTo(10.429f, 2.247f, 9.845f, 2.808f, 9.845f, 3.753f)
                curveTo(9.845f, 3.845f, 9.853f, 3.97f, 9.861f, 4.087f)
                lineTo(10.015f, 11.899f)
                curveTo(10.04f, 12.778f, 10.486f, 13.288f, 11.321f, 13.288f)
                close()
                moveTo(4.687f, 18.247f)
                curveTo(5.62f, 18.247f, 6.374f, 17.511f, 6.374f, 16.583f)
                curveTo(6.374f, 15.655f, 5.62f, 14.91f, 4.687f, 14.91f)
                curveTo(3.754f, 14.91f, 3.0f, 15.655f, 3.0f, 16.583f)
                curveTo(3.0f, 17.511f, 3.754f, 18.247f, 4.687f, 18.247f)
                close()
                moveTo(11.321f, 18.247f)
                curveTo(12.246f, 18.247f, 13.0f, 17.511f, 13.0f, 16.583f)
                curveTo(13.0f, 15.655f, 12.246f, 14.91f, 11.321f, 14.91f)
                curveTo(10.389f, 14.91f, 9.626f, 15.655f, 9.626f, 16.583f)
                curveTo(9.626f, 17.511f, 10.389f, 18.247f, 11.321f, 18.247f)
                close()
            }
        }
            .build()
        return _priorityHigh!!
    }

private var _priorityHigh: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.PriorityHigh, contentDescription = null)
    }
}
