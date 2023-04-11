package com.cheesejuice.fancymansion.ui.common

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

enum class ErrorType{
    Toast, Dialog, Skip
}
data class ErrorData(val errorType : ErrorType, val title : String, val message : String)
sealed class UiState {
    object Empty : UiState()
    class Error(val errorData: ErrorData, val onConfirm:()-> Unit = {}, val onDismiss:()-> Unit = {}) : UiState()
    class Loading(val message: String?, val onDismiss:()-> Unit = {}) : UiState()
    class Loaded(val message: String? = null) : UiState()

    // class Permission(val message: String?) : UiState()
}

abstract class BaseViewModel : ViewModel(){
    protected val _uiState = MutableStateFlow<UiState>(UiState.Empty)
    val uiState: StateFlow<UiState> = _uiState

    fun showLoading(message : String? = null, onDismiss:()-> Unit = {}) {
        if(_uiState.value !is UiState.Loading){
            _uiState.value = UiState.Loading(
                message = message,
                onDismiss = onDismiss
            )
        }
    }

    fun showErrorDialog(
        title : String,
        message : String,
        onConfirm: ()-> Unit = { dismissDialog() },
        onDismiss:()-> Unit = { dismissDialog() })
    {
        if(_uiState.value !is UiState.Error){
            _uiState.value = UiState.Error(
                ErrorData(
                    errorType = ErrorType.Dialog,
                    title = title,
                    message = message,
                ),
                onConfirm = onConfirm,
                onDismiss = onDismiss
            )
        }
    }

    protected fun dismissDialog() {
        if(_uiState.value !is UiState.Loaded){
            _uiState.value = UiState.Loaded(null)
        }
    }

    fun showErrorToast(
        title : String,
        message : String)
    {
        _uiState.value = UiState.Error(
            ErrorData(
                errorType = ErrorType.Toast,
                title = title,
                message = message,
            ),
            onDismiss = { dismissDialog() }
        )
    }
}