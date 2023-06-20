package com.cheesejuice.fancymansion.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cheesejuice.feature.makeBook.makeStart.MakeStartContract
import com.cheesejuice.feature.makeBook.makeStart.MakeStartViewModel
import com.cheesejuice.feature.makeBook.makeStart.composables.MakeStartScreenFrame
import com.cheesejuice.feature.readBook.readPage.Navigation
import com.cheesejuice.feature.readBook.readStart.Navigation.Routes.READ_START
import com.cheesejuice.feature.readBook.readStart.ReadStartContract

@Composable
fun MakeStartScreenDestination(navController: NavController) {
    val viewModel : MakeStartViewModel = hiltViewModel()
    MakeStartScreenFrame(
        uiState = viewModel.uiState.value,
        loadState = viewModel.loadState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { effect ->
            when (effect) {
                is MakeStartContract.Effect.Navigation.NavigateReadStart -> {
                    navController.navigateReadStartScreen(
                        userId = effect.userId,
                        readMode = effect.readMode.name,
                        bookId = effect.bookId
                    )
                }

                else -> {}
            }
        }
    )
}