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

val AppIcons.PriorityLow: ImageVector
    get() {
        if (_priorityLow != null) {
            return _priorityLow!!
        }
        _priorityLow = Builder(
            name = "PriorityLow",
            defaultWidth = 19.42857142857143.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 17.0f,
            viewportHeight = 21.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF8E8E93)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(8.871f, 3.247f)
                curveTo(8.258f, 3.247f, 7.85f, 3.708f, 7.85f, 4.387f)
                verticalLineTo(12.34f)
                lineTo(7.911f, 13.925f)
                lineTo(6.63f, 12.333f)
                lineTo(5.078f, 10.667f)
                curveTo(4.894f, 10.47f, 4.655f, 10.324f, 4.349f, 10.324f)
                curveTo(3.797f, 10.324f, 3.375f, 10.755f, 3.375f, 11.383f)
                curveTo(3.375f, 11.668f, 3.484f, 11.938f, 3.695f, 12.172f)
                lineTo(8.115f, 16.919f)
                curveTo(8.306f, 17.123f, 8.592f, 17.247f, 8.871f, 17.247f)
                curveTo(9.151f, 17.247f, 9.437f, 17.123f, 9.627f, 16.919f)
                lineTo(14.055f, 12.172f)
                curveTo(14.266f, 11.938f, 14.375f, 11.668f, 14.375f, 11.383f)
                curveTo(14.375f, 10.755f, 13.953f, 10.324f, 13.401f, 10.324f)
                curveTo(13.094f, 10.324f, 12.856f, 10.47f, 12.665f, 10.667f)
                lineTo(11.112f, 12.333f)
                lineTo(9.832f, 13.925f)
                lineTo(9.9f, 12.34f)
                verticalLineTo(4.387f)
                curveTo(9.9f, 3.708f, 9.484f, 3.247f, 8.871f, 3.247f)
                close()
            }
        }
            .build()
        return _priorityLow!!
    }

private var _priorityLow: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.PriorityLow, contentDescription = null)
    }
}
