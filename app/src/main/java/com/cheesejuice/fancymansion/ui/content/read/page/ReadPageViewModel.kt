package com.cheesejuice.fancymansion.ui.content.read.page

import androidx.lifecycle.viewModelScope
import com.cheesejuice.fancymansion.ID_NOT_FOUND
import com.cheesejuice.fancymansion.SAMPLE_BOOK_ID
import com.cheesejuice.fancymansion.data.model.ChoiceItem
import com.cheesejuice.fancymansion.data.model.Config
import com.cheesejuice.fancymansion.data.model.Logic
import com.cheesejuice.fancymansion.data.model.Page
import com.cheesejuice.fancymansion.data.model.PageContent
import com.cheesejuice.fancymansion.data.model.PageLogic
import com.cheesejuice.fancymansion.data.source.local.Sample
import com.cheesejuice.fancymansion.data.source.local.database.model.ReadData
import com.cheesejuice.fancymansion.domain.ReadBookUseCase
import com.cheesejuice.fancymansion.module.throwable.Error
import com.cheesejuice.fancymansion.module.throwable.ThrowableManager
import com.cheesejuice.fancymansion.ui.common.BaseViewModel
import com.cheesejuice.fancymansion.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ReadPageViewModel @Inject constructor(
    private val readBookUseCase : ReadBookUseCase
) : BaseViewModel()
{
    private val bookId = SAMPLE_BOOK_ID
    private lateinit var config : Config
    private lateinit var logic : Logic

    private lateinit var readData : Flow<ReadData?>

    private val currentPageId = MutableStateFlow(ID_NOT_FOUND)


    val page : StateFlow<Page?> = currentPageId.map {
        if(it != ID_NOT_FOUND){
            readBookUseCase.getPage(bookId = bookId, userId = "local", pageId = it,  logic = logic)
        }else{
            null
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        null
    )

    init {
        _uiState.value = UiState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            val configLocal = readBookUseCase.getConfig(bookId, userId = "local")
            val logicLocal = readBookUseCase.getLogic(bookId, userId = "local")

            configLocal?.let {
                config = configLocal
                readData = readBookUseCase.getReadDataFlow(config)
                currentPageId.value = readData.first()!!.savePage
            }

            logicLocal?.let {
                logic = logicLocal
            }

            withContext(Dispatchers.Main) {
                if(configLocal == null || logicLocal == null){
                    _uiState.value = UiState.Empty
                    ThrowableManager.sendError(
                        error = Error.NOT_FOUND_BOOK,
                        message = "[bookId = $bookId] book's " +
                            if (configLocal == null) "config " else "" +
                                if (logicLocal == null) "logic " else "" + "not found"
                    )
                } else {
                    _uiState.value = UiState.Loaded()
                }
            }
        }
    }

    fun clickChoiceItem(choice: ChoiceItem){

        currentPageId.value = choice.routes[0].routePageId
    }
}