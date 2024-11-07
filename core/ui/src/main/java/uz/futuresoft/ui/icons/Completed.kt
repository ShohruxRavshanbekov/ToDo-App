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

val AppIcons.Completed: ImageVector
    get() {
        if (_completed != null) {
            return _completed!!
        }
        _completed = Builder(
            name = "Completed",
            defaultWidth = 24.0.dp,
            defaultHeight = 25.0.dp,
            viewportWidth = 24.0f,
            viewportHeight = 25.0f
        ).apply {
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(12.0f, 12.5f)
                moveToRelative(-11.0f, 0.0f)
                arcToRelative(11.0f, 11.0f, 0.0f, true, true, 22.0f, 0.0f)
                arcToRelative(11.0f, 11.0f, 0.0f, true, true, -22.0f, 0.0f)
            }
            path(
                fill = SolidColor(Color(0xFF34C759)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(12.006f, 24.5f)
                curveTo(18.586f, 24.5f, 24.0f, 19.086f, 24.0f, 12.506f)
                curveTo(24.0f, 5.914f, 18.586f, 0.5f, 11.994f, 0.5f)
                curveTo(5.403f, 0.5f, 0.0f, 5.914f, 0.0f, 12.506f)
                curveTo(0.0f, 19.086f, 5.414f, 24.5f, 12.006f, 24.5f)
                close()
                moveTo(10.748f, 18.203f)
                curveTo(10.239f, 18.203f, 9.82f, 17.965f, 9.469f, 17.523f)
                lineTo(6.818f, 14.375f)
                curveTo(6.546f, 14.057f, 6.445f, 13.763f, 6.445f, 13.423f)
                curveTo(6.445f, 12.71f, 7.022f, 12.143f, 7.736f, 12.143f)
                curveTo(8.143f, 12.143f, 8.461f, 12.313f, 8.766f, 12.676f)
                lineTo(10.726f, 15.054f)
                lineTo(15.109f, 8.1f)
                curveTo(15.415f, 7.613f, 15.789f, 7.364f, 16.242f, 7.364f)
                curveTo(16.944f, 7.364f, 17.578f, 7.885f, 17.578f, 8.598f)
                curveTo(17.578f, 8.881f, 17.465f, 9.198f, 17.261f, 9.493f)
                lineTo(11.994f, 17.5f)
                curveTo(11.7f, 17.942f, 11.247f, 18.203f, 10.748f, 18.203f)
                close()
            }
        }
            .build()
        return _completed!!
    }

private var _completed: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Completed, contentDescription = null)
    }
}
