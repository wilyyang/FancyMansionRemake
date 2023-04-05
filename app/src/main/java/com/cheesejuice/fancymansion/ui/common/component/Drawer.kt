package com.cheesejuice.fancymansion.ui.common.component

import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cheesejuice.fancymansion.R

@Preview
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Drawer() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.End
        ) {
            ButtonIcon(
                modifier = Modifier.size(48.dp),
                idIcon = R.drawable.settings_24px
            )
        }

        // 계정 정보
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            val context = LocalContext.current
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "profile",
                modifier = Modifier.size(52.dp).clip(CircleShape).clickable {},
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(start = 24.dp)
                    .clickable {}
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text =  "UNKNOWN",
                        style = MaterialTheme.typography.headlineSmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier.widthIn(max = 200.dp)
                    )
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.chevron_right_24px),
                        contentDescription = null
                    )
                }
                Text(
                    text = "ehdrnr1178@gmail.com"
                )
            }
        }

        // 메뉴 들어가는 곳
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .weight(1f)
                .padding(20.dp),
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
        }
    }
}