package com.cheesejuice.fancymansion.domain.entity.readbook.book

data class PageContentEntity(
    val pageId : Long,
    val pageTitle : String,
    val pageImage : String = "",
    val description : String = "",
    val question : String
)