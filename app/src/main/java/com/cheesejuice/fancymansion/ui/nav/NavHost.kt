package com.cheesejuice.fancymansion.ui.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cheesejuice.fancymansion.core.ui.theme.preview.TestScreenSetup
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
            TestScreenSetup()
        }

        composable(route = ReadPageScreen.route){
            ReadPageScreenSetup(
                navController = navController
            )
        }
    }
}