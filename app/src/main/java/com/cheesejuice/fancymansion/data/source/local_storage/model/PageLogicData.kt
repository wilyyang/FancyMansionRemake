package com.cheesejuice.fancymansion.data.source.local_storage.model

import com.cheesejuice.fancymansion.core.common.PageType
import com.cheesejuice.fancymansion.data.repository.mapper.book.PageLogicEntity

data class PageLogicData(
    val pageId : Long,
    val pageTitle : String,
    val type : Int = PageType.NORMAL.ordinal,
    val choiceItems : List<ChoiceItemData> = listOf()
)

fun PageLogicData.asMapper() = PageLogicEntity(
    pageId = pageId,
    pageTitle = pageTitle,
    type = type,
    choiceItems = choiceItems.map { it.asMapper() }.toMutableList()
)

fun PageLogicEntity.asData() = PageLogicData(
    pageId = pageId,
    pageTitle = pageTitle,
    type = type,
    choiceItems = choiceItems.map { it.asData() }
)