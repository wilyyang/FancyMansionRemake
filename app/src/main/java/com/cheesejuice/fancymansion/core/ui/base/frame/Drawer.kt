package com.cheesejuice.fancymansion.core.ui.base.frame

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.core.ui.component.BasicButton
import com.cheesejuice.fancymansion.core.ui.component.ButtonIconFixed

data class TestUser(val name: String, val email: String?, val userPhoto: String?)

class MenuType(val key : String, val title : String, val onClick : (item: TestUser) -> Unit)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainDrawer(
    userInfo : TestUser = TestUser(name = "갑동이", email = "ehdrnr1178@gmail.com", userPhoto = null),
    menuItems : List<MenuType>,
    onClickSetting: () -> Unit = {},
    onClickAccount: () -> Unit = {},
) {
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
            ButtonIconFixed(
                modifier = Modifier.size(48.dp),
                idIcon = R.drawable.settings_24px
            ){
                onClickSetting()
            }
        }

        // 계정 정보
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            val context = LocalContext.current
            Image(
                painter = rememberAsyncImagePainter(
                    model = userInfo.userPhoto ?: R.drawable.person_24px,
                    placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
                ),
                contentDescription = "profile",
                modifier = Modifier.size(52.dp).clip(CircleShape).clickable {},
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Max)
                    .padding(start = 24.dp)
                    .clickable {
                        onClickAccount()
                    }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = userInfo.name,
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
                    text = userInfo.email?: "UNKNOWN",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        // 메뉴
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .weight(1f)
                .padding(20.dp),
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(4.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(menuItems) {
                    menuItem ->
                BasicButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .shadow(8.dp, shape = MaterialTheme.shapes.small),
                    contentPadding = PaddingValues(vertical = 24.dp),
                    text = menuItem.title,
                    textStyle = MaterialTheme.typography.titleLarge,
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    textColor = MaterialTheme.colorScheme.onSurface,
                ) {
                    menuItem.onClick(userInfo)
                }
            }
        }
    }
}