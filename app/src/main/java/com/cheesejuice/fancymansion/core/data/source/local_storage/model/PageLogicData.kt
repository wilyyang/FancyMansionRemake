package com.cheesejuice.fancymansion.core.data.source.local_storage.model

import com.cheesejuice.fancymansion.core.common.PageType
import com.cheesejuice.fancymansion.core.entity.book.PageLogicEntity

data class PageLogicData(
    val pageId : Long,
    val pageTitle : String,
    val type : Int = PageType.NORMAL.ordinal,
    val choiceItems : List<ChoiceItemData> = listOf()
)

fun PageLogicData.asEntity() = PageLogicEntity(
    pageId = pageId,
    pageTitle = pageTitle,
    type = type,
    choiceItems = choiceItems.map { it.asEntity() }.toMutableList()
)

fun PageLogicEntity.asData() = PageLogicData(
    pageId = pageId,
    pageTitle = pageTitle,
    type = type,
    choiceItems = choiceItems.map { it.asData() }
)