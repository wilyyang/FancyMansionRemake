package com.cheesejuice.fancymansion.ui.theme.value.color

import androidx.compose.ui.graphics.Color

val LightColorSet = BaseColorSet()

open class BaseColorSet(
    val Main100 : Color = main100,
    val Main200 : Color = main200,
    val Main300 : Color = main300,
    val Main400 : Color = main400,
    val Main500 : Color = main500,
    val Main600 : Color = main600,
    val Main700 : Color = main700,
    val Main800 : Color = main800,

    val Gray100 : Color = gray100,
    val Gray200 : Color = gray200,
    val Gray300 : Color = gray300,
    val Gray400 : Color = gray400,
    val Gray500 : Color = gray500,
    val Gray600 : Color = gray600,
    val Gray700 : Color = gray700,
    val Gray800 : Color = gray800,
    val Gray900 : Color = gray900,

    val IconDarkEnabled   : Color = icon_dark_enabled,
    val IconDarkDisabled  : Color = icon_dark_disabled,
    val IconLightEnabled  : Color = icon_light_enabled,
    val IconLightDisabled : Color = icon_light_disabled,

    val Surface   : Color = white,
    val OnSurface   : Color = black,

    val Error         : Color = error,
    val WhiteAlways   : Color = white,
    val BlackAlways   : Color = black,
    val Transparent   : Color = Color.Transparent
)