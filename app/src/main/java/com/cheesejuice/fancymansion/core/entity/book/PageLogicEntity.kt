package com.cheesejuice.fancymansion.core.entity.book

import com.cheesejuice.fancymansion.core.common.PageType

data class PageLogicEntity(
    val pageId : Long,
    val pageTitle : String,
    val type : Int = PageType.NORMAL.ordinal,
    val choiceItems : List<ChoiceItemEntity> = listOf()
)