package com.cheesejuice.fancymansion.ui.common.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.cheesejuice.fancymansion.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField(
    modifier : Modifier = Modifier,
    isSingleLine : Boolean = true,

    value : String,
    textStyle : TextStyle = MaterialTheme.typography.bodyMedium,
    hint : String = "",

    visualTransformation : VisualTransformation = VisualTransformation.None,
    imeAction : ImeAction = ImeAction.Default,
    keyboardType : KeyboardType = KeyboardType.Text,
    keyboardActions : KeyboardActions = KeyboardActions.Default,

    onValueChange : (String) -> Unit,
) {
    BasicTextField(
        modifier = modifier,
        singleLine = isSingleLine,

        value = value,
        textStyle = textStyle,

        visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else visualTransformation,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions,
        decorationBox = {
                innerTextField ->
            if(value.isEmpty()){
                Text(
                    text = hint,
                    modifier = Modifier.fillMaxWidth(),
                    style = textStyle.copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                )
            }else {
                innerTextField()
            }
        },
        onValueChange = onValueChange,
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LabelTextField(
    modifier : Modifier = Modifier,

    @androidx.annotation.IntRange(from = 1)
    maxLine : Int = 1,

    value : String,
    textStyle : TextStyle = MaterialTheme.typography.bodyMedium,
    hint : String? = null,

    visualTransformation : VisualTransformation = VisualTransformation.None,
    imeAction : ImeAction = ImeAction.Default,
    keyboardType : KeyboardType = KeyboardType.Text,
    keyboardActions : KeyboardActions = KeyboardActions.Default,

    label : String,
    trailing : @Composable (RowScope.() -> Unit)? = null,
    focusRequester : FocusRequester? = null,
    rememberFocus : MutableState<Boolean> = remember { mutableStateOf(false) },

    onValueChange : (String) -> Unit,
) {
    val labelStyle : TextStyle = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurface)

    Column(modifier = modifier) {
        Text(text = label, style = labelStyle)
        Row(verticalAlignment = Alignment.CenterVertically) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 4.dp)
                    .onFocusChanged {
                        rememberFocus.value = it.isFocused
                    }
                    .apply {
                        focusRequester?.let { focusRequester(focusRequester = it) }
                    },
                textStyle = textStyle.copy(color = MaterialTheme.colorScheme.onSurface),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = keyboardActions,
                singleLine = maxLine == 1,
                maxLines = maxLine,
                visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else visualTransformation,
                decorationBox = { innerTextField ->
                    hint?.also {
                        if (value.isBlank()) {
                            Text(text = it, style = textStyle.copy(color = MaterialTheme.colorScheme.onSurfaceVariant))
                        }
                    }
                    innerTextField()
                },
                cursorBrush = SolidColor(value = MaterialTheme.colorScheme.primary)
            )
            if (rememberFocus.value) {
                ButtonIcon(idIcon = R.drawable.close_20px, modifier = Modifier.size(18.dp)) {
                    onValueChange("")
                }
                if (trailing != null) {
                    Spacer(modifier = Modifier.width(12.dp))
                }
            }
            trailing?.invoke(this)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Divider(
            color = when {
                rememberFocus.value -> MaterialTheme.colorScheme.outlineVariant
                value.isBlank() -> MaterialTheme.colorScheme.outline
                else -> MaterialTheme.colorScheme.outline
            }
        )
    }
}