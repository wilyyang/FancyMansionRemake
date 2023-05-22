package com.cheesejuice.datasource.localAppStorage.model

import com.cheesejuice.data.mapper.book.PageContentMapper

data class PageContentData(
    val pageId : Long,
    val pageTitle : String,
    val pageImage : String = "",
    val description : String = "",
    val question : String
)

fun PageContentData.asMapper() = PageContentMapper(
    pageId = pageId,
    pageTitle = pageTitle,
    pageImage = pageImage,
    description = description,
    question = question
)

fun PageContentMapper.asData() = PageContentData(
    pageId = pageId,
    pageTitle = pageTitle,
    pageImage = pageImage,
    description = description,
    question = question
)