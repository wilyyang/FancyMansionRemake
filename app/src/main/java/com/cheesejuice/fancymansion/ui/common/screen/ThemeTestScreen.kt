package com.cheesejuice.fancymansion.ui.common.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.ui.common.component.BasicButton
import com.cheesejuice.fancymansion.ui.common.component.BasicIcon
import com.cheesejuice.fancymansion.ui.common.component.DropDown
import com.cheesejuice.fancymansion.ui.theme.FancyMansionTheme
import com.cheesejuice.fancymansion.ui.theme.green
import com.cheesejuice.fancymansion.ui.theme.red
import com.cheesejuice.fancymansion.ui.theme.yellow

@Composable
fun ThemeTestScreen(){
    FancyMansionTheme {
        Surface {
            ComponentTest()
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
                BasicIcon( idIcon = R.drawable.chevron_left_24px)
                Spacer(Modifier.width(10.dp))
                Text(text = "최 상단", style = MaterialTheme.typography.titleMedium)

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                    BasicIcon(idIcon = R.drawable.help_40px)
                    BasicIcon(idIcon = R.drawable.info_40px)
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
                BasicIcon( idIcon = R.drawable.menu_24px)
                Spacer(Modifier.width(10.dp))
                Text(text = "타이틀 테스트", style = MaterialTheme.typography.titleMedium)

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                    BasicIcon(idIcon = R.drawable.search_24px)
                    BasicIcon(idIcon = R.drawable.settings_24px)
                    BasicIcon(idIcon = R.drawable.refresh_24px)
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

            Column {

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
                Simple(id, color)
            }
        }
    }

}

@Composable
fun Simple(imageId:Int, color:Color? = null){
    Column(
        Modifier
            .wrapContentSize()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = MaterialTheme.shapes.extraSmall
            )) {
        Icon(painter = painterResource(id = imageId), tint = color?:LocalContentColor.current, contentDescription = null)
    }
}


@Composable
fun ShapeTest(){
    Dialog(onDismissRequest = { /*TODO*/ }) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.extraLarge
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Text(text = "대화상자 타이틀", style = MaterialTheme.typography.titleLarge)
                Column {
                    Holder()
                    Holder()
                }

                Row{
                    Selector()
                    Selector()
                    Selector()
                }

                Column{
                    ShapeHolder(text = "extraSmall", shape = MaterialTheme.shapes.extraSmall)
                    ShapeHolder(text = "small", shape = MaterialTheme.shapes.small)
                    ShapeHolder(text = "medium", shape = MaterialTheme.shapes.medium)
                    ShapeHolder(text = "large", shape = MaterialTheme.shapes.large)
                    ShapeHolder(text = "extraLarge", shape = MaterialTheme.shapes.extraLarge)
                }
            }
        }
    }
}

@Composable
fun Holder(){
    Card(modifier = Modifier
        .padding(vertical = 5.dp)
        .fillMaxWidth(), shape = MaterialTheme.shapes.medium) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(text = "홀더 타이틀", style = MaterialTheme.typography.titleMedium)
            Row{
                Text(text = "내용 타이틀 : ", style = MaterialTheme.typography.titleSmall)
                Text(text = "내용 기입 스타일", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Composable
fun Selector(){
    Card(modifier = Modifier.padding(4.dp), shape = MaterialTheme.shapes.small) {
        Text(modifier = Modifier.padding(4.dp), text = "설렉터", style = MaterialTheme.typography.labelLarge)
    }
}

@Composable
fun ShapeHolder(text : String, shape: Shape){
    Card(modifier = Modifier.padding(4.dp), shape = shape) {
        Text(modifier = Modifier.padding(10.dp), text = text, style = MaterialTheme.typography.labelLarge)
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

        Text(text = "Title Large", style = MaterialTheme.typography.titleLarge)
        Text(text = "Title Medium", style = MaterialTheme.typography.titleMedium)
        Text(text = "Title Small", style = MaterialTheme.typography.titleSmall)

        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())

        Text(text = "Body Large", style = MaterialTheme.typography.bodyLarge)
        Text(text = "Body Medium", style = MaterialTheme.typography.bodyMedium)
        Text(text = "Body Small", style = MaterialTheme.typography.bodySmall)

        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())

        Text(text = "Label Large", style = MaterialTheme.typography.labelLarge)
        Text(text = "Label Medium", style = MaterialTheme.typography.labelMedium)
        Text(text = "Label Small", style = MaterialTheme.typography.labelSmall)

        Divider(
            Modifier
                .height(1.dp)
                .fillMaxWidth())
    }
}

@Composable
fun ColorTest(){
    Column (Modifier.scrollable(
        orientation = Orientation.Vertical,
        state = rememberScrollState()
    )){
        ClickHolder(
            text = "background",
            background = MaterialTheme.colorScheme.background,
            textColor = MaterialTheme.colorScheme.onBackground
        )

        Spacer(Modifier.height(20.dp))
        ClickHolder(
            text = "surface",
            background = MaterialTheme.colorScheme.surface,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        ClickHolder(
            text = "surfaceVariant",
            background = MaterialTheme.colorScheme.surfaceVariant,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
        ClickHolder(
            text = "inverseSurface",
            background = MaterialTheme.colorScheme.inverseSurface,
            textColor = MaterialTheme.colorScheme.inverseOnSurface
        )
        ClickHolder(
            text = "surfaceTint",
            background = MaterialTheme.colorScheme.surfaceTint,
            textColor = MaterialTheme.colorScheme.onSurface
        )

        Spacer(Modifier.height(20.dp))

        ClickHolder(
            text = "outline",
            background = MaterialTheme.colorScheme.background,
            textColor = MaterialTheme.colorScheme.outline
        )

        ClickHolder(
            text = "outlineVariant",
            background = MaterialTheme.colorScheme.background,
            textColor = MaterialTheme.colorScheme.outlineVariant
        )

        Spacer(Modifier.height(20.dp))

        ClickHolder(
            text = "primary",
            background = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.onPrimary
        )

        ClickHolder(
            text = "primaryContainer",
            background = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer
        )

        ClickHolder(
            text = "inversePrimary",
            background = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.inversePrimary
        )

        Spacer(Modifier.height(20.dp))

        ClickHolder(
            text = "secondary",
            background = MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.onSecondary
        )

        ClickHolder(
            text = "secondaryContainer",
            background = MaterialTheme.colorScheme.secondaryContainer,
            textColor = MaterialTheme.colorScheme.onSecondaryContainer
        )

        Spacer(Modifier.height(20.dp))

        ClickHolder(
            text = "tertiary",
            background = MaterialTheme.colorScheme.tertiary,
            textColor = MaterialTheme.colorScheme.onTertiary
        )

        ClickHolder(
            text = "tertiaryContainer",
            background = MaterialTheme.colorScheme.tertiaryContainer,
            textColor = MaterialTheme.colorScheme.onTertiaryContainer
        )

        Spacer(Modifier.height(20.dp))

        ClickHolder(
            text = "error",
            background = MaterialTheme.colorScheme.error,
            textColor = MaterialTheme.colorScheme.onError
        )

        ClickHolder(
            text = "errorContainer",
            background = MaterialTheme.colorScheme.errorContainer,
            textColor = MaterialTheme.colorScheme.onErrorContainer
        )
    }
}

@Composable
fun ColumnScope.ClickHolder(text : String, background : Color, textColor : Color) {
    Text(modifier = Modifier
        .weight(1f)
        .fillMaxWidth()
        .background(background)
        .clickable { }
        .border(
            width = 1.dp,
            color = Color.Black
        ),
        text = text,
        style = MaterialTheme.typography.titleLarge,
        color = textColor
    )
}