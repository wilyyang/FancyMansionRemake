package com.cheesejuice.feature.readBook.readStart

import androidx.lifecycle.SavedStateHandle
import com.cheesejuice.core.common.NOT_ASSIGN_SAVE_PAGE
import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.core.common.resource.StringResource
import com.cheesejuice.core.common.throwable.FileNotFoundCancellationException
import com.cheesejuice.core.common.throwable.ShowErrorType
import com.cheesejuice.core.ui.base.BaseViewModel
import com.cheesejuice.core.ui.base.LoadState
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookConfigFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookCoverImageFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookPageContentFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookPageImageFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseGetReadRecord
import com.cheesejuice.domain.usecase.readBook.UseCaseInitReadRecord
import com.cheesejuice.feature.readBook.readPage.Navigation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class ReadStartViewModel @Inject constructor(
    private val savedStateHandle : SavedStateHandle,
    private val useCaseGetBookConfigFromFile : UseCaseGetBookConfigFromFile,
    private val useCaseGetBookCoverImageFromFile : UseCaseGetBookCoverImageFromFile,
    private val useCaseGetBookPageContentFromFile : UseCaseGetBookPageContentFromFile,
    private val useCaseGetBookPageImageFromFile : UseCaseGetBookPageImageFromFile,

    private val useCaseInitReadRecord : UseCaseInitReadRecord,
    private val useCaseGetReadRecord : UseCaseGetReadRecord,
) : BaseViewModel<ReadStartContract.State, ReadStartContract.Event, ReadStartContract.Effect>() {

    private lateinit var userId : String
    private lateinit var readMode : ReadMode
    private lateinit var bookId : String
    private var isPopBackStack : Boolean = false

    override fun setInitialState() = ReadStartContract.State(
        config = null, coverImage = null,
        savePageId = NOT_ASSIGN_SAVE_PAGE, savePageTitle = null, savePageImage = null,
        emptyMessage = StringResource.empty_message_load_data
    )

    override fun handleEvents(event : ReadStartContract.Event) {
        when (event) {
            is ReadStartContract.Event.ReadPageFirstClicked -> {
                launchWithLoading {
                    useCaseInitReadRecord(config = uiState.value.config!!)

                    setEffect {
                        ReadStartContract.Effect.Navigation.NavigateReadPage(
                            userId = userId,
                            readMode = readMode,
                            bookId = bookId,
                            pageId = uiState.value.config!!.defaultStartPageId
                        )
                    }
                }
            }

            is ReadStartContract.Event.ReadPageSavePointClicked -> {
                setEffect {
                    ReadStartContract.Effect.Navigation.NavigateReadPage(
                        userId = userId,
                        readMode = readMode,
                        bookId = bookId,
                        pageId = uiState.value.savePageId
                    )
                }
            }

            is ReadStartContract.Event.OnCreateScreen -> {
                if(isPopBackStack){
                    launchWithLoading {
                        updateSavePageInfo()
                    }
                }else{
                    isPopBackStack = true
                }
            }

            is ReadStartContract.Event.BackButtonClicked -> {
                setEffect { ReadStartContract.Effect.Navigation.Back }
            }
        }
    }

    init {
        launchWithLoading {
            userId = savedStateHandle[Navigation.Args.USER_ID]!!
            readMode = ReadMode.from(savedStateHandle[Navigation.Args.READ_MODE]!!)
            bookId = savedStateHandle[Navigation.Args.BOOK_ID]!!

            val configLocal = useCaseGetBookConfigFromFile(userId = userId, readMode = readMode, bookId = bookId)
            val coverImage = configLocal?.let {
                useCaseGetBookCoverImageFromFile(userId = userId, readMode = readMode, bookId = bookId, configLocal.coverImage)
            }

            if (configLocal != null && coverImage != null) {
                setState { copy(config = configLocal, coverImage = coverImage, emptyMessage = null) }
                updateSavePageInfo()
            } else {
                cancel(
                    cause = FileNotFoundCancellationException(
                        message = "[$bookId Book] Data not found, " +
                            (if (configLocal == null) "Config is null, " else "") +
                            (if (coverImage == null) "CoverImage is null, " else "")
                    )
                )
            }
        }
    }

    private suspend fun updateSavePageInfo() {
        val savePageId = uiState.value.config?.let {
            useCaseGetReadRecord(userId = userId, config = it).savePage
        }

        val pageContent = if (savePageId != null && savePageId != NOT_ASSIGN_SAVE_PAGE) {
            useCaseGetBookPageContentFromFile(userId = userId, readMode = readMode, bookId = bookId, pageId = savePageId)
        } else null

        val savePageImage = pageContent?.let {
            useCaseGetBookPageImageFromFile(userId = userId, readMode = readMode, bookId = bookId, image = pageContent.pageImage)
        }

        if (savePageId != null) {
            setState {
                copy(
                    savePageId = savePageId, savePageTitle = pageContent?.pageTitle, savePageImage = savePageImage
                )
            }
        } else {
            cancel(
                cause = FileNotFoundCancellationException(
                    message = "[$bookId Book] Data not found, SavePageId is null"
                )
            )
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