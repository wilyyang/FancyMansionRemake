package com.cheesejuice.fancymansion.ui.content.read.page

import androidx.compose.runtime.mutableStateOf
import com.cheesejuice.fancymansion.LOCAL_USER_ID
import com.cheesejuice.fancymansion.ReadMode
import com.cheesejuice.fancymansion.SAMPLE_BOOK_ID
import com.cheesejuice.fancymansion.data.model.ChoiceItem
import com.cheesejuice.fancymansion.data.model.Config
import com.cheesejuice.fancymansion.data.model.InitData
import com.cheesejuice.fancymansion.data.model.Logic
import com.cheesejuice.fancymansion.domain.ReadBookUseCase
import com.cheesejuice.fancymansion.ui.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import javax.inject.Inject

@HiltViewModel
class ReadPageViewModel @Inject constructor(
    private val readBookUseCase : ReadBookUseCase
) : BaseViewModel()
{
    private val userId = LOCAL_USER_ID
    private val readMode = ReadMode.EDIT
    private val bookId = SAMPLE_BOOK_ID
    private val initBook = true
    private lateinit var config : Config
    private lateinit var logic : Logic

    val page = mutableStateOf(InitData.page)

    init {
        launchWithLoading{
            // make file
            readBookUseCase.makeSample(userId = userId, readMode = readMode, bookId = bookId)
            ////
            val configLocal = readBookUseCase.getConfig(userId = userId, readMode = readMode, bookId = bookId)
            val logicLocal = readBookUseCase.getLogic(userId = userId, readMode = readMode, bookId = bookId)
            if(configLocal != null && logicLocal != null){
                config = configLocal
                logic = logicLocal
                val readData = readBookUseCase.getReadData(userId = userId, config = config, initBook = initBook)
                movePageFromId(readData.savePage, isStartPage = true)
            } else {
                cancel(message = "[$bookId Book] config is ${if(configLocal == null) "" else "not"} null, " +
                    "logic is ${if(logicLocal == null) "" else "not"} null")
            }
        }
    }

    fun onClickChoiceItem(choice: ChoiceItem){
        launchWithLoading {
            readBookUseCase.visitReadElement(userId = userId, bookId = bookId, elementId = choice.choiceId)
            val nextPageId = readBookUseCase.decideRoute(userId = userId, bookId = bookId, choice = choice)
            movePageFromId(nextPageId)
        }
    }

    private suspend fun movePageFromId(pageId : Long, isStartPage : Boolean = false){
        delay(300)
        val page = readBookUseCase.getPage(userId = userId, readMode = readMode, bookId = bookId, pageId = pageId, logic = logic)
        if(page != null){
            this@ReadPageViewModel.page.value = page
            readBookUseCase.visitReadElement(userId = userId, bookId = bookId, elementId = pageId, isStartPage = isStartPage)
        }else{
            cancel(message = "[$bookId Book] page[$pageId] is null")
        }
    }
}