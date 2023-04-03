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

val Typography = Typography(
    // Display
    displayLarge = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 64.sp,
        fontSize = 57.sp,
        fontWeight = FontWeight.Normal
    ),
    displayMedium = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 52.sp,
        fontSize = 45.sp,
        fontWeight = FontWeight.Normal
    ),
    displaySmall = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 44.sp,
        fontSize = 36.sp,
        fontWeight = FontWeight.Normal
    ),

    // Headline
    headlineLarge = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 40.sp,
        fontSize = 32.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineMedium = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 36.sp,
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal
    ),
    headlineSmall = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 32.sp,
        fontSize = 24.sp,
        fontWeight = FontWeight.Normal
    ),

    // Title
    titleLarge = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 28.sp,
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal
    ),
    titleMedium = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 24.sp,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    ),
    titleSmall = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 20.sp,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    ),

    // Body
    bodyLarge = TextStyle(
        fontFamily = SpoqaFontFamily,
        lineHeight = 24.sp,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
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