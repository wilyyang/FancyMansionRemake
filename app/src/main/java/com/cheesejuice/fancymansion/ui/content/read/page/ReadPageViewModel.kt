package com.cheesejuice.fancymansion.ui.content.read.page

import androidx.lifecycle.viewModelScope
import com.cheesejuice.fancymansion.data.model.ChoiceItem
import com.cheesejuice.fancymansion.data.model.Page
import com.cheesejuice.fancymansion.data.model.PageLogic
import com.cheesejuice.fancymansion.data.repository.ReadBookRepository
import com.cheesejuice.fancymansion.data.source.local.Sample
import com.cheesejuice.fancymansion.ui.common.BaseViewModel
import com.cheesejuice.fancymansion.ui.common.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ReadPageViewModel @Inject constructor(
    private val readBookRepository : ReadBookRepository
) : BaseViewModel()
{
    private val bookId = Sample.book.config.bookId
    private val index = MutableStateFlow(0)

    private val pages = Sample.book.pages
    private val logics = Sample.book.logic.logics

    val page : StateFlow<Page> = index.map { pages[it] }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        pages[index.value]
    )

    val pageLogic : StateFlow<PageLogic> = index.map { logics[it] }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        logics[index.value]
    )

    init {
        _uiState.value = UiState.Loading()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                readBookRepository.insertReadData(bookId)
            }

        }
    }

    fun clickChoiceItem(choice: ChoiceItem){


    }
}