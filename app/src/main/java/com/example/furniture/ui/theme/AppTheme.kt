package com.example.furniture.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider


import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.ResourceFont
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.furniture.R

data class AppColors(
    val primary: Color,
    val secondary: Color,
    val error: Color,
    val mediumTitle: Color,
    val appGray: Color,
)

data class AppTypography(
    val largeTitle: TextStyle = TextStyle.Default,
    val mediumTitle: TextStyle = TextStyle.Default,
    val subTitle: TextStyle = TextStyle.Default,
    val normal: TextStyle = TextStyle.Default,
    val h1: TextStyle = TextStyle.Default,
    val h2: TextStyle = TextStyle.Default,
    val body1: TextStyle = TextStyle.Default,
    val body2: TextStyle = TextStyle.Default,
    val textProduct: TextStyle = TextStyle.Default,
    val priceProduct: TextStyle = TextStyle.Default
)

data class AppFont(
    val gelasioRegular: Font,
    val gelasioBold: Font,
    val nunitoSanMedium: Font,
    val nunitoSanRegular: Font,
)

private val LocalColors = staticCompositionLocalOf<AppColors> {
    error("No custom colors provided")
}

private val LocalTypography = staticCompositionLocalOf<AppTypography> {
    error("No custom typography provided")
}
private val LocalFont = staticCompositionLocalOf<AppFont> {
    error("No custom font provided")

}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val context = LocalContext.current;
    val fonts = AppFont(
        gelasioRegular = Font(R.font.gelasio_regular),
        gelasioBold = Font(R.font.gelasio_bold),
        nunitoSanMedium = Font(R.font.nunitosan_medium),
        nunitoSanRegular = Font(R.font.nunitosan_regular)
    )
    val colors = AppColors(
        primary = Color.White,
        secondary = Color.Black,
        error = Color.Red,
        mediumTitle = Color(
            0xFF606060
        ),
        appGray = Color(0xFFE0E0E0)
    )
    val typography = AppTypography(
        largeTitle = TextStyle(
            fontSize = 30.sp,
            color = Color(0xFF303030),
            fontWeight = FontWeight(700),
            lineHeight = TextUnit(38f, TextUnitType(38L)),
            fontFamily = FontFamily(fonts.gelasioBold)
        ),
        mediumTitle = TextStyle(
            fontSize = 24.sp,
            color = colors.mediumTitle,
            fontWeight = FontWeight(600),
            lineHeight = TextUnit(30f, TextUnitType(30L)),
            fontFamily = FontFamily(fonts.gelasioRegular)
        ),
        subTitle = TextStyle(
            fontSize = 18.sp,
            color = Color(0xFF808080),
            fontWeight = FontWeight(400),
            lineHeight = TextUnit(35f, TextUnitType(35L)),
            fontFamily = FontFamily(fonts.nunitoSanRegular),

            ),
        h1 = TextStyle(fontSize = 30.sp),
        h2 = TextStyle(fontSize = 24.sp),
        body1 = TextStyle(fontSize = 16.sp),
        body2 = TextStyle(fontSize = 14.sp),
        textProduct = TextStyle(
            fontSize = 14.sp,
            color = Color.Gray,
            fontFamily = FontFamily(fonts.nunitoSanMedium)
        ),
        priceProduct = TextStyle(
            fontSize = 14.sp,
            color = Color.Black,
            fontFamily = FontFamily(fonts.nunitoSanMedium),
            fontWeight = FontWeight(700)
        )
    )
    CompositionLocalProvider(
        LocalColors provides colors,
        LocalTypography provides typography,
        LocalFont provides fonts
    ) {
        content.invoke()
    }
}

object AppTheme {
    val appColors: AppColors
        @Composable
        get() = LocalColors.current;
    val appTypography: AppTypography
        @Composable
        get() = LocalTypography.current;
    val appFonts: AppFont
        @Composable
        get() = LocalFont.current;
}