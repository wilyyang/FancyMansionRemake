package com.cheesejuice.fancymansion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.cheesejuice.core.ui.theme.preview.THEME_PREVIEW_ROUTE
import com.cheesejuice.core.ui.theme.preview.ThemePreviewMaterialColor
import com.cheesejuice.core.ui.theme.preview.ThemePreviewScreenFrame
import com.cheesejuice.feature.readBook.readPage.Navigation.Args.BOOK_ID
import com.cheesejuice.feature.readBook.readPage.Navigation.Args.PAGE_ID
import com.cheesejuice.feature.readBook.readPage.Navigation.Args.READ_MODE
import com.cheesejuice.feature.readBook.readPage.Navigation.Args.USER_ID
import com.cheesejuice.feature.readBook.readPage.Navigation.Routes.READ_PAGE
import com.cheesejuice.feature.readBook.readStart.Navigation.Routes.READ_START

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = READ_START
    ) {

        composable(route = THEME_PREVIEW_ROUTE) {
            ThemePreviewScreenFrame()
        }

        composable(route = READ_START) {
            ReadStartScreenDestination(navController = navController)
        }

        composable(
            route = "$READ_PAGE/{$USER_ID}/{$READ_MODE}/{$BOOK_ID}/{$PAGE_ID}",
            arguments = listOf(
                navArgument(USER_ID) { type = NavType.StringType },
                navArgument(READ_MODE) { type = NavType.StringType },
                navArgument(BOOK_ID) { type = NavType.StringType },
                navArgument(PAGE_ID) { type = NavType.LongType }
            )
        ) {
            ReadPageScreenDestination(navController = navController)
        }
    }
}

fun NavController.navigateReadPageScreen(
    userId : String,
    readMode : String,
    bookId : String,
    pageId : Long
) {
    navigate("${READ_PAGE}/$userId/$readMode/$bookId/$pageId")
}