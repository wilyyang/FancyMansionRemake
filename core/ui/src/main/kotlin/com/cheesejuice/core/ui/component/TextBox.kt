package com.cheesejuice.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.cheesejuice.core.ui.R
import com.cheesejuice.core.ui.theme.TextStyleGroup
import com.cheesejuice.core.ui.theme.disableAlpha

@Composable
fun TextBox(
    modifier : Modifier = Modifier,
    @androidx.annotation.IntRange(from = 1)
    minLine : Int = 1,
    maxLine : Int = 1,

    value : String,
    hint : String = "",

    visualTransformation : VisualTransformation = VisualTransformation.None,
    imeAction : ImeAction = ImeAction.Default,
    keyboardType : KeyboardType = KeyboardType.Text,
    keyboardActions : KeyboardActions = KeyboardActions.Default,

    label : String? = null,
    rememberFocus : MutableState<Boolean> = remember { mutableStateOf(false) },
    focusRequester : FocusRequester? = null,

    isDivider : Boolean = false,
    isBorder : Boolean = false,
    borderShape : Shape = MaterialTheme.shapes.extraSmall,
    textPadding : Int = 4,

    error : String? = null,

    styleGroup : TextStyleGroup = TextStyleGroup.Medium,

    isEnabled : Boolean = true,
    onValueChange : (String) -> Unit,
) {
    TextBox(
        modifier = modifier,
        minLine = minLine,
        maxLine = maxLine,

        value = value,
        hint = hint,

        visualTransformation = visualTransformation,
        imeAction = imeAction,
        keyboardType = keyboardType,
        keyboardActions = keyboardActions,

        label = label,
        rememberFocus = rememberFocus,
        focusRequester = focusRequester,

        isDivider = isDivider,
        isBorder = isBorder,
        borderShape = borderShape,
        textPadding = textPadding,

        error = error,

        labelStyle = styleGroup.labelStyle,
        textStyle = styleGroup.bodyStyle,
        errorStyle = styleGroup.labelStyle,

        isEnabled = isEnabled,
        onValueChange = onValueChange
    )
}

@Composable
fun TextBox(
    modifier : Modifier = Modifier,
    @androidx.annotation.IntRange(from = 1)
    minLine : Int = 1,
    maxLine : Int = 1,

    value : String,
    textStyle : TextStyle = MaterialTheme.typography.bodyMedium,
    hint : String = "",

    visualTransformation : VisualTransformation = VisualTransformation.None,
    imeAction : ImeAction = ImeAction.Default,
    keyboardType : KeyboardType = KeyboardType.Text,
    keyboardActions : KeyboardActions = KeyboardActions.Default,

    label : String? = null,
    labelStyle : TextStyle = MaterialTheme.typography.labelMedium,

    rememberFocus : MutableState<Boolean> = remember { mutableStateOf(false) },
    focusRequester : FocusRequester? = null,

    isDivider : Boolean = false,
    isBorder : Boolean = false,
    borderShape : Shape = MaterialTheme.shapes.extraSmall,
    textPadding : Int = 4,

    error : String? = null,
    errorStyle : TextStyle = MaterialTheme.typography.labelMedium,

    isEnabled : Boolean = true,
    onValueChange : (String) -> Unit,
) {

    val finalBackgroundColor = when {
        !isEnabled -> MaterialTheme.colorScheme.surface.copy(alpha = disableAlpha)
        rememberFocus.value -> MaterialTheme.colorScheme.surface
        else -> MaterialTheme.colorScheme.surfaceVariant // not focus
    }

    val contentColor = when {
        !isEnabled -> MaterialTheme.colorScheme.onSurface.copy(alpha = disableAlpha)
        error != null -> MaterialTheme.colorScheme.error
        rememberFocus.value -> MaterialTheme.colorScheme.onSurface
        else -> MaterialTheme.colorScheme.onSurfaceVariant // not focus
    }

    Column(modifier = modifier) {
        // 라벨 영역
        label?.let {
            Text(text = label, style = labelStyle, color = MaterialTheme.colorScheme.outlineVariant)
        }

        Row(
            // 보더 영역
            modifier = (
                if (isBorder) {
                    Modifier
                        .border(
                            width = 1.dp,
                            color = contentColor,
                            shape = borderShape
                        )
                        .background(finalBackgroundColor)
                        .padding(textPadding.dp)
                } else Modifier),
            verticalAlignment = Alignment.CenterVertically) {

            // 입력 영역
            BasicTextField(
                modifier = Modifier.weight(1f)
                    .onFocusChanged {rememberFocus.value = it.isFocused }
                    .apply {focusRequester?.let { focusRequester(focusRequester = it) }},
                enabled = isEnabled,

                singleLine = maxLine == 1,
                minLines = minLine,
                maxLines = maxLine,
                value = value,
                textStyle = textStyle.copy(color = contentColor),

                visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else visualTransformation,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = keyboardType,
                    imeAction = imeAction
                ),
                keyboardActions = keyboardActions,
                decorationBox = { innerTextField ->

                    // 힌트 영역
                    if (isEnabled && value.isEmpty()) {
                        Text(text = hint,  maxLines = maxLine, style = textStyle.copy(color = MaterialTheme.colorScheme.onSurfaceVariant))
                    }
                    innerTextField()
                },
                onValueChange = onValueChange,
            )

            // 취소 버튼 영역
            if (rememberFocus.value) {
                ButtonIconFixed(
                    modifier = Modifier.size(18.dp),
                    idIcon = R.drawable.close_20px,
                    onClick = {
                        onValueChange("")
                    }
                )
            }
        }

        // 디바이더 영역
        if(isDivider){
            Spacer(modifier = Modifier.height(4.dp))
            Divider(color = contentColor)
        }

        // 에러 영역
        error?.let {
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = it, style = errorStyle, color = MaterialTheme.colorScheme.error)
        }
    }
}