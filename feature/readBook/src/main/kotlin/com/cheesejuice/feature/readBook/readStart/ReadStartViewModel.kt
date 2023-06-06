package com.cheesejuice.feature.readBook.readStart

import androidx.lifecycle.SavedStateHandle
import com.cheesejuice.core.common.LOCAL_USER_ID
import com.cheesejuice.core.common.NOT_ASSIGN_SAVE_PAGE
import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.core.common.SAMPLE_BOOK_ID
import com.cheesejuice.core.common.resource.StringResource
import com.cheesejuice.core.common.throwable.FileNotFoundCancellationException
import com.cheesejuice.core.common.throwable.ShowErrorType
import com.cheesejuice.core.ui.base.BaseViewModel
import com.cheesejuice.core.ui.base.LoadState
import com.cheesejuice.domain.usecase.makeBook.UseCaseMakeSample
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookConfigFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookCoverImageFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookPageContentFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookPageImageFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseGetReadRecord
import com.cheesejuice.domain.usecase.readBook.UseCaseInitReadRecord
import com.cheesejuice.domain.usecase.user.UseCaseCheckIsFirstExecute
import com.cheesejuice.domain.usecase.user.UseCaseGetUserId
import com.cheesejuice.domain.usecase.user.UseCaseInitUserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class ReadStartViewModel @Inject constructor(
    private val savedStateHandle : SavedStateHandle,
    private val useCaseCheckIsFirstExecute : UseCaseCheckIsFirstExecute,
    private val useCaseInitUserInfo : UseCaseInitUserInfo,
    private val useCaseMakeSample : UseCaseMakeSample,

    private val useCaseGetBookConfigFromFile : UseCaseGetBookConfigFromFile,
    private val useCaseGetBookCoverImageFromFile : UseCaseGetBookCoverImageFromFile,
    private val useCaseGetBookPageContentFromFile : UseCaseGetBookPageContentFromFile,
    private val useCaseGetBookPageImageFromFile : UseCaseGetBookPageImageFromFile,

    private val useCaseGetUserId : UseCaseGetUserId,
    private val useCaseInitReadRecord : UseCaseInitReadRecord,
    private val useCaseGetReadRecord : UseCaseGetReadRecord,
) : BaseViewModel<ReadStartContract.State, ReadStartContract.Event, ReadStartContract.Effect>()  {

    private lateinit var userId : String
    private lateinit var readMode : ReadMode
    private lateinit var bookId : String
    override fun setInitialState() = ReadStartContract.State(
        config = null, coverImage = null,
        savePageId = NOT_ASSIGN_SAVE_PAGE, savePageTitle = null, savePageImage = null,
        emptyMessage = StringResource.empty_message_load_data
    )

    override fun handleEvents(event : ReadStartContract.Event) {
        when (event) {
            is ReadStartContract.Event.ReadStartFirstPageClicked -> {
                launchWithLoading {
                    useCaseInitReadRecord(config = uiState.value.config!!)

                    setEffect {
                        ReadStartContract.Effect.Navigation.ReadStart(
                            userId = userId,
                            readMode = readMode,
                            bookId = bookId,
                            pageId = uiState.value.config!!.defaultStartPageId
                        )
                    }
                }
            }

            is ReadStartContract.Event.ReadStartSavePointClicked -> {
                setEffect {
                    ReadStartContract.Effect.Navigation.ReadStart(
                        userId = userId,
                        readMode = readMode,
                        bookId = bookId,
                        pageId = uiState.value.savePageId
                    )
                }
            }
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
            readMode = ReadMode.edit
            bookId = SAMPLE_BOOK_ID

            val configLocal = useCaseGetBookConfigFromFile(userId = userId, readMode = readMode, bookId = bookId)
            val coverImage = configLocal?.let {
                useCaseGetBookCoverImageFromFile(userId = userId, readMode = readMode, bookId = bookId, configLocal.coverImage)
            }
            val savePageId = configLocal?.let {
                useCaseGetReadRecord(userId = userId, config = configLocal).savePage
            }

            val pageContent = if (savePageId != null && savePageId != NOT_ASSIGN_SAVE_PAGE) {
                useCaseGetBookPageContentFromFile(userId = userId, readMode = readMode, bookId = bookId, pageId = savePageId)
            } else null

            val savePageImage = pageContent?.let {
                useCaseGetBookPageImageFromFile(userId = userId, readMode = readMode, bookId = bookId, image = pageContent.pageImage)
            }

            if (configLocal != null && coverImage != null && savePageId != null) {
                setState {
                    copy(
                        config = configLocal, coverImage = coverImage,
                        savePageId = savePageId, savePageTitle = pageContent?.pageTitle, savePageImage = savePageImage,
                        emptyMessage = null
                    )
                }
            } else {
                cancel(
                    cause = FileNotFoundCancellationException(
                        message = "[$bookId Book] Data not found, " +
                            (if (configLocal == null) "Config is null, " else "") +
                            (if (coverImage == null) "CoverImage is null, " else "") +
                            (if (savePageId == null) "SavePageId is null" else "")
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