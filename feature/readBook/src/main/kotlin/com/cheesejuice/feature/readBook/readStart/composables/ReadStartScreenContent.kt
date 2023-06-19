package com.cheesejuice.feature.readBook.readStart.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cheesejuice.core.common.DIGIT_PAGE_ID
import com.cheesejuice.core.common.NOT_ASSIGN_SAVE_PAGE
import com.cheesejuice.core.common.monthDateFormat
import com.cheesejuice.core.ui.component.BasicButton
import com.cheesejuice.core.ui.component.BookImage
import com.cheesejuice.core.ui.theme.colorScheme
import com.cheesejuice.core.ui.theme.dividerLightColor
import com.cheesejuice.core.ui.theme.lightTextAlpha
import com.cheesejuice.core.ui.theme.primary_50
import com.cheesejuice.core.ui.theme.subTextAlpha
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
    illustrator : String,
    email : String,
    date : Long,
    savePageId : Long,
    savePageTitle : String?,
    savePageImage : File?,
    onEventSent : (event : ReadStartContract.Event) -> Unit
) {
    Column {
        Box(modifier = Modifier.weight(1f)) {
            BookCoverContent(
                modifier = Modifier.fillMaxSize(),
                coverImage = coverImage,
                description = description,
                title = title,
                writer = writer,
                illustrator = illustrator,
                email = email,
                date = date
            )


            Row(
                modifier = Modifier
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                MaterialTheme.colorScheme.background
                            )
                        )
                    )
                    .align(Alignment.BottomCenter)
                    .padding(top = 60.dp, bottom = 25.dp)
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth(),
            ) {
                BasicButton(
                    modifier = Modifier
                        .weight(1f)
                        .shadow(
                            elevation = 5.dp,
                            shape = MaterialTheme.shapes.small,
                            clip = true
                        ),
                    text = "처음부터 읽기",
                    textStyle = MaterialTheme.typography.titleMedium,
                    backgroundColor = primary_50,
                    contentPadding = PaddingValues(vertical = 16.dp, horizontal = 24.dp),
                    onClick = {
                        onEventSent(ReadStartContract.Event.ReadStartFirstPageClicked)
                    }
                )
            }
        }


        if (savePageId != NOT_ASSIGN_SAVE_PAGE && savePageTitle != null) {
            BottomPreviewSaveContent(
                savePageId = savePageId,
                savePageTitle = savePageTitle,
                savePageImage = savePageImage,
                onClickStartSavePage = {
                    onEventSent(ReadStartContract.Event.ReadStartSavePointClicked)
                }
            )
        }
    }

}

@Composable
fun BookCoverContent(
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

@Composable
fun BottomPreviewSaveContent(
    savePageId : Long,
    savePageTitle : String,
    savePageImage : File?,
    onClickStartSavePage : () -> Unit
) {
    Divider(color = dividerLightColor())
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        BookImage(
            modifier = Modifier
                .fillMaxHeight()
                .width(80.dp),
            image = savePageImage
        )
        Divider(
            color = dividerLightColor(),
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
                .padding(start = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = savePageTitle,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "page ${(savePageId / DIGIT_PAGE_ID).toInt()}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Divider(
            color = dividerLightColor(),
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentWidth()
                .clickable { onClickStartSavePage() }
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "여기부터\n다시읽기",
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = subTextAlpha),
                style = MaterialTheme.typography.labelMedium,
            )
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
            illustrator = Sample.book.config.illustrator,
            email = Sample.book.config.email,
            date = Sample.book.config.updateTime,
            savePageId = Sample.book.pageContents[2].pageId,
            savePageTitle = Sample.book.pageContents[2].pageTitle,
            savePageImage = null,
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