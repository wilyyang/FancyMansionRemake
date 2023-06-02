package com.cheesejuice.feature.readBook.readPage

import androidx.lifecycle.SavedStateHandle
import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.core.common.resource.StringResource
import com.cheesejuice.core.common.throwable.FileNotFoundCancellationException
import com.cheesejuice.core.common.throwable.ShowErrorType
import com.cheesejuice.core.ui.base.BaseViewModel
import com.cheesejuice.core.ui.base.LoadState
import com.cheesejuice.domain.entity.readbook.book.ChoiceItemEntity
import com.cheesejuice.domain.entity.readbook.book.LogicEntity
import com.cheesejuice.domain.usecase.readBook.UseCaseDecideRoute
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookLogicFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookPageFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseRecordReadElement
import com.cheesejuice.domain.usecase.readBook.UseCaseRecordSavePageId
import com.cheesejuice.feature.readBook.readPage.Navigation.Args.BOOK_ID
import com.cheesejuice.feature.readBook.readPage.Navigation.Args.PAGE_ID
import com.cheesejuice.feature.readBook.readPage.Navigation.Args.READ_MODE
import com.cheesejuice.feature.readBook.readPage.Navigation.Args.USER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class ReadPageViewModel @Inject constructor(
    private val savedStateHandle : SavedStateHandle,
    private val useCaseGetBookLogicFromFile : UseCaseGetBookLogicFromFile,
    private val useCaseGetBookPageFromFile : UseCaseGetBookPageFromFile,
    private val useCaseRecordSavePageId : UseCaseRecordSavePageId,

    private val useCaseDecideRoute : UseCaseDecideRoute,
    private val useCaseRecordReadElement : UseCaseRecordReadElement,
) : BaseViewModel<ReadPageContract.State, ReadPageContract.Event, ReadPageContract.Effect>()  {

    private lateinit var userId : String
    private lateinit var readMode : ReadMode
    private lateinit var bookId : String
    private lateinit var logic : LogicEntity
    override fun setInitialState() = ReadPageContract.State(page = null)

    override fun handleEvents(event : ReadPageContract.Event) {
        when (event) {
            is ReadPageContract.Event.ChoiceItemClicked -> onChoiceItemClicked(event.choice)

            is ReadPageContract.Event.BackButtonClicked -> {
                setEffect { ReadPageContract.Effect.Navigation.Back }
            }
        }
    }

    init {
        launchWithLoading {
            // Get Data
            userId = savedStateHandle[USER_ID]!!
            readMode = ReadMode.from(savedStateHandle[READ_MODE]!!)
            bookId = savedStateHandle[BOOK_ID]!!
            val savePageId : Long = savedStateHandle[PAGE_ID]!!
            val logicLocal = useCaseGetBookLogicFromFile(userId = userId, readMode = readMode, bookId = bookId)

            // Move Page
            if (logicLocal != null) {
                logic = logicLocal
                movePageFromId(savePageId, isStartPage = true)
            } else {
                cancel(
                    cause = FileNotFoundCancellationException(message = "[$bookId Book] logic is null")
                )
            }
        }
    }

    private fun onChoiceItemClicked(choice : ChoiceItemEntity) = launchWithLoading {
        useCaseRecordReadElement(userId = userId, readMode = readMode.name, bookId = bookId, elementId = choice.choiceId)
        movePageFromId(pageId = useCaseDecideRoute(userId = userId, readMode = readMode.name, bookId = bookId, choice = choice))
    }

    private suspend fun movePageFromId(pageId : Long, isStartPage : Boolean = false) =
        useCaseGetBookPageFromFile(userId = userId, readMode = readMode, bookId = bookId, pageId = pageId, logic = logic)?.let {
            delay(300L)
            useCaseRecordReadElement(
                userId = userId,
                readMode = readMode.name,
                bookId = bookId,
                elementId = pageId,
                isStartPage = isStartPage
            )
            useCaseRecordSavePageId(userId = userId, readMode = readMode.name, bookId = bookId, savePageId = pageId)
            setState { copy(page = it) }
        } ?: {
            cancel(cause = FileNotFoundCancellationException(message = "[$bookId Book] page[$pageId] is null"))
        }

    override fun showErrorResult(result : ShowErrorType) {
        when(result.throwable){
            is FileNotFoundCancellationException -> {
                setLoadState(
                    LoadState.ErrorDialog(
                        title = "Book 정보를 찾을 수 없습니다.",
                        message = result.throwable.message ?: StringResource.error_empty_message,
                        onConfirm = {
                            setEffect { ReadPageContract.Effect.Navigation.Back }
                        },
                        onDismiss = {
                            setEffect { ReadPageContract.Effect.Navigation.Back }
                        }
                    ))
            }
            else -> {
                super.showErrorResult(result)
            }
        }
    }
}