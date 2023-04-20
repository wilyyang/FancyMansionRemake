package com.cheesejuice.fancymansion.ui.content.read.page

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cheesejuice.fancymansion.PageType
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.data.model.ChoiceItem
import com.cheesejuice.fancymansion.data.source.local.Sample
import com.cheesejuice.fancymansion.ui.common.UiState
import com.cheesejuice.fancymansion.ui.common.component.BasicButton
import com.cheesejuice.fancymansion.ui.common.component.BookImage
import com.cheesejuice.fancymansion.ui.common.component.Label
import com.cheesejuice.fancymansion.ui.common.frame.BaseScreen
import com.cheesejuice.fancymansion.ui.theme.colorScheme
import com.cheesejuice.fancymansion.ui.theme.dividerAlpha
import com.cheesejuice.fancymansion.ui.theme.onTextAlpha
import com.cheesejuice.fancymansion.ui.theme.typography
import java.io.File

@Composable
fun ReadPageScreenSetup(
    navController : NavController,
    viewModel : ReadPageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState
    val page by viewModel.page.collectAsState()
    val pageLogic by viewModel.pageLogic.collectAsState()

    ReadPageScreenFrame(
        uiState = uiState,
        pageImage = File(page.pageImage),
        pageType = PageType.type(pageLogic.type),
        title = page.pageTitle,
        contentText = page.description,
        question = page.question,
        choiceList = pageLogic.choiceItems.toList(),

        testResourceId = Sample.getSampleImageId(page.pageImage),

        onClickChoiceItem = viewModel::clickChoiceItem
    )
}

@Preview(showSystemUi = true)
@Composable
fun ReadPageScreenPreview(){
    val pageIdx = 0
    val page = Sample.book.pages[pageIdx]
    val pageLogic = Sample.book.logic.logics[pageIdx]
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ){
        ReadPageScreenFrame(
            uiState = UiState.Loaded(),
            pageImage = File(page.pageImage),
            pageType = PageType.type(pageLogic.type),
            title = page.pageTitle,
            contentText = page.description,
            question = page.question,
            choiceList = pageLogic.choiceItems.toList(),

            testResourceId = Sample.getSampleImageId(page.pageImage)
        )
    }
}

@Composable
fun ReadPageScreenFrame(
    uiState: UiState,

    pageImage : File? = null,
    pageType : PageType,
    title : String,
    contentText : String,
    question : String,
    choiceList : List<ChoiceItem>,

    testResourceId : Int? = null,

    onClickChoiceItem : (ChoiceItem) -> Unit = {},
){
    val titleStyle : TextStyle = MaterialTheme.typography.headlineSmall
    val contentTextStyle : TextStyle = MaterialTheme.typography.bodyMedium
    val questionTextStyle : TextStyle = MaterialTheme.typography.titleLarge
    val choiceTextStyle : TextStyle = MaterialTheme.typography.titleMedium

    BaseScreen (
        uiState = uiState,
    ){
        Column(modifier = Modifier.fillMaxSize()){
            pageImage?.let {
                BookImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    imageFile = pageImage,
                    testResourceId = testResourceId
                )
                Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = dividerAlpha))
            }
            LazyColumn(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .weight(1f)
            ) {
                item {
                    Spacer(Modifier.height(20.dp))
                    Column(modifier = Modifier.padding(vertical = 20.dp)) {
                        if(pageType == PageType.END){
                            Label(
                                modifier = Modifier.padding(bottom = 4.dp),
                                label = stringResource(id = R.string.page_type_end)
                            )
                        }
                        Text(text = title, style = titleStyle)
                    }
                    Text(text = contentText, style = contentTextStyle)
                    Spacer(Modifier.height(20.dp))
                    Text(text = question, style = questionTextStyle)
                    Spacer(Modifier.height(20.dp))
                }

                items(choiceList) { choice ->
                    ChoiceButton(
                        choice = choice,
                        textStyle = choiceTextStyle,
                        onClickChoiceItem = onClickChoiceItem
                    )
                }
            }
        }
    }
}

@Composable
fun ChoiceButton(
    choice : ChoiceItem,
    textStyle : TextStyle,
    onClickChoiceItem : (ChoiceItem) -> Unit
){
    BasicButton(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.small),
        backgroundColor = MaterialTheme.colorScheme.primaryContainer,
        text = choice.title,
        textStyle = textStyle,
        textColor = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = onTextAlpha),
        onClick = { onClickChoiceItem(choice) },
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 15.dp),
        contentArrangement = Arrangement.Start
    )
}