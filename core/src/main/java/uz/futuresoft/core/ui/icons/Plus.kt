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

val AppIcons.Plus: ImageVector
    get() {
        if (_plus != null) {
            return _plus!!
        }
        _plus = Builder(
            name = "Plus",
            defaultWidth = 24.0.dp,
            defaultHeight = 23.49389567147614.dp,
            viewportWidth = 17.119f,
            viewportHeight = 16.758f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f,
                strokeAlpha = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(17.119f)
                verticalLineToRelative(16.758f)
                horizontalLineToRelative(-17.119f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 8.379f)
                curveTo(0.0f, 9.194f, 0.664f, 9.858f, 1.479f, 9.858f)
                lineTo(6.904f, 9.858f)
                lineTo(6.904f, 15.283f)
                curveTo(6.904f, 16.094f, 7.563f, 16.758f, 8.379f, 16.758f)
                curveTo(9.194f, 16.758f, 9.863f, 16.094f, 9.863f, 15.283f)
                lineTo(9.863f, 9.858f)
                lineTo(15.283f, 9.858f)
                curveTo(16.094f, 9.858f, 16.758f, 9.194f, 16.758f, 8.379f)
                curveTo(16.758f, 7.563f, 16.094f, 6.895f, 15.283f, 6.895f)
                lineTo(9.863f, 6.895f)
                lineTo(9.863f, 1.479f)
                curveTo(9.863f, 0.664f, 9.194f, 0.0f, 8.379f, 0.0f)
                curveTo(7.563f, 0.0f, 6.904f, 0.664f, 6.904f, 1.479f)
                lineTo(6.904f, 6.895f)
                lineTo(1.479f, 6.895f)
                curveTo(0.664f, 6.895f, 0.0f, 7.563f, 0.0f, 8.379f)
                close()
            }
        }
            .build()
        return _plus!!
    }

private var _plus: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Plus, contentDescription = null)
    }
}
