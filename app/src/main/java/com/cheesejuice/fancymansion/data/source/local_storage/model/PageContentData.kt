package com.cheesejuice.fancymansion.data.source.local_storage.model

import com.cheesejuice.fancymansion.data.repository.mapper.book.PageContentEntity

data class PageContentData(
    val pageId : Long,
    val pageTitle : String,
    val pageImage : String = "",
    val description : String = "",
    val question : String
)

fun PageContentData.asMapper() = PageContentEntity(
    pageId = pageId,
    pageTitle = pageTitle,
    pageImage = pageImage,
    description = description,
    question = question
)

fun PageContentEntity.asData() = PageContentData(
    pageId = pageId,
    pageTitle = pageTitle,
    pageImage = pageImage,
    description = description,
    question = question
)