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

val AppIcons.Trash: ImageVector
    get() {
        if (_trash != null) {
            return _trash!!
        }
        _trash = Builder(
            name = "Trash",
            defaultWidth = 19.689687473388403.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 19.268f,
            viewportHeight = 23.486f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, fillAlpha = 0.0f,
                strokeAlpha = 0.0f, strokeLineWidth = 0.0f, strokeLineCap = Butt,
                strokeLineJoin = Miter, strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(0.0f, 0.0f)
                horizontalLineToRelative(19.268f)
                verticalLineToRelative(23.486f)
                horizontalLineToRelative(-19.268f)
                close()
            }
            path(
                fill = SolidColor(Color(0xFFffffff)), stroke = null, fillAlpha = 0.85f,
                strokeLineWidth = 0.0f, strokeLineCap = Butt, strokeLineJoin = Miter,
                strokeLineMiter = 4.0f, pathFillType = NonZero
            ) {
                moveTo(6.475f, 18.779f)
                curveTo(6.094f, 18.779f, 5.85f, 18.555f, 5.83f, 18.184f)
                lineTo(5.537f, 7.432f)
                curveTo(5.527f, 7.07f, 5.771f, 6.836f, 6.162f, 6.836f)
                curveTo(6.523f, 6.836f, 6.777f, 7.061f, 6.787f, 7.422f)
                lineTo(7.1f, 18.184f)
                curveTo(7.109f, 18.545f, 6.855f, 18.779f, 6.475f, 18.779f)
                close()
                moveTo(9.453f, 18.779f)
                curveTo(9.072f, 18.779f, 8.809f, 18.545f, 8.809f, 18.184f)
                lineTo(8.809f, 7.432f)
                curveTo(8.809f, 7.07f, 9.072f, 6.836f, 9.453f, 6.836f)
                curveTo(9.834f, 6.836f, 10.107f, 7.07f, 10.107f, 7.432f)
                lineTo(10.107f, 18.184f)
                curveTo(10.107f, 18.545f, 9.834f, 18.779f, 9.453f, 18.779f)
                close()
                moveTo(12.441f, 18.779f)
                curveTo(12.061f, 18.779f, 11.807f, 18.545f, 11.816f, 18.184f)
                lineTo(12.119f, 7.432f)
                curveTo(12.129f, 7.061f, 12.383f, 6.836f, 12.744f, 6.836f)
                curveTo(13.135f, 6.836f, 13.379f, 7.07f, 13.369f, 7.432f)
                lineTo(13.076f, 18.184f)
                curveTo(13.057f, 18.555f, 12.813f, 18.779f, 12.441f, 18.779f)
                close()
                moveTo(5.166f, 4.463f)
                lineTo(6.719f, 4.463f)
                lineTo(6.719f, 2.373f)
                curveTo(6.719f, 1.816f, 7.109f, 1.455f, 7.695f, 1.455f)
                lineTo(11.191f, 1.455f)
                curveTo(11.777f, 1.455f, 12.168f, 1.816f, 12.168f, 2.373f)
                lineTo(12.168f, 4.463f)
                lineTo(13.721f, 4.463f)
                lineTo(13.721f, 2.275f)
                curveTo(13.721f, 0.859f, 12.803f, 0.0f, 11.299f, 0.0f)
                lineTo(7.588f, 0.0f)
                curveTo(6.084f, 0.0f, 5.166f, 0.859f, 5.166f, 2.275f)
                close()
                moveTo(0.732f, 5.244f)
                lineTo(18.184f, 5.244f)
                curveTo(18.584f, 5.244f, 18.906f, 4.902f, 18.906f, 4.502f)
                curveTo(18.906f, 4.102f, 18.584f, 3.77f, 18.184f, 3.77f)
                lineTo(0.732f, 3.77f)
                curveTo(0.342f, 3.77f, 0.0f, 4.102f, 0.0f, 4.502f)
                curveTo(0.0f, 4.912f, 0.342f, 5.244f, 0.732f, 5.244f)
                close()
                moveTo(4.98f, 21.748f)
                lineTo(13.936f, 21.748f)
                curveTo(15.332f, 21.748f, 16.27f, 20.84f, 16.338f, 19.443f)
                lineTo(17.021f, 5.039f)
                lineTo(1.885f, 5.039f)
                lineTo(2.578f, 19.453f)
                curveTo(2.646f, 20.85f, 3.564f, 21.748f, 4.98f, 21.748f)
                close()
            }
        }
            .build()
        return _trash!!
    }

private var _trash: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Trash, contentDescription = null)
    }
}
