package com.cheesejuice.feature.readBook.readPage

import com.cheesejuice.core.ui.base.ViewEvent
import com.cheesejuice.core.ui.base.ViewSideEffect
import com.cheesejuice.core.ui.base.ViewState
import com.cheesejuice.domain.entity.readbook.book.ChoiceItemEntity
import com.cheesejuice.domain.entity.readbook.book.PageEntity

class ReadPageContract {
    data class State(
        val page : PageEntity?,
        val emptyMessage : String?
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

object Navigation {
    object Args {
        const val USER_ID = "user_id"
        const val READ_MODE = "read_mode"
        const val BOOK_ID = "book_id"
        const val PAGE_ID = "page_id"
    }

    object Routes {
        const val READ_PAGE = "read_page"
    }
}