package com.cheesejuice.fancymansion.ui.common.frame

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.cheesejuice.fancymansion.ui.common.component.MainDrawer
import com.cheesejuice.fancymansion.ui.common.component.TopBar
import com.cheesejuice.fancymansion.ui.theme.FancyMansionTheme
import kotlinx.coroutines.CoroutineScope

@Composable
fun BaseScreen(
    containerColor : Color? = null,

    title : String? = null,
    idNavigationIcon : Int? = null,
    onClickNavigation : (() -> Unit)? = null,
    actions : @Composable (RowScope.() -> Unit)? = null,

    content : @Composable (snackBarState : SnackbarHostState, paddingValues : PaddingValues) -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }
    FancyMansionTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = containerColor ?: MaterialTheme.colorScheme.background,
            snackbarHost = { SnackbarHost(snackBarHostState) },
            topBar = {
                TopBar(
                    title = title,
                    idNavigationIcon = idNavigationIcon,
                    onClickNavigation = onClickNavigation,
                    actions = actions
                )
            },
            content = { content(snackBarHostState, it) }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreenBottomSheet(
    modifier : Modifier = Modifier,
    containerColor : Color? = null,
    scope : CoroutineScope = rememberCoroutineScope(),

    title : String? = null,
    idNavigationIcon : Int? = null,
    onClickNavigation : (() -> Unit)? = null,
    actions : @Composable (RowScope.() -> Unit)? = null,

    state : BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    bottomSheetPeek : Dp = 0.dp,
    bottomSheetContent : @Composable ColumnScope.() -> Unit,

    content : @Composable (scaffoldState : BottomSheetScaffoldState, scope : CoroutineScope) -> Unit
) {
    FancyMansionTheme {
        BottomSheetScaffold(
            modifier = modifier,
            containerColor = containerColor ?: MaterialTheme.colorScheme.background,
            scaffoldState = state,

            topBar = {
                TopBar(
                    title = title,
                    idNavigationIcon = idNavigationIcon,
                    onClickNavigation = onClickNavigation,
                    actions = actions
                )
            },
            sheetDragHandle = null,
            sheetSwipeEnabled = false,
            sheetTonalElevation = 0.dp,
            sheetShadowElevation = 100.dp,
            sheetPeekHeight = bottomSheetPeek,
            sheetContent = bottomSheetContent,

            content = { content(state, scope) }
        )
    }
}

// 공부중
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreenDrawer(
    modifier : Modifier = Modifier,
    containerColor : Color? = null,
    scope : CoroutineScope = rememberCoroutineScope(),

    title : String? = null,
    idNavigationIcon : Int? = null,
    onClickNavigation : (() -> Unit)? = null,
    actions : @Composable (RowScope.() -> Unit)? = null,

    state : BottomSheetScaffoldState = rememberBottomSheetScaffoldState(),
    bottomSheetPeek : Dp = 0.dp,
    bottomSheetContent : @Composable ColumnScope.() -> Unit,

    drawerState : DrawerState,
    drawerContent : @Composable (scaffoldState : BottomSheetScaffoldState, scope : CoroutineScope) -> Unit,
    content : @Composable (scaffoldState : BottomSheetScaffoldState, scope : CoroutineScope) -> Unit
) {
    FancyMansionTheme {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet(
                    drawerShape = RectangleShape,
                    content = {
                        drawerContent(state, scope)
                    }
                )
            },
            content = {
                BottomSheetScaffold(
                    modifier = modifier,
                    containerColor = containerColor ?: MaterialTheme.colorScheme.background,
                    scaffoldState = state,

                    topBar = {
                        TopBar(
                            title = title,
                            idNavigationIcon = idNavigationIcon,
                            onClickNavigation = onClickNavigation,
                            actions = actions
                        )
                    },
                    sheetDragHandle = null,
                    sheetSwipeEnabled = false,
                    sheetTonalElevation = 0.dp,
                    sheetShadowElevation = 100.dp,
                    sheetPeekHeight = bottomSheetPeek,
                    sheetContent = bottomSheetContent,

                    content = { content(state, scope) }
                )
            })
    }
}