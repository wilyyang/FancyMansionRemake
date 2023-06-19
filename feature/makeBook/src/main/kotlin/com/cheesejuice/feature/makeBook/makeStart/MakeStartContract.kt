package com.cheesejuice.feature.makeBook.makeStart

import com.cheesejuice.core.ui.base.ViewEvent
import com.cheesejuice.core.ui.base.ViewSideEffect
import com.cheesejuice.core.ui.base.ViewState
import java.io.File

class MakeStartContract {
    data class State(
        val coverImage : File?,
        val emptyMessage : String?
    ) : ViewState

    sealed class Event : ViewEvent {

    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {

        }
    }
}

object Navigation {
    object Routes {
        const val MAKE_START = "make_start"
    }
}