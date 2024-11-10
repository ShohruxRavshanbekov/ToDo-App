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

val AppIcons.Eye: ImageVector
    get() {
        if (_eye != null) {
            return _eye!!
        }
        _eye = Builder(
            name = "Eye",
            defaultWidth = 24.0.dp,
            defaultHeight = 14.894181376669469.dp,
            viewportWidth = 32.121f,
            viewportHeight = 19.934f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f,
                strokeAlpha = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(32.121f)
                verticalLineToRelative(19.934f)
                horizontalLineToRelative(-32.121f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(15.891f, 19.922f)
                curveTo(25.277f, 19.922f, 31.77f, 12.328f, 31.77f, 9.961f)
                curveTo(31.77f, 7.582f, 25.266f, 0.0f, 15.891f, 0.0f)
                curveTo(6.621f, 0.0f, 0.0f, 7.582f, 0.0f, 9.961f)
                curveTo(0.0f, 12.328f, 6.621f, 19.922f, 15.891f, 19.922f)
                close()
                moveTo(15.891f, 16.512f)
                curveTo(12.269f, 16.512f, 9.34f, 13.57f, 9.34f, 9.961f)
                curveTo(9.34f, 6.34f, 12.269f, 3.41f, 15.891f, 3.41f)
                curveTo(19.5f, 3.41f, 22.441f, 6.34f, 22.441f, 9.961f)
                curveTo(22.441f, 13.57f, 19.5f, 16.512f, 15.891f, 16.512f)
                close()
                moveTo(15.891f, 12.352f)
                curveTo(17.215f, 12.352f, 18.281f, 11.285f, 18.281f, 9.961f)
                curveTo(18.281f, 8.637f, 17.215f, 7.57f, 15.891f, 7.57f)
                curveTo(14.566f, 7.57f, 13.5f, 8.637f, 13.5f, 9.961f)
                curveTo(13.5f, 11.285f, 14.566f, 12.352f, 15.891f, 12.352f)
                close()
            }
        }
            .build()
        return _eye!!
    }

private var _eye: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Eye, contentDescription = null)
    }
}
