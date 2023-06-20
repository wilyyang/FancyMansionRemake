package com.cheesejuice.feature.makeBook.makeStart

import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.core.ui.base.ViewEvent
import com.cheesejuice.core.ui.base.ViewSideEffect
import com.cheesejuice.core.ui.base.ViewState
import com.cheesejuice.domain.entity.makebook.book.EditableConfigEntity
import java.io.File

class MakeStartContract {
    data class State(
        val config : EditableConfigEntity?,
        val coverImage : File?,
        val emptyMessage : String?
    ) : ViewState

    sealed class Event : ViewEvent {
        object ReadStartPreviewClicked : Event()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class NavigateReadStart(val userId : String, val readMode : ReadMode, val bookId : String) : Navigation()
        }
    }
}

object Navigation {
    object Routes {
        const val MAKE_START = "make_start"
    }
}