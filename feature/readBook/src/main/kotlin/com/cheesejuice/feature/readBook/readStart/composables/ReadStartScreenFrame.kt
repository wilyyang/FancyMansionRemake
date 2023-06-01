package com.cheesejuice.feature.readBook.readStart.composables

import androidx.compose.runtime.Composable
import com.cheesejuice.core.ui.base.LoadState
import com.cheesejuice.domain.usecase.makeBook.sample.Sample
import com.cheesejuice.feature.readBook.readStart.ReadStartContract
import kotlinx.coroutines.flow.Flow

@Composable
fun ReadStartScreenFrame(
    uiState : ReadStartContract.State,
    loadState : LoadState,
    effectFlow: Flow<ReadStartContract.Effect>?,
    onEventSent : (event : ReadStartContract.Event) -> Unit,
    onNavigationRequested: (ReadStartContract.Effect.Navigation) -> Unit
) {
    ReadStartScreenContent(
        coverImage = null,
        description = Sample.book.config.description,
        title = Sample.book.config.title,
        writer = Sample.book.config.writer,
        email = Sample.book.config.email,
        date = Sample.book.config.updateTime,
        currentPage = 0,
        currentPageTitle = "dfasgerag",
        onEventSent = {}
    )
}