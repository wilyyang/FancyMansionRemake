package com.cheesejuice.fancymansion.ui.common.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.ui.common.component.BasicButton
import com.cheesejuice.fancymansion.ui.common.component.BookHolder
import com.cheesejuice.fancymansion.ui.common.component.ButtonIcon
import com.cheesejuice.fancymansion.ui.common.component.DropDown
import com.cheesejuice.fancymansion.ui.common.component.DropdownType
import com.cheesejuice.fancymansion.ui.common.component.TextBox
import com.cheesejuice.fancymansion.ui.theme.FancyMansionTheme
import com.cheesejuice.fancymansion.ui.theme.TextStyleGroup
import com.cheesejuice.fancymansion.ui.theme.colorScheme
import com.cheesejuice.fancymansion.ui.theme.green
import com.cheesejuice.fancymansion.ui.theme.red
import com.cheesejuice.fancymansion.ui.theme.yellow

@Composable
fun ThemeTestScreen(){
    FancyMansionTheme {

        ComponentTest()
    }
}

@Preview(showSystemUi = true)
@Composable
fun ComponentTest(){
    // 배경
    Column {
        Column(modifier = Modifier.weight(1f)) {
            // 탑바
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.surface),
                verticalAlignment = Alignment.CenterVertically
            ){
                ButtonIcon( idIcon = R.drawable.menu_24px)
                Spacer(Modifier.width(10.dp))
                Text(text = "툴바 테스트", style = MaterialTheme.typography.titleLarge)
            }

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
                onClick = { pair -> selected.value = pair }
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
                        textStyle = MaterialTheme.typography.bodyMedium,
                        maxLine = 1,
                        value = focus.value,
                        hint = "포커스 이동",
                        label = "포커스 이동",
                        onValueChange = {
                            focus.value = it
                            error.value = if(it.isBlank()) { "텍스트가 입력되지 않았습니다." } else null
                        },
                        isDivider = true,
                        error = error.value
                    )
                }

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)){

                    val longText = remember { mutableStateOf("") }
                    TextBox(
                        modifier = Modifier.fillMaxWidth(),
                        textStyle = MaterialTheme.typography.bodyMedium,
                        minLine = 3,
                        maxLine = 3,
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
                        .fillMaxWidth()
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
                            textColor = colorScheme.inverseOnSurface,
                            backgroundColor = colorScheme.inverseSurface,
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

                        BookHolder(
                            modifier = Modifier.padding(vertical = 4.dp),
                            textColor = colorScheme.onErrorContainer,
                            backgroundColor = colorScheme.errorContainer,
                            dropdown = dropdownList
                        )
                    }
                }
            }
        }
        BasicButton(modifier = Modifier.fillMaxWidth(), text = "기본 버튼 테스트", isClickable = false)
    }
}

@Composable
fun IconTest(){
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
fun TypographyTest(){
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
fun ColorTest(){
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
        BasicButton(
            text = "inverseSurface",
            backgroundColor = MaterialTheme.colorScheme.inverseSurface,
            textColor = MaterialTheme.colorScheme.inverseOnSurface
        )
        BasicButton(
            text = "surfaceTint",
            backgroundColor = MaterialTheme.colorScheme.surfaceTint,
            textColor = MaterialTheme.colorScheme.onSurface
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