package com.cheesejuice.fancymansion.ui.common.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import com.cheesejuice.fancymansion.ui.theme.TextStyleGroup
import com.cheesejuice.fancymansion.ui.theme.typography

@Composable
fun RowInfo(
    modifier : Modifier = Modifier,
    title: String,
    content: String,
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    styleGroup : TextStyleGroup = TextStyleGroup.Medium
) {
    RowInfo(
        modifier = modifier,
        title = title,
        content = content,
        titleColor = titleColor,
        contentColor = contentColor,
        titleStyle = styleGroup.titleStyle,
        contentStyle = styleGroup.bodyStyle
    )
}

@Composable
fun RowInfo(
    modifier : Modifier = Modifier,
    title: String,
    content: String,
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    titleStyle : TextStyle = typography.titleMedium,
    contentStyle : TextStyle = typography.bodyMedium
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.weight(2f),
            text = title,
            color = titleColor,
            style = titleStyle
        )
        Text(
            modifier = Modifier.weight(5f),
            text = content,
            color = contentColor,
            style = contentStyle,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}