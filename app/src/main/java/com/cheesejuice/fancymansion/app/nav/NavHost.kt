package com.cheesejuice.fancymansion.app.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cheesejuice.core.ui.theme.preview.TestScreenPreview
import com.cheesejuice.fancymansion.feature.readbook.readpage.ReadPageScreenSetup

@Composable
fun FancyMansionNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = ReadPageScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = TestScreen.route){
            TestScreenPreview()
        }

        composable(route = ReadPageScreen.route){
            ReadPageScreenSetup(
                navController = navController
            )
        }
    }
}