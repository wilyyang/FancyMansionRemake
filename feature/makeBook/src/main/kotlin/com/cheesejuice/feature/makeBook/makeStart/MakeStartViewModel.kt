package com.cheesejuice.feature.makeBook.makeStart

import androidx.lifecycle.SavedStateHandle
import com.cheesejuice.core.common.resource.StringResource
import com.cheesejuice.core.common.throwable.FileNotFoundCancellationException
import com.cheesejuice.core.common.throwable.ShowErrorType
import com.cheesejuice.core.ui.base.BaseViewModel
import com.cheesejuice.core.ui.base.LoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MakeStartViewModel @Inject constructor(
    private val savedStateHandle : SavedStateHandle
) : BaseViewModel<MakeStartContract.State, MakeStartContract.Event, MakeStartContract.Effect>()  {

    private lateinit var userId : String
    private lateinit var bookId : String

    override fun setInitialState() = MakeStartContract.State(
        coverImage = null,
        emptyMessage = StringResource.empty_message_load_data
    )

    override fun handleEvents(event : MakeStartContract.Event) {
        when (event) {
            else -> {}
        }
    }

    init {
        launchWithLoading {
            // 임시 영역

            // Get Data

        }
    }

    override fun showErrorResult(result : ShowErrorType) {
        when(result.throwable){
            is FileNotFoundCancellationException -> {
                setLoadState(
                    LoadState.ErrorDialog(
                        title = "Book 정보를 찾을 수 없습니다.",
                        message = result.throwable.message ?: StringResource.error_empty_message,
                        onConfirm = {
                            /*TODO*/
                        },
                        onDismiss = {
                            /*TODO*/
                        }
                    ))
            }
            else -> {
                super.showErrorResult(result)
            }
        }
    }
}