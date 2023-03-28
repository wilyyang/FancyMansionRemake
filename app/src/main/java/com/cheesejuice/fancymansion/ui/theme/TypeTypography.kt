package com.cheesejuice.fancymansion.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cheesejuice.fancymansion.R

val spoqaHanSansNeo = FontFamily(
    Font(R.font.spoqa_hansans_neo_regular, FontWeight.Normal),
    Font(R.font.spoqa_hansans_neo_medium, FontWeight.Medium),
    Font(R.font.spoqa_hansans_neo_bold, FontWeight.Bold)
)

enum class TypeTypography(val style: TextStyle) {
    h1(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            lineHeight = 46.sp
        )
    ),
    h2(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp,
            lineHeight = 42.sp
        )
    ),
    h3(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            lineHeight = 36.sp
        )
    ),
    h4(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            lineHeight = 32.sp
        )
    ),
    h5(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            lineHeight = 28.sp
        )
    ),
    h6(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 24.sp
        )
    ),
    h7(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    ),
    s1(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    ),
    s2(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    ),
    p1(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    ),
    p2(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    ),
    c1(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 18.sp
        )
    ),
    c2(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 18.sp
        )
    ),
    b1(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp,
            lineHeight = 24.sp
        )
    ),
    b2(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
    ),

    b3(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    ),
    b4(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 18.sp
        )
    ),
    b5(
        TextStyle(
            fontFamily = spoqaHanSansNeo,
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            lineHeight = 12.sp
        )
    );
}