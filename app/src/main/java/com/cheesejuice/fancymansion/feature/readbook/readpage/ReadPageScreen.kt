package com.cheesejuice.fancymansion.feature.readbook.readpage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cheesejuice.core.common.PageType
import com.cheesejuice.core.ui.base.BaseScreen
import com.cheesejuice.core.ui.base.EmptyState
import com.cheesejuice.core.ui.base.LoadingState
import com.cheesejuice.core.ui.component.BasicButton
import com.cheesejuice.core.ui.component.BookImage
import com.cheesejuice.core.ui.component.Label
import com.cheesejuice.core.ui.theme.colorScheme
import com.cheesejuice.core.ui.theme.dividerAlpha
import com.cheesejuice.core.ui.theme.onTextAlpha
import com.cheesejuice.core.ui.theme.typography
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.domain.entity.readbook.book.ChoiceItemEntity
import com.cheesejuice.fancymansion.domain.entity.readbook.book.PageEntity
import com.cheesejuice.fancymansion.domain.usecase.makeBook.sample.Sample
import java.io.File

@Composable
fun ReadPageScreenSetup(
    navController : NavController,
    viewModel : ReadPageViewModel = hiltViewModel()
) {
    val loadingState by viewModel.loadingState.collectAsState()
    val emptyState by viewModel.emptyState.collectAsState()
    val page by viewModel.page
    ReadPageScreenFrame(
        loadingState = loadingState,
        emptyState = emptyState,
        page = page,
        onClickChoiceItem = viewModel::onClickChoiceItem
    )
}

@Composable
fun ReadPageScreenFrame(
    loadingState : LoadingState? = null,
    emptyState : EmptyState? = null,
    page : PageEntity,
    onClickChoiceItem : (ChoiceItemEntity) -> Unit = {}
) {
    BaseScreen(
        loadingState = loadingState
    ) {
        ReadPageScreenContent(
            pageImage = page.image,
            pageType = PageType.type(page.logic.type),
            title = page.content.pageTitle,
            contentText = page.content.description,
            question = page.content.question,
            choiceList = page.logic.choiceItems.toList(),
            onClickChoiceItem = onClickChoiceItem
        )

        emptyState?.let {
            ReadPageScreenEmpty(it.message)
        }
    }
}
@Composable
fun ReadPageScreenContent(
    pageImage : File? = null,
    pageType : com.cheesejuice.core.common.PageType,
    title : String,
    contentText : String,
    question : String,
    choiceList : List<ChoiceItemEntity>,
    onClickChoiceItem : (ChoiceItemEntity) -> Unit = {}
) {
    val titleStyle : TextStyle = MaterialTheme.typography.headlineSmall
    val contentTextStyle : TextStyle = MaterialTheme.typography.bodyMedium
    val questionTextStyle : TextStyle = MaterialTheme.typography.titleLarge
    val choiceTextStyle : TextStyle = MaterialTheme.typography.titleMedium

    Column(modifier = Modifier.fillMaxSize()) {
        pageImage?.let {
            BookImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp),
                image = pageImage
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
                    if (pageType == com.cheesejuice.core.common.PageType.END) {
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

@Composable
fun ChoiceButton(
    choice : ChoiceItemEntity,
    textStyle : TextStyle,
    onClickChoiceItem : (ChoiceItemEntity) -> Unit
) {
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

@Composable
fun ReadPageScreenEmpty(message : String) {
    val contentTextStyle : TextStyle = MaterialTheme.typography.bodyMedium

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = message, style = contentTextStyle)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ReadPageScreenPreview() {
    val samplePageIdx = 0
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ) {
        ReadPageScreenFrame(
            loadingState = null,
            emptyState = null,
            page = PageEntity(
                content = Sample.book.pageContents[samplePageIdx],
                logic = Sample.book.logic.logics[samplePageIdx],
                image = File("")
            )
        )
    }
}
