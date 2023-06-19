package com.cheesejuice.feature.makeBook.makeStart.composables

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
import com.cheesejuice.core.common.monthDateFormat
import com.cheesejuice.core.ui.component.BookImage
import com.cheesejuice.core.ui.theme.colorScheme
import com.cheesejuice.core.ui.theme.lightTextAlpha
import com.cheesejuice.core.ui.theme.subTextAlpha
import com.cheesejuice.core.ui.theme.typography
import com.cheesejuice.domain.usecase.makeBook.sample.Sample
import com.cheesejuice.feature.makeBook.makeStart.MakeStartContract
import java.io.File

@Composable
fun MakeStartScreenContent(
    coverImage : File? = null,
    description : String,
    title : String,
    writer : String,
    illustrator : String,
    email : String,
    date : Long,
    onEventSent : (event : MakeStartContract.Event) -> Unit
) {
    Column {
        Box(modifier = Modifier.weight(1f)) {
            MakeBookCoverContent(
                modifier = Modifier.fillMaxSize(),
                coverImage = coverImage,
                description = description,
                title = title,
                writer = writer,
                illustrator = illustrator,
                email = email,
                date = date
            )
        }
    }
}

@Composable
fun MakeBookCoverContent(
    modifier : Modifier,
    coverImage : File? = null,
    description : String,
    title : String,
    writer : String,
    illustrator : String,
    email : String,
    date : Long
) {
    LazyColumn(
        modifier = modifier
    ) {
        item {
            Spacer(Modifier.height(50.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Surface(
                    modifier = Modifier
                        .width(280.dp)
                        .height(400.dp),
                    shape = MaterialTheme.shapes.extraSmall,
                    shadowElevation = 5.dp
                ) {
                    BookImage(
                        modifier = Modifier.fillMaxSize(),
                        image = coverImage
                    )
                }
            }

            Spacer(Modifier.height(20.dp))

            Column(modifier = Modifier.padding(vertical = 20.dp, horizontal = 30.dp)) {
                Text(
                    text = description,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 1
                )
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "$writer, $illustrator",
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = subTextAlpha),
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    modifier = Modifier.padding(top = 3.dp),
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = lightTextAlpha),
                    text = "${email.ifBlank { "미출시" }} ${monthDateFormat.format(date)}",
                    style = MaterialTheme.typography.labelMedium
                )

                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun MakeStartScreenContentPreview() {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = typography
    ) {
        MakeStartScreenContent(
            coverImage = null,
            description = Sample.book.config.description,
            title = Sample.book.config.title,
            writer = Sample.book.config.writer,
            illustrator = Sample.book.config.illustrator,
            email = Sample.book.config.email,
            date = Sample.book.config.updateTime,
            onEventSent = {}
        )
    }
}

@Composable
fun MakeStartScreenEmpty(message : String) {
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