package com.cheesejuice.fancymansion.ui.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cheesejuice.fancymansion.ui.common.screen.test.TestScreenSetup
import com.cheesejuice.fancymansion.ui.content.read.page.ReadPageScreenSetup

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