package com.cheesejuice.fancymansion.ui.common.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.cheesejuice.fancymansion.ui.common.component.BasicButton

@Composable
fun ErrorDialog(
    title : String,
    errorMessage : String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(text = title)
        },
        text = {
            Text(text = errorMessage)
        },
        shape = MaterialTheme.shapes.small,
        confirmButton = {
            TextButton(
                onClick = onConfirm
            ){
                Text("확인")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ){
                Text("취소", color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    )
}