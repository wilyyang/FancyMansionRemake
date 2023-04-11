package com.cheesejuice.fancymansion.ui.common.frame

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.ui.common.ErrorType
import com.cheesejuice.fancymansion.ui.common.UiState
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
    uiState : UiState = UiState.Loaded(),

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

    CommonStateProcess(uiState)
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
    uiState : UiState = UiState.Loaded(),

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

    CommonStateProcess(uiState)
}

@Composable
fun CommonStateProcess(uiState : UiState) = when (uiState) {
    is UiState.Loading -> {
        Loading(
            loadingMessage = uiState.message,
            onDismiss = uiState.onDismiss
        )
    }

    is UiState.Error -> {
        when(uiState.errorData.errorType){
            ErrorType.Dialog -> {
                ErrorDialog(
                    title = uiState.errorData.title,
                    errorMessage = uiState.errorData.message,
                    onConfirm = uiState.onConfirm,
                    onDismiss = uiState.onDismiss
                )
            }

            ErrorType.Toast -> {
                Toast.makeText(
                    LocalContext.current,
                    "${uiState.errorData.title} : ${uiState.errorData.message}",
                    Toast.LENGTH_SHORT
                ).show()
                uiState.onDismiss()
            }

            else -> {}
        }
    }
    else -> {}
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