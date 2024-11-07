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

val AppIcons.ChevronRight: ImageVector
    get() {
        if (_chevronRight != null) {
            return _chevronRight!!
        }
        _chevronRight = Builder(
            name = "ChevronRight",
            defaultWidth = 16.53811236220008.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 11.689f,
            viewportHeight = 16.963f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f,
                strokeAlpha = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(11.689f)
                verticalLineToRelative(16.963f)
                horizontalLineToRelative(-11.689f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(11.689f, 8.477f)
                curveTo(11.689f, 8.232f, 11.592f, 8.008f, 11.406f, 7.832f)
                lineTo(3.672f, 0.254f)
                curveTo(3.496f, 0.088f, 3.281f, 0.0f, 3.027f, 0.0f)
                curveTo(2.529f, 0.0f, 2.139f, 0.381f, 2.139f, 0.889f)
                curveTo(2.139f, 1.133f, 2.236f, 1.357f, 2.393f, 1.523f)
                lineTo(9.502f, 8.477f)
                lineTo(2.393f, 15.43f)
                curveTo(2.236f, 15.596f, 2.139f, 15.811f, 2.139f, 16.065f)
                curveTo(2.139f, 16.572f, 2.529f, 16.953f, 3.027f, 16.953f)
                curveTo(3.281f, 16.953f, 3.496f, 16.865f, 3.672f, 16.69f)
                lineTo(11.406f, 9.121f)
                curveTo(11.592f, 8.936f, 11.689f, 8.721f, 11.689f, 8.477f)
                close()
            }
        }
            .build()
        return _chevronRight!!
    }

private var _chevronRight: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.ChevronRight, contentDescription = null)
    }
}
