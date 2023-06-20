package com.cheesejuice.feature.readBook.readStart

import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.core.ui.base.ViewEvent
import com.cheesejuice.core.ui.base.ViewSideEffect
import com.cheesejuice.core.ui.base.ViewState
import com.cheesejuice.domain.entity.readbook.book.ConfigEntity
import com.cheesejuice.feature.readBook.readPage.ReadPageContract
import java.io.File

class ReadStartContract {
    data class State(
        val config : ConfigEntity?,
        val coverImage : File?,
        val savePageId : Long,
        val savePageTitle : String?,
        val savePageImage : File?,
        val emptyMessage : String?
    ) : ViewState

    sealed class Event : ViewEvent {
        object ReadPageFirstClicked : Event()
        object ReadPageSavePointClicked : Event()
        object BackButtonClicked : Event()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class NavigateReadPage(val userId : String, val readMode : ReadMode, val bookId : String, val pageId : Long) : Navigation()
            object Back : Navigation()
        }
    }
}

object Navigation {
    object Routes {
        const val READ_START = "read_start"
    }
}