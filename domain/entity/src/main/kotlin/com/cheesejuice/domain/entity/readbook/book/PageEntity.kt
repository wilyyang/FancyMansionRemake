package com.cheesejuice.domain.entity.readbook.book

import java.io.File

data class PageEntity(
    val content : PageContentEntity,
    val logic : PageLogicEntity,
    val image : File? = null
)