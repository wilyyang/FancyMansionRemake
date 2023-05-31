package com.cheesejuice.core.ui.base

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cheesejuice.core.common.throwable.ErrorType
import com.cheesejuice.core.common.throwable.ThrowableManager
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlin.coroutines.CoroutineContext

interface ViewState
interface ViewEvent
interface ViewSideEffect

sealed class LoadState {
    object Idle : LoadState()
    data class Loading(val message : String? = null) : LoadState()
    data class ErrorDialog(
        val title : String,
        val message : String,
        val onConfirm : () -> Unit = {},
        val onDismiss : () -> Unit = {}
    ) : LoadState()
}

const val SIDE_EFFECTS_KEY = "side_effects_key"
const val DEFAULT_DELAY_TIME = 10000L

abstract class BaseViewModel<UiState : ViewState, Event : ViewEvent, Effect : ViewSideEffect> : ViewModel(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext + CoroutineExceptionHandler { _, throwable ->

            when(val result = ThrowableManager.sendError(throwable)){
                is ErrorType.Dialog -> {
                    _loadState.value = LoadState.ErrorDialog(
                        title = result.title,
                        message = result.message
                    )
                }
                else -> {}
            }
        }

    /**
     * BaseViewModel 을 상속하는 ViewModel은
     * UiState의 초기값을 할당하는 setInitialState 와
     * 전달받은 Event를 처리할 handleEvents 를 구현해야 한다.
     */
    abstract fun setInitialState() : UiState
    abstract fun handleEvents(event : Event)

    private val initialState : UiState by lazy { setInitialState() }

    /**
     * _uiState : Screen에 전달할 데이터
     * _loadState : Screen에 dialog로 호출되는 공통 요소의 상태
     * _event : Screen 으로부터 전달 받을 이벤트
     * _effect : Screen 에 전달할 이펙트
     */
    private val _uiState : MutableState<UiState> = mutableStateOf(initialState)
    private val _loadState : MutableState<LoadState> = mutableStateOf(LoadState.Idle)
    private val _event : MutableSharedFlow<Event> = MutableSharedFlow()
    private val _effect : Channel<Effect> = Channel()

    val uiState : State<UiState> = _uiState
    val loadState : State<LoadState> = _loadState
    val effect = _effect.receiveAsFlow()

    /**
     * Event는 ViewModel 에서 수집하여 처리한다
     */
    init {
        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        launch {
            _event.collect {
                handleEvents(it)
            }
        }
    }

    /**
     * UiState, Event, Effect의 Setter
     */
    protected fun setState(reducer : UiState.() -> UiState) {
        val newState = uiState.value.reducer()
        _uiState.value = newState
    }

    fun setEvent(event : Event) {
        launch { _event.emit(event) }
    }

    protected fun setEffect(builder : () -> Effect) {
        val effectValue = builder()
        launch { _effect.send(effectValue) }
    }

    /**
     * 로딩
     */
    fun launchWithLoading(
        context : CoroutineContext = Dispatchers.IO,
        start : CoroutineStart = CoroutineStart.DEFAULT,
        delayTime : Long = DEFAULT_DELAY_TIME,
        block : suspend CoroutineScope.() -> Unit
    ) : Job {
        return launch(context, start) {
            withContext(Dispatchers.Main) {
                _loadState.value = LoadState.Loading()
                withContext(context = context) {
                    withTimeout(delayTime) {
                        block.invoke(this)
                    }
                }
                _loadState.value = LoadState.Idle
            }
        }
    }
}