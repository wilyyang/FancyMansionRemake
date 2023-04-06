package com.cheesejuice.fancymansion.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = primary_40,
    onPrimary = white,
    primaryContainer = primary_90,
    onPrimaryContainer = black,
    inversePrimary = primary_inverse,
    secondary = secondary_40,
    onSecondary = white,
    secondaryContainer = secondary_90,
    onSecondaryContainer = black,
    tertiary = tertiary_40,
    onTertiary = white,
    tertiaryContainer = tertiary_90,
    onTertiaryContainer = black,

    surface = white,
    onSurface = black,
    background = n_90,
    onBackground = black,

    // not focus color
    surfaceVariant = n_80,
    // disabled color
    surfaceTint = n_65,

    // not focus / disabled text
    onSurfaceVariant = n_50,

    inverseSurface = black,
    inverseOnSurface = white,

    error = error_50,
    onError = white,
    errorContainer = error_90,
    onErrorContainer = black,


    outline = black,
    // not focus color
    outlineVariant = n_50
)

// Default 값은 MaterialTheme 에서 참조하고 Default가 아닌 경우 해당 객체에서 직접 참조
var colorScheme = LightColorScheme

@Composable
fun FancyMansionTheme(
    content : @Composable () -> Unit)
{
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ){
        content()
    }
}