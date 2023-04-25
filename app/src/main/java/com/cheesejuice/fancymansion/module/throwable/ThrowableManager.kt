package com.cheesejuice.fancymansion.module.throwable

import com.cheesejuice.fancymansion.StringResource.error_empty_message
import com.cheesejuice.fancymansion.StringResource.error_title
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ErrorState(
    val title : String = error_title,
    val message : String,
    val onConfirm : () -> Unit = {},
    val onDismiss : () -> Unit = {}
)

object ThrowableManager {
    private val _errorState : MutableStateFlow<ErrorState?> = MutableStateFlow(null)
    val errorState : StateFlow<ErrorState?> = _errorState.asStateFlow()

    private val scopeApplication by lazy { CoroutineScope(SupervisorJob()) }

    fun sendError(throwable : Throwable) {
        scopeApplication.launch {
            showErrorDialog(throwable = throwable)
        }
    }

    suspend fun showErrorDialog(throwable : Throwable, onConfirm : () -> Unit = {}, onDismiss : () -> Unit = { _errorState.value = null }) {
        _errorState.emit(
            ErrorState(title = throwable.javaClass.name,
            message = throwable.message ?: error_empty_message,
            onConfirm = onConfirm, onDismiss = onDismiss))
    }

    suspend fun dismissErrorDialog() {
        _errorState.emit(null)
    }
}