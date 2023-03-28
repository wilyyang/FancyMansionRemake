package com.cheesejuice.fancymansion.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp
object Shape {
    val allRoundedCorner2  by lazy { RoundedCornerShape(2.dp) }
    val allRoundedCorner3  by lazy { RoundedCornerShape(3.dp) }
    val allRoundedCorner4  by lazy { RoundedCornerShape(4.dp) }
    val allRoundedCorner5  by lazy { RoundedCornerShape(5.dp) }
    val allRoundedCorner8  by lazy { RoundedCornerShape(8.dp) }
    val allRoundedCorner12 by lazy { RoundedCornerShape(12.dp) }
    val allRoundedCorner16 by lazy { RoundedCornerShape(16.dp) }
    val allRoundedCorner18 by lazy { RoundedCornerShape(18.dp) }

    val topRoundedCorner8  by lazy { RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp) }
    val topRoundedCorner16 by lazy { RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp) }

    val bottomRoundedCorner8 by lazy { RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp) }
}