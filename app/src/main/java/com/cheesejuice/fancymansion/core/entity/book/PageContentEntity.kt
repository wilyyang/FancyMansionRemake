package com.cheesejuice.fancymansion.core.entity.book

data class PageContentEntity(
    val pageId : Long,
    val pageTitle : String,
    val pageImage : String = "",
    val description : String = "",
    val question : String
)