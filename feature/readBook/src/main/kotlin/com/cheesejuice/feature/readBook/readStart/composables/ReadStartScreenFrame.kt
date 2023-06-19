package com.cheesejuice.feature.readBook.readStart.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.cheesejuice.core.common.resource.StringResource
import com.cheesejuice.core.ui.base.BaseScreen
import com.cheesejuice.core.ui.base.LoadState
import com.cheesejuice.core.ui.base.SIDE_EFFECTS_KEY
import com.cheesejuice.core.ui.theme.colorScheme
import com.cheesejuice.core.ui.theme.typography
import com.cheesejuice.domain.usecase.makeBook.sample.Sample
import com.cheesejuice.core.ui.R
import com.cheesejuice.feature.readBook.readStart.ReadStartContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun ReadStartScreenFrame(
    uiState : ReadStartContract.State,
    loadState : LoadState,
    effectFlow: Flow<ReadStartContract.Effect>?,
    onEventSent : (event : ReadStartContract.Event) -> Unit,
    onNavigationRequested: (ReadStartContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is ReadStartContract.Effect.Navigation.ReadStart -> {
                    onNavigationRequested(effect)
                }
            }
        }?.collect()
    }

    BaseScreen(
        loadState = loadState,
        isOverlayTopBar = true,
        idNavigationIcon = R.drawable.ic_chevron_left_36px,
        onClickNavigation = {}
    ) {
        uiState.run {
            if (config != null) {
                ReadStartScreenContent(
                    coverImage = coverImage,
                    description = config.description,
                    title = config.title,
                    writer = config.writer,
                    illustrator = config.illustrator,
                    email = config.email,
                    date = config.updateTime,
                    savePageId = uiState.savePageId,
                    savePageTitle = uiState.savePageTitle,
                    savePageImage = uiState.savePageImage,
                    onEventSent = onEventSent
                )
            }else{
                ReadStartScreenEmpty(message = emptyMessage ?: StringResource.empty_message_no_data)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ReadStartScreenPreview() {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ) {
        ReadStartScreenFrame(
            loadState = LoadState.Idle,
            uiState = ReadStartContract.State(
                config = Sample.book.config,
                coverImage = null,
                savePageId = Sample.book.pageContents[2].pageId,
                savePageTitle = Sample.book.pageContents[2].pageTitle,
                savePageImage = null,
                emptyMessage = null
            ),
            effectFlow = null,
            onEventSent = {},
            onNavigationRequested = {}
        )
    }
}