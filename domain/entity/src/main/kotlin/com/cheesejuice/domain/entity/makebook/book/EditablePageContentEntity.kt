package com.cheesejuice.domain.entity.makebook.book

import com.cheesejuice.domain.entity.readbook.book.PageContentEntity

data class EditablePageContentEntity(
    var pageId : Long,
    var pageTitle : String,
    var pageImage : String = "",
    var description : String = "",
    var question : String
)

fun PageContentEntity.toEditable() = EditablePageContentEntity(
    pageId = pageId,
    pageTitle = pageTitle,
    pageImage = pageImage,
    description = description,
    question = question
)