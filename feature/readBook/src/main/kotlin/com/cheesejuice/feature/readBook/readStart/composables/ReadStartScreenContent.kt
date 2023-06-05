package com.cheesejuice.feature.readBook.readStart.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cheesejuice.core.ui.component.BookImage
import com.cheesejuice.core.ui.theme.colorScheme
import com.cheesejuice.core.ui.theme.dividerColor
import com.cheesejuice.core.ui.theme.typography
import com.cheesejuice.domain.usecase.makeBook.sample.Sample
import com.cheesejuice.feature.readBook.readStart.ReadStartContract
import java.io.File

@Composable
fun ReadStartScreenContent(
    coverImage : File? = null,
    description : String,
    title : String,
    writer : String,
    email : String,
    date : Long,
    savePageId : Long,
    savePageTitle : String?,
    onEventSent : (event : ReadStartContract.Event) -> Unit
) {
    BookCoverContent(
        coverImage = coverImage,
        description = description,
        title = title,
        writer = writer,
        email = email,
        date = date
    )
}

@Composable
fun BookCoverContent(
    coverImage : File? = null,
    description : String,
    title : String,
    writer : String,
    email : String,
    date : Long
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Spacer(Modifier.height(50.dp))

            Column(modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally) {
                BookImage(
                    modifier = Modifier
                        .width(280.dp)
                        .height(420.dp)
                        .border(border = BorderStroke(width = 1.dp, color = dividerColor())),
                    image = coverImage
                )
            }

            Spacer(Modifier.height(20.dp))

            Column(modifier = Modifier.padding(vertical = 20.dp)) {
                val titleStyle : TextStyle = MaterialTheme.typography.headlineSmall
                Text(text = description, style = MaterialTheme.typography.bodyLarge, maxLines = 2)
                Text(text = title, style = titleStyle)

                Text(text = writer, style = MaterialTheme.typography.bodyLarge)
                Text(text = "$email $date", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ReadStartScreenContentPreview() {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ) {
        ReadStartScreenContent(
            coverImage = null,
            description = Sample.book.config.description,
            title = Sample.book.config.title,
            writer = Sample.book.config.writer,
            email = Sample.book.config.email,
            date = Sample.book.config.updateTime,
            savePageId = Sample.book.pageContents[2].pageId,
            savePageTitle = Sample.book.pageContents[2].pageTitle,
            onEventSent = {}
        )
    }
}

@Composable
fun ReadStartScreenEmpty(message : String) {
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