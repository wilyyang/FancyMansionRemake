package com.cheesejuice.fancymansion.ui.common.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.cheesejuice.fancymansion.ui.common.component.ButtonIcon
import com.cheesejuice.fancymansion.ui.common.component.DropDown
import com.cheesejuice.fancymansion.ui.common.component.LabelTextField
import com.cheesejuice.fancymansion.ui.common.component.SimpleTextField
import com.cheesejuice.fancymansion.ui.theme.FancyMansionTheme
import com.cheesejuice.fancymansion.ui.theme.green
import com.cheesejuice.fancymansion.ui.theme.red
import com.cheesejuice.fancymansion.ui.theme.yellow

@Composable
fun ThemeTestScreen(){
    FancyMansionTheme {
        Surface {
            IconTest()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ComponentTest(){

    // 배경
    Column (Modifier.background(MaterialTheme.colorScheme.background)){
        Column(modifier = Modifier.weight(1f)) {

            // 탑바 틴트
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.surfaceTint),
                verticalAlignment = Alignment.CenterVertically
            ){
                ButtonIcon( idIcon = R.drawable.chevron_left_24px)
                Spacer(Modifier.width(10.dp))
                Text(text = "최 상단", style = MaterialTheme.typography.titleMedium)

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                    ButtonIcon(idIcon = R.drawable.help_40px)
                    ButtonIcon(idIcon = R.drawable.info_40px)
                }
            }
            Divider(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp))

            // 탑바
            Row(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(MaterialTheme.colorScheme.surface),
                verticalAlignment = Alignment.CenterVertically
            ){
                ButtonIcon( idIcon = R.drawable.menu_24px)
                Spacer(Modifier.width(10.dp))
                Text(text = "타이틀 테스트", style = MaterialTheme.typography.titleMedium)

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                    ButtonIcon(idIcon = R.drawable.search_24px)
                    ButtonIcon(idIcon = R.drawable.settings_24px)
                    ButtonIcon(idIcon = R.drawable.refresh_24px)
                }
            }

            Divider(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp))

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
                onClick = { pair ->
                    selected.value = pair
                }
            )

            Column(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {

                Row(modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom){
                    Text("이름:", style = MaterialTheme.typography.titleMedium)

                    val cancelMessage = remember { mutableStateOf("") }
                    SimpleTextField(
                        modifier = Modifier
                            .fillMaxWidth(),
                        textStyle = MaterialTheme.typography.titleMedium,
                        value = cancelMessage.value,
                        hint = "이름를 입력하세요",
                        onValueChange = {
                            cancelMessage.value = it
                        }
                    )
                }

                val numberMessage = remember { mutableStateOf("") }
                LabelTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    label = "번호",
                    value = numberMessage.value,
                    hint = "예 : 010-1234-5678",
                    onValueChange = {
                        numberMessage.value = it
                    }
                )
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

        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())

        Column {
            Text(text = "Label Large", style = MaterialTheme.typography.labelLarge)
            Row {
                Text(text = "Title Large : ", style = MaterialTheme.typography.titleLarge)
                Text(text = "Body Large", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())

        Column {
            Text(text = "Label Medium", style = MaterialTheme.typography.labelMedium)
            Row {
                Text(text = "Title Medium : ", style = MaterialTheme.typography.titleMedium)
                Text(text = "Body Medium", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())

        Column {
            Text(text = "Label Small", style = MaterialTheme.typography.labelSmall)
            Row {
                Text(text = "Title Small : ", style = MaterialTheme.typography.titleSmall)
                Text(text = "Body Small", style = MaterialTheme.typography.bodySmall)
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