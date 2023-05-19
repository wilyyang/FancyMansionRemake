package com.cheesejuice.fancymansion.domain.entity.book

import com.cheesejuice.fancymansion.data.mapper.book.PageContentMapper
import com.cheesejuice.fancymansion.data.mapper.book.PageLogicMapper
import java.io.File

data class PageEntity(
    val content : PageContentMapper,
    val logic : PageLogicMapper,
    val image : File? = null
)