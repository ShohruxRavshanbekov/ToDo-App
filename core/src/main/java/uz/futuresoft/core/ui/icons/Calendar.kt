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

val AppIcons.Calendar: ImageVector
    get() {
        if (_calendar != null) {
            return _calendar!!
        }
        _calendar = Builder(
            name = "Calendar",
            defaultWidth = 24.0.dp,
            defaultHeight = 21.789346246973366.dp,
            viewportWidth = 19.824f,
            viewportHeight = 17.998f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f,
                strokeAlpha = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(19.824f)
                verticalLineToRelative(17.998f)
                horizontalLineToRelative(-19.824f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(3.066f, 17.998f)
                lineTo(16.406f, 17.998f)
                curveTo(18.447f, 17.998f, 19.463f, 16.982f, 19.463f, 14.971f)
                lineTo(19.463f, 3.047f)
                curveTo(19.463f, 1.035f, 18.447f, 0.02f, 16.406f, 0.02f)
                lineTo(3.066f, 0.02f)
                curveTo(1.025f, 0.02f, 0.0f, 1.025f, 0.0f, 3.047f)
                lineTo(0.0f, 14.971f)
                curveTo(0.0f, 16.992f, 1.025f, 17.998f, 3.066f, 17.998f)
                close()
                moveTo(2.92f, 16.426f)
                curveTo(2.051f, 16.426f, 1.572f, 15.967f, 1.572f, 15.059f)
                lineTo(1.572f, 5.85f)
                curveTo(1.572f, 4.951f, 2.051f, 4.482f, 2.92f, 4.482f)
                lineTo(16.533f, 4.482f)
                curveTo(17.402f, 4.482f, 17.891f, 4.951f, 17.891f, 5.85f)
                lineTo(17.891f, 15.059f)
                curveTo(17.891f, 15.967f, 17.402f, 16.426f, 16.533f, 16.426f)
                close()
                moveTo(7.832f, 7.988f)
                lineTo(8.408f, 7.988f)
                curveTo(8.75f, 7.988f, 8.857f, 7.891f, 8.857f, 7.549f)
                lineTo(8.857f, 6.973f)
                curveTo(8.857f, 6.631f, 8.75f, 6.523f, 8.408f, 6.523f)
                lineTo(7.832f, 6.523f)
                curveTo(7.49f, 6.523f, 7.373f, 6.631f, 7.373f, 6.973f)
                lineTo(7.373f, 7.549f)
                curveTo(7.373f, 7.891f, 7.49f, 7.988f, 7.832f, 7.988f)
                close()
                moveTo(11.074f, 7.988f)
                lineTo(11.65f, 7.988f)
                curveTo(11.992f, 7.988f, 12.109f, 7.891f, 12.109f, 7.549f)
                lineTo(12.109f, 6.973f)
                curveTo(12.109f, 6.631f, 11.992f, 6.523f, 11.65f, 6.523f)
                lineTo(11.074f, 6.523f)
                curveTo(10.732f, 6.523f, 10.615f, 6.631f, 10.615f, 6.973f)
                lineTo(10.615f, 7.549f)
                curveTo(10.615f, 7.891f, 10.732f, 7.988f, 11.074f, 7.988f)
                close()
                moveTo(14.316f, 7.988f)
                lineTo(14.893f, 7.988f)
                curveTo(15.234f, 7.988f, 15.352f, 7.891f, 15.352f, 7.549f)
                lineTo(15.352f, 6.973f)
                curveTo(15.352f, 6.631f, 15.234f, 6.523f, 14.893f, 6.523f)
                lineTo(14.316f, 6.523f)
                curveTo(13.975f, 6.523f, 13.867f, 6.631f, 13.867f, 6.973f)
                lineTo(13.867f, 7.549f)
                curveTo(13.867f, 7.891f, 13.975f, 7.988f, 14.316f, 7.988f)
                close()
                moveTo(4.59f, 11.182f)
                lineTo(5.156f, 11.182f)
                curveTo(5.508f, 11.182f, 5.615f, 11.084f, 5.615f, 10.742f)
                lineTo(5.615f, 10.166f)
                curveTo(5.615f, 9.824f, 5.508f, 9.727f, 5.156f, 9.727f)
                lineTo(4.59f, 9.727f)
                curveTo(4.238f, 9.727f, 4.131f, 9.824f, 4.131f, 10.166f)
                lineTo(4.131f, 10.742f)
                curveTo(4.131f, 11.084f, 4.238f, 11.182f, 4.59f, 11.182f)
                close()
                moveTo(7.832f, 11.182f)
                lineTo(8.408f, 11.182f)
                curveTo(8.75f, 11.182f, 8.857f, 11.084f, 8.857f, 10.742f)
                lineTo(8.857f, 10.166f)
                curveTo(8.857f, 9.824f, 8.75f, 9.727f, 8.408f, 9.727f)
                lineTo(7.832f, 9.727f)
                curveTo(7.49f, 9.727f, 7.373f, 9.824f, 7.373f, 10.166f)
                lineTo(7.373f, 10.742f)
                curveTo(7.373f, 11.084f, 7.49f, 11.182f, 7.832f, 11.182f)
                close()
                moveTo(11.074f, 11.182f)
                lineTo(11.65f, 11.182f)
                curveTo(11.992f, 11.182f, 12.109f, 11.084f, 12.109f, 10.742f)
                lineTo(12.109f, 10.166f)
                curveTo(12.109f, 9.824f, 11.992f, 9.727f, 11.65f, 9.727f)
                lineTo(11.074f, 9.727f)
                curveTo(10.732f, 9.727f, 10.615f, 9.824f, 10.615f, 10.166f)
                lineTo(10.615f, 10.742f)
                curveTo(10.615f, 11.084f, 10.732f, 11.182f, 11.074f, 11.182f)
                close()
                moveTo(14.316f, 11.182f)
                lineTo(14.893f, 11.182f)
                curveTo(15.234f, 11.182f, 15.352f, 11.084f, 15.352f, 10.742f)
                lineTo(15.352f, 10.166f)
                curveTo(15.352f, 9.824f, 15.234f, 9.727f, 14.893f, 9.727f)
                lineTo(14.316f, 9.727f)
                curveTo(13.975f, 9.727f, 13.867f, 9.824f, 13.867f, 10.166f)
                lineTo(13.867f, 10.742f)
                curveTo(13.867f, 11.084f, 13.975f, 11.182f, 14.316f, 11.182f)
                close()
                moveTo(4.59f, 14.385f)
                lineTo(5.156f, 14.385f)
                curveTo(5.508f, 14.385f, 5.615f, 14.277f, 5.615f, 13.936f)
                lineTo(5.615f, 13.359f)
                curveTo(5.615f, 13.018f, 5.508f, 12.92f, 5.156f, 12.92f)
                lineTo(4.59f, 12.92f)
                curveTo(4.238f, 12.92f, 4.131f, 13.018f, 4.131f, 13.359f)
                lineTo(4.131f, 13.936f)
                curveTo(4.131f, 14.277f, 4.238f, 14.385f, 4.59f, 14.385f)
                close()
                moveTo(7.832f, 14.385f)
                lineTo(8.408f, 14.385f)
                curveTo(8.75f, 14.385f, 8.857f, 14.277f, 8.857f, 13.936f)
                lineTo(8.857f, 13.359f)
                curveTo(8.857f, 13.018f, 8.75f, 12.92f, 8.408f, 12.92f)
                lineTo(7.832f, 12.92f)
                curveTo(7.49f, 12.92f, 7.373f, 13.018f, 7.373f, 13.359f)
                lineTo(7.373f, 13.936f)
                curveTo(7.373f, 14.277f, 7.49f, 14.385f, 7.832f, 14.385f)
                close()
                moveTo(11.074f, 14.385f)
                lineTo(11.65f, 14.385f)
                curveTo(11.992f, 14.385f, 12.109f, 14.277f, 12.109f, 13.936f)
                lineTo(12.109f, 13.359f)
                curveTo(12.109f, 13.018f, 11.992f, 12.92f, 11.65f, 12.92f)
                lineTo(11.074f, 12.92f)
                curveTo(10.732f, 12.92f, 10.615f, 13.018f, 10.615f, 13.359f)
                lineTo(10.615f, 13.936f)
                curveTo(10.615f, 14.277f, 10.732f, 14.385f, 11.074f, 14.385f)
                close()
            }
        }
            .build()
        return _calendar!!
    }

private var _calendar: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Calendar, contentDescription = null)
    }
}
