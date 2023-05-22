package com.cheesejuice.fancymansion.data.mapper.book

import com.cheesejuice.core.common.PageType
import com.cheesejuice.fancymansion.domain.entity.readbook.book.PageLogicEntity

data class PageLogicMapper(
    val pageId : Long,
    val pageTitle : String,
    val type : Int = PageType.NORMAL.ordinal,
    val choiceItems : List<ChoiceItemMapper> = listOf()
)

fun PageLogicMapper.asEntity() = PageLogicEntity(
    pageId = pageId,
    pageTitle = pageTitle,
    type = type,
    choiceItems = choiceItems.map { it.asEntity() }.toMutableList()
)

fun PageLogicEntity.asMapper() = PageLogicMapper(
    pageId = pageId,
    pageTitle = pageTitle,
    type = type,
    choiceItems = choiceItems.map { it.asMapper() }
)