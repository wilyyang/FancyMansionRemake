package com.cheesejuice.fancymansion.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cheesejuice.fancymansion.R

val SpoqaFontFamily = FontFamily(
    Font(R.font.spoqa_hansans_neo_regular, FontWeight.Normal),
    Font(R.font.spoqa_hansans_neo_medium, FontWeight.Medium),
    Font(R.font.spoqa_hansans_neo_bold, FontWeight.Bold)
)

enum class TextStyleGroup (val titleStyle : TextStyle, val bodyStyle : TextStyle, val labelStyle : TextStyle) {
    Large(typography.titleLarge, typography.bodyLarge, typography.labelLarge),
    Medium(typography.titleMedium, typography.bodyMedium, typography.labelMedium),
    Small(typography.titleSmall, typography.bodySmall, typography.labelSmall)
}

val typography = Typography(
    // Display
    displayLarge = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 44.sp,
        fontSize = 36.sp,
        fontWeight = FontWeight.Medium
    ),
    displayMedium = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 40.sp,
        fontSize = 32.sp,
        fontWeight = FontWeight.Medium
    ),
    displaySmall = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 36.sp,
        fontSize = 28.sp,
        fontWeight = FontWeight.Medium
    ),

    // Headline
    headlineLarge = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 32.sp,
        fontSize = 24.sp,
        fontWeight = FontWeight.Medium
    ),
    headlineMedium = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 28.sp,
        fontSize = 22.sp,
        fontWeight = FontWeight.Medium
    ),
    headlineSmall = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 24.sp,
        fontSize = 18.sp,
        fontWeight = FontWeight.Medium
    ),

    // Title
    titleLarge = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 24.sp,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),
    titleMedium = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    titleSmall = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 16.sp,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    ),

    // Body
    bodyLarge = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 24.sp,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),
    bodySmall = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 16.sp,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),

    // Label
    labelLarge = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),
    labelMedium = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 16.sp,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium
    ),
    labelSmall = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 16.sp,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium
    ),
)