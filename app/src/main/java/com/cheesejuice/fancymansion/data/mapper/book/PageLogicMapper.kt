package com.cheesejuice.fancymansion.data.mapper.book

import com.cheesejuice.fancymansion.core.common.PageType

data class PageLogicMapper(
    val pageId : Long,
    val pageTitle : String,
    val type : Int = PageType.NORMAL.ordinal,
    val choiceItems : List<ChoiceItemMapper> = listOf()
)