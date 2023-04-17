package com.cheesejuice.fancymansion.ui.nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cheesejuice.fancymansion.ui.common.screen.test.TestScreenSetup
import com.cheesejuice.fancymansion.ui.content.home.HomeScreenSetup
import com.cheesejuice.fancymansion.ui.content.read.page.ReadPageScreenSetup
import com.cheesejuice.fancymansion.ui.content.user.login.LoginScreenSetup

@Composable
fun FancyMansionNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = LoginScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(route = TestScreen.route){
            TestScreenSetup()
        }

        composable(route = LoginScreen.route){
            LoginScreenSetup(
                navController = navController
            )
        }

        composable(route = HomeScreen.route){
            HomeScreenSetup(
                navController = navController
            )
        }

        composable(route = ReadPageScreen.route){
            ReadPageScreenSetup(
                navController = navController
            )
        }
    }
}