package com.cheesejuice.fancymansion.datasource.localAppStorage.model

import com.cheesejuice.core.common.PageType
import com.cheesejuice.fancymansion.data.mapper.book.PageLogicMapper

data class PageLogicData(
    val pageId : Long,
    val pageTitle : String,
    val type : Int = PageType.NORMAL.ordinal,
    val choiceItems : List<ChoiceItemData> = listOf()
)

fun PageLogicData.asMapper() = PageLogicMapper(
    pageId = pageId,
    pageTitle = pageTitle,
    type = type,
    choiceItems = choiceItems.map { it.asMapper() }.toMutableList()
)

fun PageLogicMapper.asData() = PageLogicData(
    pageId = pageId,
    pageTitle = pageTitle,
    type = type,
    choiceItems = choiceItems.map { it.asData() }
)