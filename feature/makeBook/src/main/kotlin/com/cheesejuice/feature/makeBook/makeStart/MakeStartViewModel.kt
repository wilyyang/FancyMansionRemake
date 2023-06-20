package com.cheesejuice.feature.makeBook.makeStart

import androidx.lifecycle.SavedStateHandle
import com.cheesejuice.core.common.LOCAL_USER_ID
import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.core.common.SAMPLE_BOOK_ID
import com.cheesejuice.core.common.resource.StringResource
import com.cheesejuice.core.common.throwable.FileNotFoundCancellationException
import com.cheesejuice.core.common.throwable.ShowErrorType
import com.cheesejuice.core.ui.base.BaseViewModel
import com.cheesejuice.core.ui.base.LoadState
import com.cheesejuice.domain.entity.makebook.book.toEditable
import com.cheesejuice.domain.usecase.makeBook.UseCaseMakeSample
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookConfigFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookCoverImageFromFile
import com.cheesejuice.domain.usecase.user.UseCaseCheckIsFirstExecute
import com.cheesejuice.domain.usecase.user.UseCaseGetUserId
import com.cheesejuice.domain.usecase.user.UseCaseInitUserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class MakeStartViewModel @Inject constructor(
    private val savedStateHandle : SavedStateHandle,
    private val useCaseInitUserInfo : UseCaseInitUserInfo,
    private val useCaseCheckIsFirstExecute : UseCaseCheckIsFirstExecute,
    private val useCaseMakeSample : UseCaseMakeSample,

    private val useCaseGetBookConfigFromFile : UseCaseGetBookConfigFromFile,
    private val useCaseGetBookCoverImageFromFile : UseCaseGetBookCoverImageFromFile,

    private val useCaseGetUserId : UseCaseGetUserId
) : BaseViewModel<MakeStartContract.State, MakeStartContract.Event, MakeStartContract.Effect>()  {

    private lateinit var userId : String
    private val readMode : ReadMode = ReadMode.edit
    private lateinit var bookId : String

    override fun setInitialState() = MakeStartContract.State(
        config = null, coverImage = null,
        emptyMessage = StringResource.empty_message_load_data
    )

    override fun handleEvents(event : MakeStartContract.Event) {
        when (event) {
            is MakeStartContract.Event.ReadStartPreviewClicked -> {
                launchWithLoading {
                    setEffect {
                        MakeStartContract.Effect.Navigation.NavigateReadStart(
                            userId = userId,
                            readMode = readMode,
                            bookId = bookId
                        )
                    }
                }
            }
            else -> {}
        }
    }

    init {
        launchWithLoading {
            // 임시 영역
            if(!useCaseCheckIsFirstExecute()){
                useCaseInitUserInfo(userId = LOCAL_USER_ID)
                useCaseMakeSample()
            }
            // Get Data
            userId = useCaseGetUserId()
            bookId = SAMPLE_BOOK_ID

            val configLocal = useCaseGetBookConfigFromFile(userId = userId, readMode = readMode, bookId = bookId)
            val coverImage = configLocal?.let {
                useCaseGetBookCoverImageFromFile(userId = userId, readMode = readMode, bookId = bookId, configLocal.coverImage)
            }

            if (configLocal != null) {
                setState {
                    copy(
                        config = configLocal.toEditable(), coverImage = coverImage,
                        emptyMessage = null
                    )
                }
            } else {
                cancel(
                    cause = FileNotFoundCancellationException(
                        message = "[$bookId Book] Data not found, Config is null"
                    )
                )
            }
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