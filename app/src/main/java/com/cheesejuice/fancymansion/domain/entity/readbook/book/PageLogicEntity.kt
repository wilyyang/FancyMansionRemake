package com.cheesejuice.fancymansion.domain.entity.readbook.book

import com.cheesejuice.core.common.PageType

data class PageLogicEntity(
    val pageId : Long,
    val pageTitle : String,
    val type : Int = PageType.NORMAL.ordinal,
    val choiceItems : List<ChoiceItemEntity> = listOf()
)