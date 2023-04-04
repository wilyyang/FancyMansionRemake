package com.cheesejuice.fancymansion.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.TextStyle
import androidx.core.view.ViewCompat

private val LightColorScheme = lightColorScheme(
    primary = primary_40,
    onPrimary = white,
    primaryContainer = primary_90,
    onPrimaryContainer = black,
    inversePrimary = primary_inverse,
    secondary = secondary_40,
    onSecondary = black,
    secondaryContainer = secondary_90,
    onSecondaryContainer = black,
    tertiary = tertiary_40,
    onTertiary = black,
    tertiaryContainer = tertiary_90,
    onTertiaryContainer = black,

    surfaceTint = white,
    surface = white,
    onSurface = black,
    background = n_90,
    onBackground = black,
    surfaceVariant = n_80,
    onSurfaceVariant = n_50,
    inverseSurface = n_65,
    inverseOnSurface = n_50,

    error = error_50,
    onError = black,
    errorContainer = error_90,
    onErrorContainer = black,
    outline = n_50,
    outlineVariant = n_80
)

private val DarkColorScheme = darkColorScheme(
    primary = primary_50,
    onPrimary = n_10,
    primaryContainer = primary_10,
    onPrimaryContainer = black,
    inversePrimary = primary_inverse,
    secondary = secondary_60,
    onSecondary = black,
    secondaryContainer = secondary_10,
    onSecondaryContainer = black,
    tertiary = tertiary_60,
    onTertiary = black,
    tertiaryContainer = tertiary_10,
    onTertiaryContainer = black,

    surfaceTint = black,
    surface = black,
    onSurface = n_90,
    background = n_10,
    onBackground = n_90,
    surfaceVariant = n_20,
    onSurfaceVariant = n_50,
    inverseSurface = n_35,
    inverseOnSurface = n_50,

    error = error_50,
    onError = n_90,
    errorContainer = error_10,
    onErrorContainer = n_90,
    outline = n_50,
    outlineVariant = n_20
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

    TextStyle.Default
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography
    ){
        content()
    }
}