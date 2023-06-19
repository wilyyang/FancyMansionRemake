package com.cheesejuice.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Label(
    modifier : Modifier = Modifier,
    label : String,
    labelColor : Color = MaterialTheme.colorScheme.onPrimary,
    labelStyle : TextStyle = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
    backgroundColor : Color = MaterialTheme.colorScheme.primary,
    borderWidth : Dp = 0.dp,
    borderColor : Color = MaterialTheme.colorScheme.onPrimary
) {
    Box(
        modifier = modifier
            .border(
                width = borderWidth,
                color = borderColor,
                shape = MaterialTheme.shapes.extraSmall
            )
            .padding(
                if (borderWidth >= 1.dp) (borderWidth - 0.5.dp) else 0.dp
            )
            .clip(MaterialTheme.shapes.extraSmall)
            .background(color = backgroundColor)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 3.dp, horizontal = 6.dp),
            text = label,
            color = labelColor,
            style = labelStyle
        )
    }
}