package com.cheesejuice.fancymansion.core.ui.theme.preview

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.core.ui.base.BaseScreen
import com.cheesejuice.fancymansion.core.ui.base.LoadingState
import com.cheesejuice.fancymansion.core.ui.base.frame.MainDrawer
import com.cheesejuice.fancymansion.core.ui.base.frame.MenuType
import com.cheesejuice.fancymansion.core.ui.component.*
import com.cheesejuice.fancymansion.core.ui.theme.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun TestScreenSetup(
    viewModel : TestViewModel = hiltViewModel()
) {
    val loadingState by viewModel.loadingState.collectAsState()
    TestScreenFrame(
        loadingState = loadingState,
        menu1Click = {},
        menu3Click = {},
        bottomClick = {},
    )
}

@Preview(showSystemUi = true)
@Composable
fun TestScreenPreview(){
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ){
        TestScreenBasicButton()
    }
}

@Composable
fun TestScreenFrame(
    loadingState: LoadingState? = null,
    menu1Click : ()->Unit = {},
    menu2Click : ()->Unit = {},
    menu3Click : ()->Unit = {},
    bottomClick : ()->Unit = {},
){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope : CoroutineScope = rememberCoroutineScope()

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
                MenuType(key = "1", title = "토스트 에러",   onClick = {
                    menu1Click()
                }),
                MenuType(key = "2", title = "드로어 닫기",   onClick = { data ->
                    scope.launch {
                        drawerState.close()
                    }
                }),
                MenuType(key = "3", title = "대화상자 에러", onClick = {
                    menu3Click()
                }),
            )
            MainDrawer(menuItems = menuList)
        },
        loadingState = loadingState
    ) {
        TestScreenContent(
            onClickBottom = {
                bottomClick()
            }
        )
    }
}

@Composable
fun TestScreenContent(
    onClickBottom : () -> Unit = {}
){
    // 배경
    Column {
        Column(modifier = Modifier.weight(0.7f)) {
            val list = listOf(
                Pair("키1", "첫번째 항목"),
                Pair("키2", "두번째 항목"),
                Pair("키3", "세번째 항목"),
                Pair("키4", "네번째 항목"))
            val selected = rememberSaveable { mutableStateOf(list[0]) }
            DropDown(
                modifier = Modifier
                    .padding(top = 1.dp)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp),
                list = list,
                selectedValue = selected.value,
                onClick = { pair -> selected.value = pair },
                isClickable = true
            )

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)) {

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.Top){

                    val focus = remember { mutableStateOf("") }
                    val error: MutableState<String?> = remember { mutableStateOf(null) }
                    TextBox(
                        modifier = Modifier.fillMaxWidth(),
                        value = focus.value,
                        hint = "포커스 이동",
                        onValueChange = {
                            focus.value = it
                            error.value = if(it.isBlank()) { "텍스트가 입력되지 않았습니다." } else null
                        },
                        isBorder = true,
                        error = error.value
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.Top){

                    TextBox(
                        modifier = Modifier.fillMaxWidth(),
                        value = "안쓰는 텍스트",
                        hint = "안쓰는 텍스트",
                        onValueChange = {},
                        isEnabled = false,
                        isBorder = true
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.Top){

                    val labelText = remember { mutableStateOf("") }

                    TextBox(
                        modifier = Modifier.fillMaxWidth(),
                        label = "라벨",
                        value = labelText.value,
                        hint = "안쓰는 텍스트",
                        onValueChange =  {
                            labelText.value = it
                        },
                        isDivider = true
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)){

                    val longText = remember { mutableStateOf("") }
                    TextBox(
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        minLine = 2,
                        maxLine = 2,
                        value = longText.value,
                        hint = "긴 텍스트를 입력하세요",
                        label = "긴텍스트",
                        onValueChange = {
                            longText.value = it
                        },
                        isBorder = true
                    )
                }

                Divider(
                    Modifier
                        .padding(top = 4.dp)
                        .height(1.dp))

                val dropdownList = listOf(
                    DropdownType(key = "1", title = "삭제하기", onClick = { data -> Log.e("Crane", "${data.title} 삭제하기")}),
                    DropdownType(key = "2", title = "추가하기", onClick = { data -> Log.e("Crane", "${data.title} 추가하기")}),
                    DropdownType(key = "3", title = "수정하기", onClick = { data -> Log.e("Crane", "${data.title} 수정하기")}),
                    DropdownType(key = "4", title = "삭제하기", onClick = { data -> Log.e("Crane", "${data.title} 삭제하기")}),
                    DropdownType(key = "5", title = "긴 하루 끝", onClick = { data -> Log.e("Crane", "${data.title} 긴 하루 끝")})
                )

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentPadding = PaddingValues(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    item{
                        BookHolder(
                            modifier = Modifier.padding(vertical = 4.dp),
                            textColor = colorScheme.onSurface,
                            backgroundColor = colorScheme.surface,
                            dropdown = dropdownList
                        )
                        BookHolder(
                            modifier = Modifier.padding(vertical = 4.dp),
                            textColor = colorScheme.onSurfaceVariant,
                            backgroundColor = colorScheme.surfaceVariant,
                            dropdown = dropdownList
                        )
                        BookHolder(
                            modifier = Modifier.padding(vertical = 4.dp),
                            textColor = colorScheme.onSurface.copy(alpha = disableAlpha),
                            backgroundColor = colorScheme.surface.copy(alpha = disableAlpha),
                            dropdown = dropdownList
                        )

                        BookHolder(
                            modifier = Modifier.padding(vertical = 4.dp),
                            textColor = colorScheme.onPrimaryContainer,
                            backgroundColor = colorScheme.primaryContainer,
                            dropdown = dropdownList
                        )
                        BookHolder(
                            modifier = Modifier.padding(vertical = 4.dp),
                            textColor = colorScheme.onSecondaryContainer,
                            backgroundColor = colorScheme.secondaryContainer,
                            dropdown = dropdownList
                        )
                        BookHolder(
                            modifier = Modifier.padding(vertical = 4.dp),
                            textColor = colorScheme.onTertiaryContainer,
                            backgroundColor = colorScheme.tertiaryContainer,
                            dropdown = dropdownList
                        )
                    }
                }
            }
        }
        BasicButton(modifier = Modifier.fillMaxWidth(), text = "기본 버튼 테스트", isClickable = true) {
            onClickBottom()
        }
    }
}
@Composable
fun TestScreenBasicButton(){
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BasicButton(text = "테스트 버튼")
            Spacer(Modifier.height(5.dp))
            BasicButton(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium),
                text = "테스트 버튼")
            Spacer(Modifier.height(5.dp))
            BasicButton(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = MaterialTheme.shapes.medium
                    ),
                text = "테스트 버튼")
            Spacer(Modifier.height(5.dp))
            BasicButton(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = MaterialTheme.shapes.medium
                    ),
                text = "테스트 버튼",
                contentPadding = PaddingValues(10.dp)
            )
            Spacer(Modifier.height(5.dp))
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(MaterialTheme.shapes.medium)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = MaterialTheme.shapes.medium
                    ),
                text = "테스트 버튼",
                contentArrangement = Arrangement.Start
            )
        }
    }
}

@Composable
fun TestScreenIconTest(){
    val listId = listOf(
        Pair(R.drawable.add_photo_48px, null),
        Pair(R.drawable.crop_48px, null),
        Pair(R.drawable.help_40px, null),
        Pair(R.drawable.info_40px, null),
        Pair(R.drawable.menu_24px, null),
        Pair(R.drawable.refresh_24px, null),
        Pair(R.drawable.search_24px, null),
        Pair(R.drawable.settings_24px, null),
        Pair(R.drawable.chevron_left_24px, null),
        Pair(R.drawable.chevron_right_24px, null),
        Pair(R.drawable.favorite_24px, red),
        Pair(R.drawable.star_24px, yellow),
        Pair(R.drawable.bookmark_24px, null),
        Pair(R.drawable.expand_less_20px, null),
        Pair(R.drawable.expand_more_20px, null),
        Pair(R.drawable.more_vertical_20px, null),
        Pair(R.drawable.add_20px, null),
        Pair(R.drawable.close_20px, null),
        Pair(R.drawable.cancel_20px, null),
        Pair(R.drawable.error_20px, red),
        Pair(R.drawable.warning_20px, yellow),
        Pair(R.drawable.pass_20px, green)
    )

    Surface{
        LazyVerticalGrid (
            columns = GridCells.Adaptive(minSize = 128.dp)
        ){
            items(listId) { (id, color) ->
                Icon(
                    painter = painterResource(id = id),
                    contentDescription = null,
                    tint = color?: LocalContentColor.current
                )
            }
        }
    }
}

@Composable
fun TestScreenTypographyTest(){
    Column {
        Text(text = "Headline Large", style = MaterialTheme.typography.headlineLarge)
        Text(text = "Headline Medium", style = MaterialTheme.typography.headlineMedium)
        Text(text = "Headline Small", style = MaterialTheme.typography.headlineSmall)

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 10.dp)) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text("Title Large : ", style = TextStyleGroup.Large.titleStyle)

                val large = remember { mutableStateOf("") }
                TextBox(
                    maxLine = 1,
                    label = "Label Large",
                    value = large.value,
                    hint = "Body Large",
                    onValueChange = {
                        large.value = it
                    },
                    styleGroup = TextStyleGroup.Large
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text("Title Medium : ", style = TextStyleGroup.Medium.titleStyle)

                val large = remember { mutableStateOf("") }
                TextBox(
                    maxLine = 1,
                    label = "Label Medium",
                    value = large.value,
                    hint = "Body Medium",
                    onValueChange = {
                        large.value = it
                    },
                    styleGroup = TextStyleGroup.Medium
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                Text("Title Small : ", style = TextStyleGroup.Small.titleStyle)

                val large = remember { mutableStateOf("") }
                TextBox(
                    maxLine = 1,
                    label = "Label Small",
                    value = large.value,
                    hint = "Body Small",
                    onValueChange = {
                        large.value = it
                    },
                    styleGroup = TextStyleGroup.Small
                )
            }
        }
    }
}

@Composable
fun TestScreenColorTest(){
    Column (Modifier.verticalScroll(
        state = rememberScrollState()
    )){
        BasicButton(
            text = "background",
            backgroundColor = MaterialTheme.colorScheme.background,
            textColor = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(20.dp))
        BasicButton(
            text = "surface",
            backgroundColor = MaterialTheme.colorScheme.surface,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        BasicButton(
            text = "surfaceVariant",
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(20.dp))

        BasicButton(
            text = "outline",
            backgroundColor = MaterialTheme.colorScheme.background,
            textColor = MaterialTheme.colorScheme.outline
        )

        BasicButton(
            text = "outlineVariant",
            backgroundColor = MaterialTheme.colorScheme.background,
            textColor = MaterialTheme.colorScheme.outlineVariant
        )

        Spacer(Modifier.height(20.dp))

        BasicButton(
            text = "primary",
            backgroundColor = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.onPrimary
        )

        BasicButton(
            text = "primaryContainer",
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer
        )

        BasicButton(
            text = "inversePrimary",
            backgroundColor = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.inversePrimary
        )

        Spacer(Modifier.height(20.dp))

        BasicButton(
            text = "secondary",
            backgroundColor = MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.onSecondary
        )

        BasicButton(
            text = "secondaryContainer",
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            textColor = MaterialTheme.colorScheme.onSecondaryContainer
        )

        Spacer(Modifier.height(20.dp))

        BasicButton(
            text = "tertiary",
            backgroundColor = MaterialTheme.colorScheme.tertiary,
            textColor = MaterialTheme.colorScheme.onTertiary
        )

        BasicButton(
            text = "tertiaryContainer",
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            textColor = MaterialTheme.colorScheme.onTertiaryContainer
        )

        Spacer(Modifier.height(20.dp))

        BasicButton(
            text = "error",
            backgroundColor = MaterialTheme.colorScheme.error,
            textColor = MaterialTheme.colorScheme.onError
        )

        BasicButton(
            text = "errorContainer",
            backgroundColor = MaterialTheme.colorScheme.errorContainer,
            textColor = MaterialTheme.colorScheme.onErrorContainer
        )
    }
}