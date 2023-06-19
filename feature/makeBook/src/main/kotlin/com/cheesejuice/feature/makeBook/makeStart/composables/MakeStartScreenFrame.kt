package com.cheesejuice.feature.makeBook.makeStart.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cheesejuice.core.common.resource.StringResource
import com.cheesejuice.core.ui.base.BaseScreen
import com.cheesejuice.core.ui.base.LoadState
import com.cheesejuice.core.ui.base.SIDE_EFFECTS_KEY
import com.cheesejuice.core.ui.theme.colorScheme
import com.cheesejuice.core.ui.theme.typography
import com.cheesejuice.core.ui.R
import com.cheesejuice.core.ui.base.frame.baseTopBarDp
import com.cheesejuice.core.ui.component.ButtonIconFixed
import com.cheesejuice.domain.entity.makebook.book.toEditable
import com.cheesejuice.domain.usecase.makeBook.sample.Sample
import com.cheesejuice.feature.makeBook.makeStart.MakeStartContract
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun MakeStartScreenFrame(
    uiState : MakeStartContract.State,
    loadState : LoadState,
    effectFlow: Flow<MakeStartContract.Effect>?,
    onEventSent : (event : MakeStartContract.Event) -> Unit,
    onNavigationRequested: (MakeStartContract.Effect.Navigation) -> Unit
) {
    LaunchedEffect(SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                is MakeStartContract.Effect.Navigation.NavigateReadStart -> {
                    onNavigationRequested(effect)
                }

                else -> {}
            }
        }?.collect()
    }

    BaseScreen(
        loadState = loadState,
        isOverlayTopBar = true,
        idNavigationIcon = R.drawable.ic_chevron_left_36px,
        actions = {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End
            ) {
                ButtonIconFixed(
                    modifier = Modifier.fillMaxHeight(),
                    idIcon = R.drawable.ic_preview_play_24px,
                    onClick = {
                        onEventSent(MakeStartContract.Event.NavigateReadStartClicked)
                    }
                )
            }
        },
        onClickNavigation = {}
    ) {
        uiState.run {
            if (config != null) {
                MakeStartScreenContent(
                    coverImage = coverImage,
                    description = config.description,
                    title = config.title,
                    writer = config.writer,
                    illustrator = config.illustrator,
                    email = config.email,
                    date = config.updateTime,
                    onEventSent = onEventSent
                )
            }else{
                MakeStartScreenEmpty(message = emptyMessage ?: StringResource.empty_message_no_data)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun MakeStartScreenPreview() {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ) {
        MakeStartScreenFrame(
            loadState = LoadState.Idle,
            uiState = MakeStartContract.State(
                config = Sample.book.config.toEditable(),
                coverImage = null,
                emptyMessage = null
            ),
            effectFlow = null,
            onEventSent = {},
            onNavigationRequested = {}
        )
    }
}