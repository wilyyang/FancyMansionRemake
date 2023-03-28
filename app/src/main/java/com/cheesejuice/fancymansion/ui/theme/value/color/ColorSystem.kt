package com.cheesejuice.fancymansion.ui.theme.value.color

import androidx.compose.material.Colors

object ColorSystem {
    private val LightColorPalette = Colors(
        primary = main500,
        primaryVariant = main500,
        secondary = gray900,
        secondaryVariant = main500,
        background = gray100,
        surface = white,
        error = error,
        onPrimary = black,
        onSecondary = gray100,
        onBackground = gray900,
        onSurface = gray900,
        onError = white,
        isLight = false
    )

    private val DarkColorPalette = Colors(
        primary = main400,
        primaryVariant = main400,
        secondary = gray900_night,
        secondaryVariant = main400,
        background = gray100_night,
        surface = black,
        error = error,
        onPrimary = black,
        onSecondary = gray100_night,
        onBackground = gray900_night,
        onSurface = gray900_night,
        onError = black,
        isLight = true
    )

    // Member
    var isDarkMode = false
    var ColorSet : BaseColorSet = LightColorSet
        private set
    var MaterialColors : Colors = LightColorPalette
        private set

    fun setColorByTheme(isDarkMode: Boolean) {
        if (this.isDarkMode != isDarkMode) {
            this.isDarkMode = isDarkMode
            ColorSet = if(isDarkMode) DarkColorSet else LightColorSet
            MaterialColors = if(isDarkMode) DarkColorPalette else LightColorPalette
        }
    }
}