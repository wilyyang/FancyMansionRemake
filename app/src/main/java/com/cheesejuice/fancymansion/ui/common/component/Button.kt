package com.cheesejuice.fancymansion.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.ui.common.clickSingle

@Preview(showSystemUi = true)
@Composable
fun PreviewButton(){
    Column(modifier = Modifier.fillMaxSize()) {
        ButtonDefault(
            iconStart = R.drawable.ic_launcher_foreground,
            text = "버튼 텍스트",
            iconEnd = R.drawable.ic_launcher_foreground
        )
    }
}

@Composable
fun ButtonDefault(
    modifier : Modifier = Modifier,
    contentPadding : PaddingValues = ButtonDefaults.ContentPadding,
    contentArrangement : Arrangement.Horizontal = Arrangement.Center,
    backgroundColor : Color = MaterialTheme.colorScheme.primary,

    text : String? = null,
    textStyle : TextStyle = MaterialTheme.typography.titleLarge,
    textOverflow : TextOverflow = TextOverflow.Clip,
    textMaxLines : Int = Int.MAX_VALUE,
    textColor : Color = MaterialTheme.colorScheme.onPrimary,

    iconStart : Int? = null,
    iconEnd : Int? = null,
    iconPadding : Dp = 0.dp,
    iconColor : Color? = null,

    isClickable : Boolean = true,
    clickInterval : Int = 600,
    onClick : () -> Unit = {},
) {
    val (finalBackgroundColor, finalTextColor) = when (isClickable) {
        true -> backgroundColor to textColor
        false -> MaterialTheme.colorScheme.inverseSurface to MaterialTheme.colorScheme.inverseOnSurface
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(color = finalBackgroundColor)
            .height(IntrinsicSize.Min)
            .width(IntrinsicSize.Max)
            .clickSingle(
                enabled = isClickable,
                role = Role.Button,
                clickInterval = clickInterval,
                onClick = { onClick() }
            )
            .padding(contentPadding),
        horizontalArrangement = contentArrangement,
    ) {
        iconStart?.also {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                tint = iconColor ?: finalTextColor,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            )
            Spacer(modifier = Modifier.width(iconPadding))
        }
        text?.also {
            Text(
                text = it,
                color = finalTextColor,
                style = textStyle,
                overflow = textOverflow,
                maxLines = textMaxLines,
                modifier = if (textOverflow != TextOverflow.Clip) Modifier.weight(1f) else Modifier.wrapContentWidth()
            )
        }
        iconEnd?.also {
            Spacer(modifier = Modifier.width(iconPadding))
            Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                tint = iconColor ?: finalTextColor,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            )
        }
    }
}