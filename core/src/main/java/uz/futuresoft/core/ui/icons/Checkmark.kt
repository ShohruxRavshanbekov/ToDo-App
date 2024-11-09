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

val AppIcons.Checkmark: ImageVector
    get() {
        if (_checkmark != null) {
            return _checkmark!!
        }
        _checkmark = Builder(
            name = "Checkmark",
            defaultWidth = 24.0.dp,
            defaultHeight = 23.58467682295518.dp,
            viewportWidth = 20.283f,
            viewportHeight = 19.932f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f,
                strokeAlpha = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(20.283f)
                verticalLineToRelative(19.932f)
                horizontalLineToRelative(-20.283f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(19.922f, 9.961f)
                curveTo(19.922f, 15.449f, 15.459f, 19.922f, 9.961f, 19.922f)
                curveTo(4.473f, 19.922f, 0.0f, 15.449f, 0.0f, 9.961f)
                curveTo(0.0f, 4.463f, 4.473f, 0.0f, 9.961f, 0.0f)
                curveTo(15.459f, 0.0f, 19.922f, 4.463f, 19.922f, 9.961f)
                close()
                moveTo(12.998f, 6.084f)
                lineTo(8.828f, 12.783f)
                lineTo(6.846f, 10.225f)
                curveTo(6.602f, 9.902f, 6.387f, 9.814f, 6.104f, 9.814f)
                curveTo(5.664f, 9.814f, 5.322f, 10.176f, 5.322f, 10.615f)
                curveTo(5.322f, 10.84f, 5.41f, 11.055f, 5.557f, 11.25f)
                lineTo(8.008f, 14.258f)
                curveTo(8.262f, 14.6f, 8.535f, 14.736f, 8.867f, 14.736f)
                curveTo(9.199f, 14.736f, 9.482f, 14.58f, 9.688f, 14.258f)
                lineTo(14.277f, 7.031f)
                curveTo(14.394f, 6.826f, 14.521f, 6.602f, 14.521f, 6.387f)
                curveTo(14.521f, 5.928f, 14.121f, 5.635f, 13.691f, 5.635f)
                curveTo(13.438f, 5.635f, 13.184f, 5.791f, 12.998f, 6.084f)
                close()
            }
        }
            .build()
        return _checkmark!!
    }

private var _checkmark: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Checkmark, contentDescription = null)
    }
}
