package com.cheesejuice.feature.readBook.readStart

import com.cheesejuice.core.ui.base.ViewEvent
import com.cheesejuice.core.ui.base.ViewSideEffect
import com.cheesejuice.core.ui.base.ViewState
import com.cheesejuice.domain.entity.readbook.book.ConfigEntity

class ReadStartContract {
    data class State(
        val config : ConfigEntity?
    ) : ViewState

    sealed class Event : ViewEvent {
        data class ReadStartClicked(val config : ConfigEntity) : Event()
        object BackButtonClicked : Event()
    }

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ReadStart(val config : ConfigEntity) : Navigation()
            object Back : Navigation()
        }
    }
}