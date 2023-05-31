package com.cheesejuice.feature.readBook.readPage.composables

import androidx.activity.compose.BackHandler
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.cheesejuice.core.common.PageType
import com.cheesejuice.core.common.resource.StringResource
import com.cheesejuice.core.ui.base.BaseScreen
import com.cheesejuice.core.ui.base.LoadState
import com.cheesejuice.core.ui.base.SIDE_EFFECTS_KEY
import com.cheesejuice.core.ui.theme.colorScheme
import com.cheesejuice.core.ui.theme.typography
import com.cheesejuice.domain.entity.readbook.book.PageEntity
import com.cheesejuice.domain.usecase.makeBook.sample.Sample
import com.cheesejuice.feature.readBook.readPage.ReadPageContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import java.io.File

@Composable
fun ReadPageScreenFrame(
    uiState : ReadPageContract.State,
    loadState : LoadState,
    effectFlow: Flow<ReadPageContract.Effect>?,
    onEventSent : (event : ReadPageContract.Event) -> Unit,
    onNavigationRequested: (ReadPageContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                ReadPageContract.Effect.Navigation.Back -> {
                    onNavigationRequested(ReadPageContract.Effect.Navigation.Back)
                }
            }
        }?.collect()
    }

    BackHandler(onBack = {
        onEventSent(ReadPageContract.Event.BackButtonClicked)
    })

    BaseScreen(
        loadState = loadState
    ) {
        uiState.run {
            if (page != null) {
                ReadPageScreenContent(
                    pageImage = page.image,
                    pageType = PageType.type(page.logic.type),
                    title = page.content.pageTitle,
                    contentText = page.content.description,
                    question = page.content.question,
                    choiceList = page.logic.choiceItems.toList(),
                    onEventSent = onEventSent
                )
            } else {
                ReadPageScreenEmpty(message = StringResource.empty_message_no_page)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ReadPageScreenPreview() {
    val samplePageIdx = 0
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ) {
        ReadPageScreenFrame(
            loadState = LoadState.Idle,
            uiState = ReadPageContract.State(
                page = PageEntity(
                    content = Sample.book.pageContents[samplePageIdx],
                    logic = Sample.book.logic.logics[samplePageIdx],
                    image = File("")
                )
            ),
            effectFlow = null,
            onEventSent = {},
            onNavigationRequested = {}
        )
    }
}