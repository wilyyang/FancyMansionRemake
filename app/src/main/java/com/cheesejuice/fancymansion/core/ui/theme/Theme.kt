package com.cheesejuice.fancymansion.core.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

val disableAlpha = 0.38f
val dividerAlpha = 0.38f
val onTextAlpha = 0.76f

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

    surfaceTint = white,

    background = n_90,
    onBackground = black,

    inverseSurface = black,
    inverseOnSurface = white,

    // state : normal
    surface = white,
    onSurface = black,

    // state : not focus
    surfaceVariant = n_95,
    onSurfaceVariant = n_45,

    // state : disable
    // surfaceDisable = surface.copy(alpha = disableAlpha)
    // onSurfaceDisable = onSurface.copy(alpha = disableAlpha)

    // outline : normal
    outline = black,
    // outline : not focus
    outlineVariant = n_50,

    error = error_50,
    onError = white,
    errorContainer = error_90,
    onErrorContainer = black
)

// Default 값은 MaterialTheme 에서 참조하고 Default가 아닌 경우 해당 객체에서 직접 참조
var colorScheme = LightColorScheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FancyMansionTheme(
    content : @Composable () -> Unit)
{
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ){
        CompositionLocalProvider(
            LocalOverscrollConfiguration provides null
        ){
            content()
        }
    }
}