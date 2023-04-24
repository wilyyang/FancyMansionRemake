package com.cheesejuice.fancymansion.ui.content.read.page

import androidx.compose.runtime.mutableStateOf
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
    private val bookId = SAMPLE_BOOK_ID
    private val userId = "local"
    private lateinit var config : Config
    private lateinit var logic : Logic

    val page = mutableStateOf(InitData.page)

    init {
        launchWithLoading{
            val configLocal = readBookUseCase.getConfig(userId = userId, bookId = bookId)
            val logicLocal = readBookUseCase.getLogic(userId = userId, bookId = bookId)
            if(configLocal != null && logicLocal != null){
                config = configLocal
                logic = logicLocal
                val startPageId = readBookUseCase.initReadData(userId = userId, config = config)
                nextPageFromId(startPageId)
            } else {
                cancel(message = "[$bookId Book] config is ${if(configLocal == null) "" else "not"} null, " +
                    "logic is ${if(logicLocal == null) "" else "not"} null")
            }
        }
    }

    fun onClickChoiceItem(choice: ChoiceItem){
        launchWithLoading {
            val nextPageId = choice.routes[0].routePageId
            nextPageFromId(nextPageId)
        }
    }

    private suspend fun nextPageFromId(pageId : Long){
        delay(300)
        val page = readBookUseCase.getPage(userId = "local", bookId = bookId, pageId = pageId, logic = logic)
        if(page != null){
            this@ReadPageViewModel.page.value = page
        }else{
            cancel(message = "[$bookId Book] page[$pageId] is null")
        }
    }
}