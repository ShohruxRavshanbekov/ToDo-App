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

val AppIcons.ChevronLeft: ImageVector
    get() {
        if (_chevronLeft != null) {
            return _chevronLeft!!
        }
        _chevronLeft = Builder(
            name = "ChevronLeft",
            defaultWidth = 17.43728813559322.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 14.789f,
            viewportHeight = 20.355f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f,
                strokeAlpha = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(14.789f)
                verticalLineToRelative(20.355f)
                horizontalLineToRelative(-14.789f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 10.172f)
                curveTo(0.0f, 10.465f, 0.105f, 10.723f, 0.328f, 10.945f)
                lineTo(9.621f, 20.027f)
                curveTo(9.82f, 20.238f, 10.078f, 20.344f, 10.383f, 20.344f)
                curveTo(10.992f, 20.344f, 11.461f, 19.887f, 11.461f, 19.277f)
                curveTo(11.461f, 18.973f, 11.332f, 18.715f, 11.144f, 18.516f)
                lineTo(2.613f, 10.172f)
                lineTo(11.144f, 1.828f)
                curveTo(11.332f, 1.629f, 11.461f, 1.359f, 11.461f, 1.066f)
                curveTo(11.461f, 0.457f, 10.992f, 0.0f, 10.383f, 0.0f)
                curveTo(10.078f, 0.0f, 9.82f, 0.105f, 9.621f, 0.305f)
                lineTo(0.328f, 9.398f)
                curveTo(0.105f, 9.609f, 0.0f, 9.879f, 0.0f, 10.172f)
                close()
            }
        }
            .build()
        return _chevronLeft!!
    }

private var _chevronLeft: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.ChevronLeft, contentDescription = null)
    }
}
