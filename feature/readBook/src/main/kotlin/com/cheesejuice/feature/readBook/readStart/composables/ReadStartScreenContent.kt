package com.cheesejuice.feature.readBook.readStart.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cheesejuice.core.ui.component.BookImage
import com.cheesejuice.core.ui.theme.dividerAlpha
import com.cheesejuice.domain.usecase.makeBook.sample.Sample
import com.cheesejuice.feature.readBook.readPage.ReadPageContract
import java.io.File
import com.cheesejuice.core.ui.R

@Preview(showSystemUi = true)
@Composable
fun ReadStartScreenContentPreview(){
    ReadStartScreenContent(
        coverImage = null,
        description = Sample.book.config.description,
        title = Sample.book.config.title,
        writer = Sample.book.config.writer,
        email = Sample.book.config.email,
        date = Sample.book.config.updateTime,
        currentPage = 0,
        currentPageTitle = "dfasgerag",
        onEventSent = {}
    )
}

@Composable
fun ReadStartScreenContent(
    coverImage : File? = null,
    description : String,
    title : String,
    writer : String,
    email : String,
    date : Long,
    currentPage : Int,
    currentPageTitle : String,
    onEventSent : (event : ReadPageContract.Event) -> Unit
) {
    val titleStyle : TextStyle = MaterialTheme.typography.headlineSmall

    Column(modifier = Modifier.fillMaxSize()) {
        BookImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp),
            image = painterResource(id = R.drawable.il_default_image)
        )
        
        Divider(color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = dividerAlpha))
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f)
        ) {
            item {
                Spacer(Modifier.height(20.dp))
                Column(modifier = Modifier.padding(vertical = 20.dp)) {
                    Text(text = title, style = titleStyle)
                }
            }
        }
    }
}