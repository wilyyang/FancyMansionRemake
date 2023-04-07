package com.cheesejuice.fancymansion.ui.common.frame

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.ui.common.component.TopBar
import com.cheesejuice.fancymansion.ui.theme.FancyMansionTheme

@Composable
fun BaseScreenTest(
    modifier : Modifier = Modifier,
    containerColor : Color? = null,

    // top bar
    title : String? = null,
    idNavigationIcon : Int? = null,
    onClickNavigation : (() -> Unit)? = null,
    actions : @Composable (RowScope.() -> Unit)? = null,

    isSkim : Boolean = false,
    skimScreen : @Composable () -> Unit = {},

    content : @Composable (paddingValues : PaddingValues) -> Unit
) {
    FancyMansionTheme {
        Box(
            modifier.fillMaxSize()
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
            if (isSkim) {
                Surface(
                    modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.scrim
                ) {}

                Box {
                    skimScreen()
                }
            }
        }
    }
}

@Composable
fun BaseScreenTest(
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

    isSkim : Boolean = false,
    skimScreen : @Composable () -> Unit = {},

    content : @Composable (paddingValues : PaddingValues) -> Unit
) {
    FancyMansionTheme {
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
                Box(
                    modifier.fillMaxSize()
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

                    if (isSkim) {
                        Surface(
                            modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.scrim
                        ) {}

                        Box {
                            skimScreen()
                        }
                    }
                }
            }
        )
    }
}

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

    content : @Composable (paddingValues : PaddingValues) -> Unit
) {
    FancyMansionTheme {
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
    }
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

    content : @Composable (paddingValues : PaddingValues) -> Unit
) {
    FancyMansionTheme {
        BaseContent(
            modifier = modifier,
            containerColor = containerColor,

            title = title,
            idNavigationIcon = idNavigationIcon,
            onClickNavigation = onClickNavigation,
            actions = actions,

            content = content
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