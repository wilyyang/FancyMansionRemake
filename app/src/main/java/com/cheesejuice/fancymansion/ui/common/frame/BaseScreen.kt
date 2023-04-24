package com.cheesejuice.fancymansion.ui.common.frame

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.DrawerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.module.throwable.ThrowableManager
import com.cheesejuice.fancymansion.ui.common.LoadingState
import com.cheesejuice.fancymansion.ui.common.component.TopBar
import com.cheesejuice.fancymansion.ui.common.dialog.ErrorDialog
import com.cheesejuice.fancymansion.ui.common.dialog.Loading

@Composable
fun BaseScreen(
    modifier : Modifier = Modifier,
    containerColor : Color? = null,

    // top bar
    title : String? = null,
    idNavigationIcon : Int = R.drawable.menu_24px,
    onClickNavigation : (() -> Unit)? = null,
    actions : @Composable (RowScope.() -> Unit)? = null,

    // drawer
    drawerState : DrawerState,
    drawerContent : @Composable () -> Unit,

    // ui state
    loadingState : LoadingState? = null,

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
                onClickNavigation = onClickNavigation,
                actions = actions,

                content = content
            )
        })

    CommonStateProcess(loadingState)
}

@Composable
fun BaseScreen(
    modifier : Modifier = Modifier,
    containerColor : Color? = null,

    // top bar
    title : String? = null,
    idNavigationIcon : Int? = null,
    onClickNavigation : (() -> Unit)? = null,
    actions : @Composable (RowScope.() -> Unit)? = null,

    // ui state
    loadingState : LoadingState? = null,

    content : @Composable (paddingValues : PaddingValues) -> Unit
) {
    BaseContent(
        modifier = modifier,
        containerColor = containerColor,

        title = title,
        idNavigationIcon = idNavigationIcon,
        onClickNavigation = onClickNavigation,
        actions = actions,

        content = content
    )

    CommonStateProcess(loadingState)
}

@Composable
fun CommonStateProcess(
    loadingState : LoadingState? = null
) {
    if(loadingState != null){
        Loading(
            loadingMessage = loadingState.message,
            onDismiss = loadingState.onDismiss
        )
    }

    val errorState by ThrowableManager.errorState.collectAsState()
    errorState?.let {
        ErrorDialog(
            title = it.title,
            errorMessage = it.message,
            onConfirm = it.onConfirm,
            onDismiss = it.onDismiss
        )
    }
}

@Composable
fun BaseContent(
    modifier : Modifier = Modifier,
    containerColor : Color? = null,

    // top bar
    title : String? = null,
    idNavigationIcon : Int? = null,
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
                onClickNavigation = onClickNavigation,
                actions = actions
            )
        },
        content = { content(it) }
    )
}