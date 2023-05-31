package com.cheesejuice.feature.readBook.readPage

import com.cheesejuice.core.common.LOCAL_USER_ID
import com.cheesejuice.core.common.NOT_ASSIGN_SAVE_PAGE
import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.core.common.SAMPLE_BOOK_ID
import com.cheesejuice.core.ui.base.BaseViewModel
import com.cheesejuice.domain.entity.readbook.book.ChoiceItemEntity
import com.cheesejuice.domain.entity.readbook.book.ConfigEntity
import com.cheesejuice.domain.entity.readbook.book.LogicEntity
import com.cheesejuice.domain.usecase.makeBook.UseCaseMakeSample
import com.cheesejuice.domain.usecase.readBook.UseCaseDecideRoute
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookConfigFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookLogicFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseGetBookPageFromFile
import com.cheesejuice.domain.usecase.readBook.UseCaseGetReadRecord
import com.cheesejuice.domain.usecase.readBook.UseCaseInitReadRecord
import com.cheesejuice.domain.usecase.readBook.UseCaseRecordReadElement
import com.cheesejuice.domain.usecase.user.UseCaseGetUserId
import com.cheesejuice.domain.usecase.user.UseCaseInitUserInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class ReadPageViewModel @Inject constructor(
    private val useCaseInitUserInfo : UseCaseInitUserInfo,
    private val useCaseMakeSample : UseCaseMakeSample,
    private val useCaseGetBookConfigFromFile : UseCaseGetBookConfigFromFile,
    private val useCaseGetBookLogicFromFile : UseCaseGetBookLogicFromFile,
    private val useCaseGetBookPageFromFile : UseCaseGetBookPageFromFile,

    private val useCaseInitReadRecord : UseCaseInitReadRecord,
    private val useCaseGetUserId : UseCaseGetUserId,
    private val useCaseDecideRoute : UseCaseDecideRoute,
    private val useCaseGetReadRecord : UseCaseGetReadRecord,
    private val useCaseRecordReadElement : UseCaseRecordReadElement,
) : BaseViewModel<ReadPageContract.State, ReadPageContract.Event, ReadPageContract.Effect>()  {

    private lateinit var userId : String
    private val readMode = ReadMode.edit
    private val bookId = SAMPLE_BOOK_ID
    private lateinit var config : ConfigEntity
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
            // 임시 영역
            useCaseInitUserInfo(userId = LOCAL_USER_ID)
            useCaseMakeSample()

            // User Id
            userId = useCaseGetUserId()
            useCaseInitReadRecord(userId = userId, readMode = readMode.name, bookId = bookId, savePage = NOT_ASSIGN_SAVE_PAGE)

            // get config & logic
            val configLocal = useCaseGetBookConfigFromFile(userId = userId, readMode = readMode, bookId = bookId)
            val logicLocal = useCaseGetBookLogicFromFile(userId = userId, readMode = readMode, bookId = bookId)

            // get page
            if (configLocal != null && logicLocal != null) {
                config = configLocal
                logic = logicLocal

                val movePageId = useCaseGetReadRecord(userId = userId, config = config).let {
                    if(it.savePage != NOT_ASSIGN_SAVE_PAGE) it.savePage else config.defaultStartPageId
                }
                movePageFromId(movePageId, isStartPage = true)
            } else {
                cancel(
                    message = "[$bookId Book] config is ${if (configLocal == null) "" else "not"} null, " +
                        "logic is ${if (logicLocal == null) "" else "not"} null"
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
            useCaseRecordReadElement(
                userId = userId,
                readMode = readMode.name,
                bookId = bookId,
                elementId = pageId,
                isStartPage = isStartPage
            )
            setState { copy(page = it) }
        } ?: {
            cancel(message = "[$bookId Book] page[$pageId] is null")
        }
}