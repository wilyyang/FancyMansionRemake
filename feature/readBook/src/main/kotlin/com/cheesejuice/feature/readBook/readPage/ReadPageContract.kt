package com.cheesejuice.feature.readBook.readPage

import com.cheesejuice.core.ui.base.ViewEvent
import com.cheesejuice.core.ui.base.ViewSideEffect
import com.cheesejuice.core.ui.base.ViewState
import com.cheesejuice.domain.entity.readbook.book.ChoiceItemEntity
import com.cheesejuice.domain.entity.readbook.book.PageEntity

class ReadPageContract {
    data class State(
        val page : PageEntity?
    ) : ViewState

    sealed class Event : ViewEvent {
        data class ChoiceItemClicked(val choice : ChoiceItemEntity) : Event()
        object BackButtonClicked : Event()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            object Back : Navigation()
        }
    }
}