package com.cheesejuice.fancymansion.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cheesejuice.core.ui.theme.preview.TestScreenFrame
import com.cheesejuice.feature.readBook.readStart.ReadStartContract
import com.cheesejuice.feature.readBook.readStart.ReadStartViewModel
import com.cheesejuice.feature.readBook.readStart.composables.ReadStartScreenFrame

@Composable
fun ReadStartScreenDestination(navController: NavController) {
    TestScreenFrame()
/*
    val viewModel : ReadStartViewModel = hiltViewModel()
    ReadStartScreenFrame(
        uiState = viewModel.uiState.value,
        loadState = viewModel.loadState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { effect ->
            when (effect) {
                is ReadStartContract.Effect.Navigation.ReadStart -> {
                    navController.navigateReadPageScreen(
                        userId = effect.userId,
                        readMode = effect.readMode.name,
                        bookId = effect.bookId,
                        pageId = effect.pageId
                    )
                }
            }
        }
    )*/
}