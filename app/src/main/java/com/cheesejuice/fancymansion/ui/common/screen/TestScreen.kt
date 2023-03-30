package com.cheesejuice.fancymansion.ui.common.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.ui.common.component.ButtonDefault
import com.cheesejuice.fancymansion.ui.theme.FancyMansionTheme

@Composable
fun TestScreen(){
    FancyMansionTheme {
        Column {
            Column(modifier = Modifier.fillMaxSize()) {
                ButtonDefault(
                    iconStart = R.drawable.ic_launcher_foreground,
                    text = "버튼 텍스트",
                    iconEnd = R.drawable.ic_launcher_foreground
                )
            }
            ShapeTest()
        }
    }
}

@Composable
fun ShapeTest(){
    Column {
        Text(text = "Headline Large", style = MaterialTheme.typography.headlineLarge)
        Text(text = "Headline Medium", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Headline Small", style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun TypographyTest(){
    Column {
        Text(text = "Headline Large", style = MaterialTheme.typography.headlineLarge)
        Text(text = "Headline Medium", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Headline Small", style = MaterialTheme.typography.headlineSmall)

        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())

        Text(text = "Title Large", style = MaterialTheme.typography.titleLarge)
        Text(text = "Title Medium", style = MaterialTheme.typography.titleMedium)
        Text(text = "Title Small", style = MaterialTheme.typography.titleSmall)

        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())

        Text(text = "Body Large", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Body Medium", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Body Small", style = MaterialTheme.typography.bodySmall)

        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())

        Text(text = "Label Large", style = MaterialTheme.typography.labelLarge)
        Text(text = "Label Medium", style = MaterialTheme.typography.labelMedium)
        Text(text = "Label Small", style = MaterialTheme.typography.labelSmall)

        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())
    }
}

@Composable
fun ColorSystemTest(){
    Column (Modifier.scrollable(
        orientation = Orientation.Vertical,
        state = rememberScrollState()
    )){
        ClickHolder(
            text = "background",
            background = MaterialTheme.colorScheme.background,
            textColor = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(20.dp))
        ClickHolder(
            text = "surface",
            background = MaterialTheme.colorScheme.surface,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        ClickHolder(
            text = "surfaceVariant",
            background = MaterialTheme.colorScheme.surfaceVariant,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
        ClickHolder(
            text = "inverseSurface",
            background = MaterialTheme.colorScheme.inverseSurface,
            textColor = MaterialTheme.colorScheme.inverseOnSurface
        )
        ClickHolder(
            text = "surfaceTint",
            background = MaterialTheme.colorScheme.surfaceTint,
            textColor = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(20.dp))

        ClickHolder(
            text = "outline",
            background = MaterialTheme.colorScheme.background,
            textColor = MaterialTheme.colorScheme.outline
        )

        ClickHolder(
            text = "outlineVariant",
            background = MaterialTheme.colorScheme.background,
            textColor = MaterialTheme.colorScheme.outlineVariant
        )

        Spacer(Modifier.height(20.dp))

        ClickHolder(
            text = "primary",
            background = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.onPrimary
        )

        ClickHolder(
            text = "primaryContainer",
            background = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer
        )

        ClickHolder(
            text = "inversePrimary",
            background = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.inversePrimary
        )

        Spacer(Modifier.height(20.dp))

        ClickHolder(
            text = "secondary",
            background = MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.onSecondary
        )

        ClickHolder(
            text = "secondaryContainer",
            background = MaterialTheme.colorScheme.secondaryContainer,
            textColor = MaterialTheme.colorScheme.onSecondaryContainer
        )

        Spacer(Modifier.height(20.dp))

        ClickHolder(
            text = "tertiary",
            background = MaterialTheme.colorScheme.tertiary,
            textColor = MaterialTheme.colorScheme.onTertiary
        )

        ClickHolder(
            text = "tertiaryContainer",
            background = MaterialTheme.colorScheme.tertiaryContainer,
            textColor = MaterialTheme.colorScheme.onTertiaryContainer
        )

        Spacer(Modifier.height(20.dp))

        ClickHolder(
            text = "error",
            background = MaterialTheme.colorScheme.error,
            textColor = MaterialTheme.colorScheme.onError
        )

        ClickHolder(
            text = "errorContainer",
            background = MaterialTheme.colorScheme.errorContainer,
            textColor = MaterialTheme.colorScheme.onErrorContainer
        )
    }
}

@Composable
fun ColumnScope.ClickHolder(text : String, background : Color, textColor : Color) {
    Text(modifier = Modifier
        .weight(1f)
        .fillMaxWidth()
        .background(background)
        .clickable { }
        .border(
            width = 1.dp,
            color = Color.Black
        ),
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = textColor
    )
}