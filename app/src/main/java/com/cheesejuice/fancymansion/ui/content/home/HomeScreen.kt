package com.cheesejuice.fancymansion.ui.content.home

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cheesejuice.fancymansion.ui.common.UiState
import com.cheesejuice.fancymansion.ui.common.component.MainDrawer
import com.cheesejuice.fancymansion.ui.common.component.MenuType
import com.cheesejuice.fancymansion.ui.common.frame.BaseScreen
import com.cheesejuice.fancymansion.ui.theme.colorScheme
import com.cheesejuice.fancymansion.ui.theme.typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreenSetup(
    navController : NavController,
    viewModel : HomeViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()
    HomeScreenFrame(
        scope = scope,
        uiState = uiState
    )
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    val scope = rememberCoroutineScope()
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ){
        HomeScreenFrame(
            scope = scope
        )
    }
}

@Composable
fun HomeScreenFrame(
    scope : CoroutineScope,
    uiState: UiState = UiState.Loaded(null),
    isGoogleValid : Boolean = true,
    onClickGoogleAuthList : () -> Unit = {},
    onClickStartLocal : () -> Unit = {}
){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    BaseScreen(
        title = "기본 화면 보여주기",
        onClickNavigation = {
            scope.launch {
                drawerState.open()
            }
        },

        drawerState = drawerState,
        drawerContent = {
            val menuList = listOf(
                MenuType(key = "1", title = "토스트 에러",   onClick = {}),
                MenuType(key = "2", title = "드로어 닫기",   onClick = { data ->
                    scope.launch {
                        drawerState.close()
                    }
                }),
                MenuType(key = "3", title = "대화상자 에러", onClick = {}),
            )
            MainDrawer(menuItems = menuList)
        },
        uiState = uiState,
    ) {
        HomeScreenContent(
            isGoogleValid = isGoogleValid,
            onClickGoogleAuthList = onClickGoogleAuthList,
            onClickStartLocal = onClickStartLocal
        )
    }
}

@Composable
fun HomeScreenContent(
    isGoogleValid : Boolean = true,
    onClickGoogleAuthList : () -> Unit = {},
    onClickStartLocal : () -> Unit = {}
) {

}