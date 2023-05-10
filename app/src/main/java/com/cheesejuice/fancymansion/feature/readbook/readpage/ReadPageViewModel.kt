package com.cheesejuice.fancymansion.feature.readbook.readpage

import androidx.compose.runtime.mutableStateOf
import com.cheesejuice.fancymansion.core.common.INIT_ID
import com.cheesejuice.fancymansion.core.common.LOCAL_USER_ID
import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.common.SAMPLE_BOOK_ID
import com.cheesejuice.fancymansion.core.domain.library.file.UseCaseGetBookConfigFromFile
import com.cheesejuice.fancymansion.core.domain.library.file.UseCaseGetBookLogicFromFile
import com.cheesejuice.fancymansion.core.domain.library.file.UseCaseGetBookPageFromFile
import com.cheesejuice.fancymansion.core.domain.library.file.UseCaseMakeSample
import com.cheesejuice.fancymansion.core.domain.library.record.UseCaseDecideRoute
import com.cheesejuice.fancymansion.core.domain.library.record.UseCaseGetReadRecord
import com.cheesejuice.fancymansion.core.domain.library.record.UseCaseRecordReadElement
import com.cheesejuice.fancymansion.core.entity.book.ChoiceItemEntity
import com.cheesejuice.fancymansion.core.entity.book.ConfigEntity
import com.cheesejuice.fancymansion.core.entity.book.LogicEntity
import com.cheesejuice.fancymansion.core.entity.book.PageContentEntity
import com.cheesejuice.fancymansion.core.entity.book.PageEntity
import com.cheesejuice.fancymansion.core.entity.book.PageLogicEntity
import com.cheesejuice.fancymansion.core.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class ReadPageViewModel @Inject constructor(
    private val useCaseMakeSample : UseCaseMakeSample,
    private val useCaseGetBookConfigFromFile : UseCaseGetBookConfigFromFile,
    private val useCaseGetBookLogicFromFile : UseCaseGetBookLogicFromFile,
    private val useCaseGetBookPageFromFile : UseCaseGetBookPageFromFile,

    private val useCaseDecideRoute : UseCaseDecideRoute,
    private val useCaseGetReadRecord : UseCaseGetReadRecord,
    private val useCaseRecordReadElement : UseCaseRecordReadElement,
) : BaseViewModel() {
    private val userId = LOCAL_USER_ID
    private val readMode = ReadMode.edit
    private val bookId = SAMPLE_BOOK_ID
    private val initBook = true
    private lateinit var config : ConfigEntity
    private lateinit var logic : LogicEntity

    val page = mutableStateOf(
        PageEntity(
            content = PageContentEntity(pageId = INIT_ID, pageTitle = "", question = ""),
            logic = PageLogicEntity(pageId = INIT_ID, pageTitle = "")
        )
    )

    init {
        launchWithLoading {
            // make file
            useCaseMakeSample(userId = userId, readMode = readMode, bookId = bookId)

            // get file
            val configLocal = useCaseGetBookConfigFromFile(userId = userId, readMode = readMode, bookId = bookId)
            val logicLocal = useCaseGetBookLogicFromFile(userId = userId, readMode = readMode, bookId = bookId)
            if (configLocal != null && logicLocal != null) {
                config = configLocal
                logic = logicLocal
                val readData = useCaseGetReadRecord(userId = userId, config = config, initBook = initBook)
                movePageFromId(readData.savePage, isStartPage = true)
            } else {
                cancel(
                    message = "[$bookId Book] config is ${if (configLocal == null) "" else "not"} null, " +
                        "logic is ${if (logicLocal == null) "" else "not"} null"
                )
            }
        }
    }

    fun onClickChoiceItem(choice : ChoiceItemEntity) {
        launchWithLoading {
            useCaseRecordReadElement(userId = userId, readMode = readMode.name, bookId = bookId, elementId = choice.choiceId)
            val nextPageId = useCaseDecideRoute(userId = userId, readMode = readMode.name, bookId = bookId, choice = choice)
            movePageFromId(nextPageId)
        }
    }

    private suspend fun movePageFromId(pageId : Long, isStartPage : Boolean = false) {
        delay(300)
        val page = useCaseGetBookPageFromFile(userId = userId, readMode = readMode, bookId = bookId, pageId = pageId, logic = logic)
        if (page != null) {
            this@ReadPageViewModel.page.value = page
            useCaseRecordReadElement(
                userId = userId, readMode = readMode.name, bookId = bookId, elementId = pageId,
                isStartPage = isStartPage
            )
        } else {
            cancel(message = "[$bookId Book] page[$pageId] is null")
        }
    }
}