package com.cheesejuice.core.ui.base

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.cheesejuice.core.ui.R
import com.cheesejuice.core.ui.base.frame.TopBar
import com.cheesejuice.core.ui.dialog.ErrorDialog
import com.cheesejuice.core.ui.dialog.Loading

@Composable
fun BaseScreen(
    modifier : Modifier = Modifier,
    containerColor : Color? = null,

    // top bar
    title : String? = null,
    idNavigationIcon : Int = R.drawable.ic_menu_24px,
    topBarColor : Color? = null,
    onClickNavigation : (() -> Unit)? = null,
    actions : @Composable (RowScope.() -> Unit)? = null,

    // drawer
    drawerState : DrawerState,
    drawerContent : @Composable () -> Unit,

    // ui state
    loadState : LoadState,

    content : @Composable (paddingValues : PaddingValues) -> Unit
) {
    ModalNavigationDrawer(
        // drawer
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape,
                content = {
                    drawerContent()
                }
            )
        },

        content = {
            BaseContent(
                modifier = modifier,
                containerColor = containerColor,

                title = title,
                idNavigationIcon = idNavigationIcon,
                topBarColor = topBarColor,
                onClickNavigation = onClickNavigation,
                actions = actions,

                content = content
            )
        })

    CommonStateProcess(loadState)
}

@Composable
fun BaseScreen(
    modifier : Modifier = Modifier,
    containerColor : Color? = null,

    // top bar
    title : String? = null,
    idNavigationIcon : Int? = null,
    topBarColor : Color? = null,
    onClickNavigation : (() -> Unit)? = null,
    actions : @Composable (RowScope.() -> Unit)? = null,

    // ui state
    loadState : LoadState,

    content : @Composable (paddingValues : PaddingValues) -> Unit
) {
    BaseContent(
        modifier = modifier,
        containerColor = containerColor,

        title = title,
        idNavigationIcon = idNavigationIcon,
        topBarColor = topBarColor,
        onClickNavigation = onClickNavigation,
        actions = actions,

        content = content
    )

    CommonStateProcess(loadState)
}

@Composable
fun CommonStateProcess(
    loadState : LoadState
) {
    when(loadState){
        is LoadState.Loading -> {
            Loading(
                loadingMessage = loadState.message
            )
        }
        is LoadState.ErrorDialog -> {
            ErrorDialog(
                title = loadState.title,
                errorMessage = loadState.message,
                onConfirm = loadState.onConfirm,
                onDismiss = loadState.onDismiss
            )
        }
        else -> {}
    }
}

@Composable
fun BaseContent(
    modifier : Modifier = Modifier,
    containerColor : Color? = null,

    // top bar
    title : String? = null,
    idNavigationIcon : Int? = null,
    topBarColor : Color? = null,
    onClickNavigation : (() -> Unit)? = null,
    actions : @Composable (RowScope.() -> Unit)? = null,

    content : @Composable (paddingValues : PaddingValues) -> Unit
) {
    Scaffold(
        modifier = modifier,
        containerColor = containerColor ?: MaterialTheme.colorScheme.background,

        // top bar
        topBar = {
            TopBar(
                title = title,
                idNavigationIcon = idNavigationIcon,
                topBarColor = topBarColor,
                onClickNavigation = onClickNavigation,
                actions = actions
            )
        },
        content = { content(it) }
    )
}