package uz.futuresoft.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = sfProDisplay,
        fontWeight = FontWeight.W700,
        fontSize = 38.sp,
        lineHeight = 46.sp,
        letterSpacing = 0.42.sp
    ),
    displayMedium = TextStyle(
        fontFamily = sfProDisplay,
        fontWeight = FontWeight.W700,
        fontSize = 34.sp,
        lineHeight = 41.sp,
        letterSpacing = 0.37.sp
    ),
    titleLarge = TextStyle(
        fontFamily = sfProDisplay,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.38.sp
    ),
    titleMedium = TextStyle(      // subhead
        fontFamily = sfProText,
        fontWeight = FontWeight.W400,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = (-0.24).sp
    ),
    headlineMedium = TextStyle(
        fontFamily = sfProText,
        fontWeight = FontWeight.W600,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.41).sp
    ),
    bodyMedium = TextStyle(
        fontFamily = sfProText,
        fontWeight = FontWeight.W400,
        fontSize = 17.sp,
        lineHeight = 22.sp,
        letterSpacing = (-0.41).sp
    ),
    labelLarge = TextStyle(
        fontFamily = sfProText,
        fontWeight = FontWeight.W600,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = (-0.08).sp
    ),
)