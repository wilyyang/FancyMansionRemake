package com.cheesejuice.fancymansion.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalView
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import androidx.core.view.ViewCompat
import com.cheesejuice.fancymansion.ui.theme.value.color.ColorSystem
import com.cheesejuice.fancymansion.ui.theme.value.color.ColorSystem.ColorSet
import com.cheesejuice.fancymansion.ui.theme.value.string.StringSystem
import java.util.*

@Composable
fun FancyMansionTheme(darkTheme : Boolean = isSystemInDarkTheme(), content : @Composable () -> Unit) {
    // 색상 나이트 모드 여부 적용
    ColorSystem.setColorByTheme(darkTheme)

    // 다국어 적용
    StringSystem.setStringsByLocale(getLocale())

    // 상태 바
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = ColorSet.Surface.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colors = ColorSystem.MaterialColors,
    ){
        // 리플 테마 적용
        CompositionLocalProvider(LocalRippleTheme provides BaseRippleTheme) {
            content()
        }
    }
}

@Composable
@ReadOnlyComposable
fun getLocale(): Locale {
    val configuration = LocalConfiguration.current
    return ConfigurationCompat.getLocales(configuration).get(0) ?: LocaleListCompat.getDefault()[0]!!
}

@Immutable
private object BaseRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = RippleTheme.defaultRippleColor(
        contentColor = ColorSet.Gray600,
        lightTheme = true
    )

    @Composable
    override fun rippleAlpha() = RippleTheme.defaultRippleAlpha(
        contentColor = ColorSet.Gray600,
        lightTheme = true
    )
}