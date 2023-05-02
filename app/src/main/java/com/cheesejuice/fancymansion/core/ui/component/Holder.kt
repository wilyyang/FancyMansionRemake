package com.cheesejuice.fancymansion.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.cheesejuice.fancymansion.R

data class TestData(
    val title: String = "제목 타이틀",
    val state: String = "상태 없음",
    val time : String = "2023-04-05 18시 10분",
    val oneline : String = "그것이 당신을 매료시킵니다. 정말 재밌다.",
    val author : String = "아기당근",
    val illustrator : String = "꼬마마녀 레미",
    val content : String = "아직도 안 읽은 사람을 위한 설명글.. 한줄보다는 길고 길고 긴 여행을 끝내고 나서 이토록 재밌는 그것을 향해",
)

class DropdownType(val key : String, val title : String, val onClick : (item: TestData) -> Unit)

@Composable
fun BookHolder(
    modifier : Modifier = Modifier,
    data : TestData = TestData(),
    dropdown : List<DropdownType>? = null,

    textColor : Color = MaterialTheme.colorScheme.onSurface,
    backgroundColor : Color = MaterialTheme.colorScheme.surface,

    stateColor: Color = MaterialTheme.colorScheme.primary,
    onStateColor: Color = MaterialTheme.colorScheme.onPrimary,

    onClick : ((item : TestData) -> Unit)? = { }
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        BoxWithConstraints {
            Column(
                modifier = Modifier
                    .background(color = backgroundColor)
                    .fillMaxWidth()
                    .clickable(enabled = onClick != null, role = Role.Button, onClick = { onClick?.invoke(data) })
                    .padding(16.dp)
            ) {

                // 상단 영역
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Label(
                        label = data.state,
                        labelColor = onStateColor,
                        backgroundColor = stateColor
                    )
                }

                Text(
                    text = data.title,
                    style = MaterialTheme.typography.titleLarge,
                    color = textColor,
                    modifier = Modifier.padding(end = 8.dp)
                )

                Text(
                    text = data.time,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f)
                )

                Spacer(modifier = Modifier.height(4.dp))

                // 하단 영역
                Text(
                    text = data.oneline,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.inversePrimary,
                    modifier = Modifier.padding(top = 8.dp)
                )

                RowInfo(
                    modifier = Modifier.padding(top = 8.dp),
                    title = "작가명 : ",
                    content = data.author,
                    titleColor = textColor,
                    contentColor = textColor,
                )

                RowInfo(
                    modifier = Modifier.padding(top = 8.dp),
                    title = "일러스트 : ",
                    content = data.illustrator,
                    titleColor = textColor,
                    contentColor = textColor,
                )

                Text(
                    text = data.content,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }

            if (!dropdown.isNullOrEmpty()) {
                var expanded by rememberSaveable { mutableStateOf(false) }
                Icon(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clip(MaterialTheme.shapes.medium)
                        .clickable { expanded = !expanded }
                        .padding(12.dp)
                        .size(20.dp),
                    painter = painterResource(id = R.drawable.more_vertical_20px),
                    contentDescription = "dropdown menu",
                    tint = MaterialTheme.colorScheme.onSurface
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(13.dp)
                ) {
                    DropdownMenu(
                        modifier = Modifier
                            .background(color = MaterialTheme.colorScheme.surface)
                            .padding(horizontal = 8.dp),
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        dropdown.forEach {
                            DropdownMenuItem(
                                text = {
                                    Text(text = it.title, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.fillMaxWidth())
                                },
                                onClick = {
                                    it.onClick(data)
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}