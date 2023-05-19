package com.cheesejuice.fancymansion.data.mapper.book

data class PageContentMapper(
    val pageId : Long,
    val pageTitle : String,
    val pageImage : String = "",
    val description : String = "",
    val question : String
)