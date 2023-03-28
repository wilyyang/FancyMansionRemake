package com.cheesejuice.fancymansion.ui.common.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cheesejuice.fancymansion.ui.theme.FancyMansionTheme
import com.cheesejuice.fancymansion.ui.theme.TypeTypography
import com.cheesejuice.fancymansion.ui.theme.value.color.ColorSystem.ColorSet
import com.cheesejuice.fancymansion.ui.theme.value.string.StringSystem.Strings

@Composable
fun TestScreen(){
    FancyMansionTheme {
        Column {
            StringSystemTest()
        }
    }
}

@Composable
fun StringSystemTest(){
    Column {
        ClickHolder(text = Strings.app_name)
        ClickHolder(text = Strings.test_name)
    }
}

@Composable
fun ColorSystemTest(){
    Column {
        ClickHolder(background = ColorSet.Main100, textColor = ColorSet.Gray900)
        ClickHolder(background = ColorSet.Main200, textColor = ColorSet.Gray800)
        ClickHolder(background = ColorSet.Main300, textColor = ColorSet.Gray700)
        ClickHolder(background = ColorSet.Main400, textColor = ColorSet.Gray600)
        ClickHolder(background = ColorSet.Main500, textColor = ColorSet.Gray500)
        ClickHolder(background = ColorSet.Main600, textColor = ColorSet.Gray400)
        ClickHolder(background = ColorSet.Main700, textColor = ColorSet.Gray300)
        ClickHolder(background = ColorSet.Main800, textColor = ColorSet.Gray200)
        ClickHolder(background = ColorSet.Main800, textColor = ColorSet.Gray100)

        Divider(
            Modifier
                .height(2.dp)
                .fillMaxWidth())

        ClickHolder(background = ColorSet.Surface, textColor = ColorSet.OnSurface)

        Divider(
            Modifier
                .height(2.dp)
                .fillMaxWidth())

        ClickHolder(background = ColorSet.IconDarkEnabled, textColor = ColorSet.IconLightEnabled)
        ClickHolder(background = ColorSet.IconDarkDisabled, textColor = ColorSet.IconLightDisabled)
    }
}

@Composable
fun ColumnScope.ClickHolder(text : String = "색상 테스트", background : Color = ColorSet.Main100, textColor : Color = ColorSet.Gray900) {
    Text(modifier = Modifier
        .weight(1f)
        .fillMaxWidth()
        .background(background)
        .clickable { }, text = text, style = TypeTypography.h1.style, color = textColor
    )
}


@Composable
fun TypographyTest(){
    Column {
        Text(text = "h1 1단계", style = TypeTypography.h1.style)
        Text(text = "h2 2단계", style = TypeTypography.h2.style)
        Text(text = "h3 3단계", style = TypeTypography.h3.style)
        Text(text = "h4 4단계", style = TypeTypography.h4.style)
        Text(text = "h5 5단계", style = TypeTypography.h5.style)
        Text(text = "h6 6단계", style = TypeTypography.h6.style)
        Text(text = "h7 7단계", style = TypeTypography.h7.style)

        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())

        Text(text = "s1 1단계", style = TypeTypography.s1.style)
        Text(text = "s2 2단계", style = TypeTypography.s2.style)

        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())

        Text(text = "p1 1단계", style = TypeTypography.p1.style)
        Text(text = "p2 2단계", style = TypeTypography.p2.style)

        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())

        Text(text = "c1 1단계", style = TypeTypography.c1.style)
        Text(text = "c2 2단계", style = TypeTypography.c2.style)

        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())

        Text(text = "b1 1단계", style = TypeTypography.b1.style)
        Text(text = "b2 2단계", style = TypeTypography.b2.style)
        Text(text = "b3 3단계", style = TypeTypography.b3.style)
        Text(text = "b4 4단계", style = TypeTypography.b4.style)
        Text(text = "b5 5단계", style = TypeTypography.b5.style)
    }
}
