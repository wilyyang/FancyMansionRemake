package com.cheesejuice.fancymansion.ui.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.ui.common.OnSingleClickListener
import com.cheesejuice.fancymansion.ui.common.clickSingle

@Composable
fun BasicButton(
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
                tint = iconColor ?: textColor,
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
                tint = iconColor ?: textColor,
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
            )
        }
    }
}

@Composable
fun BasicIcon(
    modifier : Modifier = Modifier,
    modifierIcon : Modifier = Modifier.fillMaxSize(),
    idIcon : Int,
    tint : Color = LocalContentColor.current,
    onClick : () -> Unit = {},
) {
    val rememberOnClick by rememberUpdatedState(newValue = OnSingleClickListener(onSingleClick = onClick))
    IconButton(
        onClick = { rememberOnClick.invoke() },
        modifier = modifier
    ) {
        Icon(
            modifier = modifierIcon,
            painter = painterResource(id = idIcon),
            contentDescription = null,
            tint = tint
        )
    }
}

@Composable
fun DropDown(
    modifier: Modifier = Modifier,
    contentPadding : PaddingValues = PaddingValues(0.dp),
    backgroundColor : Color = MaterialTheme.colorScheme.surface,

    list : List<Pair<String, String>>,
    textStyle : TextStyle = MaterialTheme.typography.bodyMedium,
    textColor : Color = MaterialTheme.colorScheme.onSurface,

    isClickable : Boolean = true,
    selectedValue: Pair<String, String>? = null,
    onClick: (Pair<String, String>) -> Unit,
) {
    val (finalBackgroundColor, finalTextColor) = when (isClickable) {
        true -> backgroundColor to textColor
        false -> MaterialTheme.colorScheme.inverseSurface to MaterialTheme.colorScheme.inverseOnSurface
    }

    var dropDownWidth by remember { mutableStateOf(0) }
    var expanded by rememberSaveable { mutableStateOf(false) }
    Column(
        modifier = modifier
    ) {
        BasicButton(
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { dropDownWidth = it.width },
            contentPadding = contentPadding,
            text = selectedValue?.second,
            textStyle = textStyle,
            textOverflow = TextOverflow.Ellipsis,
            textMaxLines = 1,
            iconPadding = 8.dp,
            iconEnd = if (expanded) R.drawable.expand_less_20px else R.drawable.expand_more_20px,
            isClickable = isClickable,
            backgroundColor = finalBackgroundColor,
            textColor = finalTextColor
        ) {
            expanded = !expanded
        }

        DropdownMenu(
            modifier = Modifier
                .width(with(LocalDensity.current) { dropDownWidth.toDp() })
                .background(color = backgroundColor),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            list.forEach {
                DropdownMenuItem(
                    text = { Text(text = it.second, color = textColor, modifier = Modifier.fillMaxWidth()) },
                    onClick = {
                        expanded = false
                        onClick.invoke(it)
                    }
                )
            }
        }
    }
}