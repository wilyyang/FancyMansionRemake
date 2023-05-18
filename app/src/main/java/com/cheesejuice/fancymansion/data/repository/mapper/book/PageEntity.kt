package com.cheesejuice.fancymansion.data.repository.mapper.book

import java.io.File

data class PageEntity(
    val content : PageContentEntity,
    val logic : PageLogicEntity,
    val image : File? = null
)