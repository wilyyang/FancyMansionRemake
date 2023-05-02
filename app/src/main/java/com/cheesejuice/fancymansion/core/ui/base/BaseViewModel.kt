package com.cheesejuice.fancymansion.core.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheesejuice.fancymansion.core.common.resource.StringResource.empty_message_error
import com.cheesejuice.fancymansion.core.common.resource.StringResource.empty_message_no_data
import com.cheesejuice.fancymansion.core.common.throwable.ThrowableManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.coroutines.CoroutineContext

const val DEFAULT_DELAY_TIME = 10000L

data class LoadingState(val message : String? = null, val onDismiss : () -> Unit = {})
data class EmptyState(val message : String)
abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val _loadingState : MutableStateFlow<LoadingState?> = MutableStateFlow(null)
    val loadingState : StateFlow<LoadingState?> = _loadingState.asStateFlow()

    private val _emptyState : MutableStateFlow<EmptyState?> = MutableStateFlow(null)
    val emptyState : StateFlow<EmptyState?> = _emptyState.asStateFlow()

    override val coroutineContext : CoroutineContext
        get() = viewModelScope.coroutineContext + CoroutineExceptionHandler { _, throwable ->
            ThrowableManager.sendError(throwable)

            dismissLoading(message = empty_message_error)
        }

    fun launchWithLoading(
        context : CoroutineContext = Dispatchers.IO,
        start : CoroutineStart = CoroutineStart.DEFAULT,
        delayTime : Long = DEFAULT_DELAY_TIME,
        block : suspend CoroutineScope.() -> Unit
    ) : Job {
        return launch(context, start) {
            withContext(Dispatchers.Main) {
                showLoading()
                withContext(context = context) {
                    withTimeout(delayTime) {
                        block.invoke(this)
                    }
                }
                dismissLoading()
            }
        }.apply {
            invokeOnCompletion { cause : Throwable? ->
                cause?.also {
                    dismissLoading(message = empty_message_no_data)
                    ThrowableManager.sendError(it)
                }
            }
        }
    }

    fun showLoading(message : String? = null) {
        _loadingState.value = LoadingState(message)
    }

    fun dismissLoading(message : String? = null) {
        _loadingState.value = null
        message?.let {
            _emptyState.value = EmptyState(message = message)
        }
    }
}