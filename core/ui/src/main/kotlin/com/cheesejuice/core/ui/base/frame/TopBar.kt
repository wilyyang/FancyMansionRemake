package com.cheesejuice.core.ui.base.frame

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cheesejuice.core.ui.component.ButtonIconFixed
import com.cheesejuice.core.ui.R

@Composable
fun TopBar(
    title: String? = null,
    idNavigationIcon: Int? = null,
    onClickNavigation: (() -> Unit)? = null,
    actions: @Composable (RowScope.() -> Unit)? = null
) {
    // 타이틀, 백 버튼 리스너 가 있으면 탑바 생성
    if (title != null || onClickNavigation != null) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            onClickNavigation?.also {
                ButtonIconFixed(
                    modifier = Modifier.size(56.dp),
                    idIcon = idNavigationIcon ?: R.drawable.ic_chevron_left_24px,
                    onClick = it
                )
            }

            title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(start = if (onClickNavigation == null) 16.dp else 0.dp)
                        .weight(1f)
                        .wrapContentHeight()
                )
            }
            actions?.invoke(this)
        }
    }
}