package com.cheesejuice.fancymansion.data.mapper.book

import com.cheesejuice.fancymansion.domain.entity.readbook.book.PageContentEntity

data class PageContentMapper(
    val pageId : Long,
    val pageTitle : String,
    val pageImage : String = "",
    val description : String = "",
    val question : String
)

fun PageContentMapper.asEntity() = PageContentEntity(
    pageId = pageId,
    pageTitle = pageTitle,
    pageImage = pageImage,
    description = description,
    question = question
)

fun PageContentEntity.asMapper() = PageContentMapper(
    pageId = pageId,
    pageTitle = pageTitle,
    pageImage = pageImage,
    description = description,
    question = question
)