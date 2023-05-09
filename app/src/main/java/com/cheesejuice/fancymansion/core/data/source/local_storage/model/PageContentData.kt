package com.cheesejuice.fancymansion.core.data.source.local_storage.model

import com.cheesejuice.fancymansion.core.entity.book.PageContentEntity

data class PageContentData(
    val pageId : Long,
    val pageTitle : String,
    val pageImage : String = "",
    val description : String = "",
    val question : String
)

fun PageContentData.asEntity() = PageContentEntity(
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