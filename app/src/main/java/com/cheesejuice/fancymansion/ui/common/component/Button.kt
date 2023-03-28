package com.cheesejuice.fancymansion.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cheesejuice.fancymansion.ui.common.OnSingleClickListener
import com.cheesejuice.fancymansion.ui.theme.TypeTypography
import com.cheesejuice.fancymansion.ui.theme.value.color.ColorSystem.ColorSet

@Composable
fun ButtonDefault(
    modifier : Modifier = Modifier,
    contentPadding : PaddingValues = ButtonDefaults.ContentPadding,
    contentArrangement : Arrangement.Horizontal = Arrangement.Center,
    backgroundColor : Color = ColorSet.Main500,

    text : String? = null,
    textStyle : TextStyle = TypeTypography.h6.style,
    textOverflow : TextOverflow = TextOverflow.Clip,
    textMaxLines : Int = Int.MAX_VALUE,
    textColor : Color = ColorSet.BlackAlways,

    iconStart : Int? = null,
    iconEnd : Int? = null,
    iconPadding : Dp = 0.dp,
    iconColor : Color? = null,

    isClickable : Boolean = true,
    clickInterval : Int = 600,
    onClick : () -> Unit,
) {
    val singleClickListener = remember {
        mutableStateOf(
            OnSingleClickListener(
                interval = clickInterval,
                onSingleClick = onClick
            )
        )
    }

    val (finalBackgroundColor, finalTextColor) = when (isClickable) {
        true -> backgroundColor to textColor
        false -> ColorSet.Gray300 to ColorSet.Gray500
    }


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(color = finalBackgroundColor)
            .height(IntrinsicSize.Min)
            .width(IntrinsicSize.Max)
            .clickable(
                role = Role.Button,
                onClick = {
                    if (isClickable) {
                        singleClickListener.value.invoke()
                    }
                },
                enabled = isClickable
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