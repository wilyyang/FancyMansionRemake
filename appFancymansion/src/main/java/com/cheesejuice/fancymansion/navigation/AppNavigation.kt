package com.cheesejuice.fancymansion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cheesejuice.core.ui.theme.preview.TestScreenPreview
import com.cheesejuice.fancymansion.navigation.Navigation.Routes.READ_PAGE
import com.cheesejuice.fancymansion.navigation.Navigation.Routes.THEME_PREVIEW

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = READ_PAGE
    ) {
        composable(route = THEME_PREVIEW){
            TestScreenPreview()
        }

        composable(route = READ_PAGE){
            ReadPageScreenDestination(navController = navController)
        }
    }
}

object Navigation {
    object Args {
        const val USER_ID = "user_id"
    }

    object Routes {
        const val THEME_PREVIEW = "theme_preview"
        const val READ_PAGE = "read_page"
    }
}