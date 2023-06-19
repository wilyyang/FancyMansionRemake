package com.cheesejuice.feature.makeBook.makeStart.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import com.cheesejuice.core.ui.base.BaseScreen
import com.cheesejuice.core.ui.base.LoadState
import com.cheesejuice.core.ui.base.SIDE_EFFECTS_KEY
import com.cheesejuice.core.ui.theme.colorScheme
import com.cheesejuice.core.ui.theme.typography
import com.cheesejuice.core.ui.R
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
                else -> {}
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
                coverImage = null,
                emptyMessage = null
            ),
            effectFlow = null,
            onEventSent = {},
            onNavigationRequested = {}
        )
    }
}