package com.cheesejuice.feature.readBook.readPage.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.cheesejuice.core.common.PageType
import com.cheesejuice.core.common.R
import com.cheesejuice.core.ui.component.BasicButton
import com.cheesejuice.core.ui.component.BookImage
import com.cheesejuice.core.ui.component.Label
import com.cheesejuice.core.ui.theme.dividerColor
import com.cheesejuice.core.ui.theme.onTextAlpha
import com.cheesejuice.domain.entity.readbook.book.ChoiceItemEntity
import com.cheesejuice.feature.readBook.readPage.ReadPageContract
import java.io.File

@Composable
fun ReadPageScreenContent(
    pageImage : File? = null,
    pageType : PageType,
    title : String,
    contentText : String,
    question : String,
    choiceList : List<ChoiceItemEntity>,
    onEventSent : (event : ReadPageContract.Event) -> Unit
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
            Divider(color = dividerColor())
        }
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
        ) {
            item {
                Spacer(Modifier.height(20.dp))
                Column(modifier = Modifier.padding(vertical = 20.dp)) {
                    if (pageType == PageType.END) {
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
                    onChoiceItemClicked =  { onEventSent(ReadPageContract.Event.ChoiceItemClicked(choice)) }
                )
            }
        }
    }
}

@Composable
fun ChoiceButton(
    choice : ChoiceItemEntity,
    textStyle : TextStyle,
    onChoiceItemClicked : (ChoiceItemEntity) -> Unit
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
        onClick = { onChoiceItemClicked(choice) },
        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 15.dp),
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