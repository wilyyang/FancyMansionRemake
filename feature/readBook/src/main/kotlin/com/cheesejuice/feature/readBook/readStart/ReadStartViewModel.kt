package com.cheesejuice.feature.readBook.readStart

import com.cheesejuice.core.common.LOCAL_USER_ID
import com.cheesejuice.core.common.NOT_ASSIGN_SAVE_PAGE
import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.core.common.SAMPLE_BOOK_ID
import com.cheesejuice.core.common.resource.StringResource
import com.cheesejuice.core.common.throwable.FileNotFoundCancellationException
import com.cheesejuice.core.common.throwable.ShowErrorType
import com.cheesejuice.core.ui.base.BaseViewModel
import com.cheesejuice.core.ui.base.LoadState
import com.cheesejuice.domain.entity.readbook.book.ConfigEntity
import com.cheesejuice.domain.usecase.makeBook.UseCaseMakeSample
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookConfigFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseGetReadRecord
import com.cheesejuice.domain.usecase.readBook.UseCaseInitReadRecord
import com.cheesejuice.domain.usecase.user.UseCaseGetUserId
import com.cheesejuice.domain.usecase.user.UseCaseInitUserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class ReadStartViewModel @Inject constructor(
    private val useCaseInitUserInfo : UseCaseInitUserInfo,
    private val useCaseMakeSample : UseCaseMakeSample,
    private val useCaseGetBookConfigFromFile : UseCaseGetBookConfigFromFile,

    private val useCaseInitReadRecord : UseCaseInitReadRecord,
    private val useCaseGetUserId : UseCaseGetUserId,
    private val useCaseGetReadRecord : UseCaseGetReadRecord,
) : BaseViewModel<ReadStartContract.State, ReadStartContract.Event, ReadStartContract.Effect>()  {

    private lateinit var userId : String
    private val readMode = ReadMode.edit
    private val bookId = SAMPLE_BOOK_ID
    private lateinit var config : ConfigEntity

    override fun setInitialState() = ReadStartContract.State(config = null)

    override fun handleEvents(event : ReadStartContract.Event) {
        when (event) {
            is ReadStartContract.Event.ReadStartClicked -> {
                setEffect { ReadStartContract.Effect.Navigation.ReadStart(config = event.config) }
            }

            is ReadStartContract.Event.BackButtonClicked -> {
                setEffect { ReadStartContract.Effect.Navigation.Back }
            }
            else -> {}
        }
    }

    init {
        launchWithLoading {
            // 임시 영역
            useCaseInitUserInfo(userId = LOCAL_USER_ID)
            useCaseMakeSample()

            // User Id
            userId = useCaseGetUserId()
            useCaseInitReadRecord(userId = userId, readMode = readMode.name, bookId = bookId, savePage = NOT_ASSIGN_SAVE_PAGE)

            // get config
            val configLocal = useCaseGetBookConfigFromFile(userId = userId, readMode = readMode, bookId = bookId)

            if (configLocal != null) {
                config = configLocal
                setState { copy(config = config) }
            } else {
                cancel(cause = FileNotFoundCancellationException(message = "[$bookId Book] config is null"))
            }
        }
    }

    override fun showErrorResult(result : ShowErrorType) {
        when(result.throwable){
            is FileNotFoundCancellationException -> {
                setLoadState(
                    LoadState.ErrorDialog(
                        title = "Book 파일을 찾을 수 없습니다.",
                        message = result.throwable.message ?: StringResource.error_empty_message,
                        onConfirm = {
                            setEffect { ReadStartContract.Effect.Navigation.Back }
                        },
                        onDismiss = {
                            setEffect { ReadStartContract.Effect.Navigation.Back }
                        }
                    ))
            }
            else -> {
                super.showErrorResult(result)
            }
        }
    }
}