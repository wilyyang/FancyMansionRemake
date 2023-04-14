package com.cheesejuice.fancymansion.ui.content.read.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.cheesejuice.fancymansion.ui.common.UiState
import com.cheesejuice.fancymansion.ui.common.component.BasicButton
import com.cheesejuice.fancymansion.ui.common.component.BookImage
import com.cheesejuice.fancymansion.ui.common.component.Label
import com.cheesejuice.fancymansion.ui.common.frame.BaseScreen
import com.cheesejuice.fancymansion.ui.theme.colorScheme
import com.cheesejuice.fancymansion.ui.theme.typography
import java.io.File

data class Choice(val id : Long = -1L, val title : String = "")

@Composable
fun ReadPageScreenSetup(
    navController : NavController,
    viewModel : ReadPageViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val choiceList = listOf(Choice(title = "선택지1"), Choice(title = "선택지2"), Choice(title = "선택지3"))
    ReadPageScreenFrame(
        uiState = uiState,
        pageImage = File(""),
        pageType = PageType.END,
        title = "타이틀 테스트",
        contentText = "콘텐츠 내용 테스트 콘텐츠 내용 테스트 콘텐츠 내용 테스트 콘텐츠 내용 테스트 콘텐츠 내용 테스트",
        question = "질문 테스트 질문 테스트 ?",
        choiceList = choiceList
    )
}

@Preview(showSystemUi = true)
@Composable
fun ReadPageScreenPreview(){
    val choiceList = listOf(Choice(title = "선택지1"), Choice(title = "선택지2"), Choice(title = "선택지3"))

    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ){
        ReadPageScreenFrame(
            pageImage = File(""),
            pageType = PageType.END,
            title = "타이틀 테스트",
            contentText = "콘텐츠 내용 테스트 콘텐츠 내용 테스트 콘텐츠 내용 테스트 콘텐츠 내용 테스트 콘텐츠 내용 테스트",
            question = "질문 테스트 질문 테스트 ?",
            choiceList = choiceList
        )
    }
}

@Composable
fun ReadPageScreenFrame(
    uiState: UiState = UiState.Loaded(null),

    pageImage : File? = null,
    pageType : PageType = PageType.NORMAL,
    title : String = "",
    contentText : String = "",
    question : String = "",
    choiceList : List<Choice>? = null,

    moveToNextPage : (Choice) -> Unit = {},
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
                    imageFile = pageImage
                )
                Divider(Modifier.height(1.dp))
            }
            Spacer(Modifier.height(20.dp))
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp)
            ) {
                item{
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

                choiceList?.let {
                    items(it) { choice ->
                        BasicButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.medium)
                                .padding(vertical = 8.dp),
                            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
                            text = choice.title,
                            textStyle = choiceTextStyle,
                            textColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            onClick = { moveToNextPage(choice) },
                            contentArrangement = Arrangement.Start
                        )
                    }
                }
            }
        }
    }
}