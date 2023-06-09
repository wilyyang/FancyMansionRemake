package com.cheesejuice.fancymansion.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cheesejuice.feature.readBook.readPage.ReadPageContract
import com.cheesejuice.feature.readBook.readPage.ReadPageViewModel
import com.cheesejuice.feature.readBook.readPage.composables.ReadPageScreenFrame
import com.cheesejuice.feature.readBook.readStart.Navigation.Routes.READ_START
@Composable
fun ReadPageScreenDestination(navController : NavController) {
    val viewModel : ReadPageViewModel = hiltViewModel()
    ReadPageScreenFrame(
        uiState = viewModel.uiState.value,
        loadState = viewModel.loadState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { effect ->
            if (effect is ReadPageContract.Effect.Navigation.Back) {
                navController.navigate(route = READ_START){
                    popUpTo(navController.graph.id)
                }
            }
        },
    )
}