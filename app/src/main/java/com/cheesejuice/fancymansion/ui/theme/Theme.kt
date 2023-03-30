package com.cheesejuice.fancymansion.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import com.cheesejuice.fancymansion.R

val spoqaHanSansNeo = FontFamily(
    Font(R.font.spoqa_hansans_neo_regular, FontWeight.Normal),
    Font(R.font.spoqa_hansans_neo_medium, FontWeight.Medium),
    Font(R.font.spoqa_hansans_neo_bold, FontWeight.Bold)
)

private val LightColorScheme = lightColorScheme(
    primary = black,
    secondary = white,
    tertiary = error
)

private val DarkColorScheme = darkColorScheme(
    primary = white,
    secondary = black,
    tertiary = error
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

@Composable
fun FancyMansionTheme(
    darkTheme : Boolean = isSystemInDarkTheme(),
    content : @Composable () -> Unit)
{
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
    ){
        content()
    }
}