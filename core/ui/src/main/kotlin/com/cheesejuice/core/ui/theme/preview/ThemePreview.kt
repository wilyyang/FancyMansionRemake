package com.cheesejuice.core.ui.theme.preview

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cheesejuice.core.ui.base.BaseScreen
import com.cheesejuice.core.ui.base.LoadState
import com.cheesejuice.core.ui.base.frame.MainDrawer
import com.cheesejuice.core.ui.base.frame.MenuType
import com.cheesejuice.core.ui.component.BasicButton
import com.cheesejuice.core.ui.component.BookHolder
import com.cheesejuice.core.ui.component.DropDown
import com.cheesejuice.core.ui.component.DropdownType
import com.cheesejuice.core.ui.component.Label
import com.cheesejuice.core.ui.component.TextBox
import com.cheesejuice.core.ui.theme.TextStyleGroup
import com.cheesejuice.core.ui.theme.colorScheme
import com.cheesejuice.core.ui.theme.disableAlpha
import com.cheesejuice.core.ui.theme.dividerColor
import com.cheesejuice.core.ui.theme.dividerLightColor
import com.cheesejuice.core.ui.theme.typography
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

const val THEME_PREVIEW_ROUTE = "THEME_PREVIEW"

@Preview
@Composable
fun ThemePreviewScreenFrame(
    loadState: LoadState = LoadState.Idle
){
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope : CoroutineScope = rememberCoroutineScope()

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ){
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
                    MenuType(key = "1", title = "토스트 에러", onClick = { }),
                    MenuType(key = "2", title = "드로어 닫기", onClick = { }),
                    MenuType(key = "3", title = "대화상자 에러", onClick = { }),
                )
                MainDrawer(menuItems = menuList)
            },
            loadState  = loadState
        ) {
            ThemePreviewScreenContent(onClickBottom = {})
        }
    }
}

@Composable
fun ThemePreviewScreenContent(
    onClickBottom : () -> Unit = {}
){
    // 배경
    Column {
        Column(modifier = Modifier.weight(1f)) {

            // 상단 드롭다운
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

                // 텍스트 박스
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.Top){

                    val focus = remember { mutableStateOf("") }
                    val error: MutableState<String?> = remember { mutableStateOf(null) }
                    TextBox(
                        modifier = Modifier.fillMaxWidth(),
                        value = focus.value,
                        hint = "",
                        onValueChange = {
                            focus.value = it
                            error.value = if(it.isBlank()) { "텍스트가 입력되지 않았습니다." } else null
                        },
                        isBorder = true,
                        error = "텍스트가 없습니다"
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                    verticalAlignment = Alignment.Top){

                    TextBox(
                        modifier = Modifier.fillMaxWidth(),
                        value = "Disabled 텍스트박스",
                        hint = "Disabled 텍스트박스",
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

                // 홀더
               val dropdownList = listOf(
                    DropdownType(key = "1", title = "삭제하기", onClick = { data -> Log.e("Crane", "${data.title} 삭제하기")}),
                    DropdownType(key = "2", title = "추가하기", onClick = { data -> Log.e("Crane", "${data.title} 추가하기")}),
                    DropdownType(key = "3", title = "수정하기", onClick = { data -> Log.e("Crane", "${data.title} 수정하기")}),
                    DropdownType(key = "4", title = "삭제하기", onClick = { data -> Log.e("Crane", "${data.title} 삭제하기")}),
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

        // 하단 버튼
        BasicButton(modifier = Modifier.fillMaxWidth(), text = "기본 버튼 테스트", isClickable = true) {
            onClickBottom()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ThemePreviewMaterialTypography(){
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ){
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
}

@Preview(showSystemUi = true)
@Composable
fun ThemePreviewMaterialColor(){
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ){
        Column {
            Divider(color = dividerColor())

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                item{
                    SimpleLabel(
                        label = "onSurface (Focusing)",
                        labelColor = colorScheme.onSurface,
                        backgroundColor = colorScheme.surface
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    SimpleLabel(
                        label = "onSurfaceVariant (Not Focusing)",
                        labelColor = colorScheme.onSurfaceVariant,
                        backgroundColor = colorScheme.surfaceVariant
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    SimpleLabel(
                        label = "onSurfaceDisable",
                        labelColor = colorScheme.onSurface.copy(alpha = disableAlpha),
                        backgroundColor = colorScheme.surface.copy(alpha = disableAlpha)
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(color = dividerColor())
                    Text(text = "dividerColor", color = dividerColor())
                    Divider(color = dividerColor())
                    Spacer(modifier = Modifier.height(10.dp))

                    SimpleLabel(
                        label = "onPrimary",
                        labelColor = colorScheme.onPrimary,
                        backgroundColor = colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    SimpleLabel(
                        label = "inversePrimary",
                        labelColor = colorScheme.inversePrimary,
                        backgroundColor = colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    SimpleLabel(
                        label = "onSecondary",
                        labelColor = colorScheme.onSecondary,
                        backgroundColor = colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    SimpleLabel(
                        label = "onTertiary",
                        labelColor = colorScheme.onTertiary,
                        backgroundColor = colorScheme.tertiary
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Divider(color = dividerLightColor())
                    Text(text = "dividerLightColor", color = dividerLightColor())
                    Divider(color = dividerLightColor())
                    Spacer(modifier = Modifier.height(10.dp))

                    SimpleLabel(
                        label = "onPrimaryContainer",
                        labelColor = colorScheme.onPrimaryContainer,
                        backgroundColor = colorScheme.primaryContainer
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    SimpleLabel(
                        label = "onSecondaryContainer",
                        labelColor = colorScheme.onSecondaryContainer,
                        backgroundColor = colorScheme.secondaryContainer
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    SimpleLabel(
                        label = "onTertiaryContainer",
                        labelColor = colorScheme.onTertiaryContainer,
                        backgroundColor = colorScheme.tertiaryContainer
                    )
                }
            }
        }
    }
}


@Composable
fun SimpleLabel(
    label : String,
    labelColor : Color,
    backgroundColor : Color
){
    Label(
        label = "On Color : $label",
        labelStyle = MaterialTheme.typography.titleMedium,
        labelColor = labelColor,
        backgroundColor = backgroundColor,
        borderWidth = 1.dp,
        borderColor = labelColor
    )
}