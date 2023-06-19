package com.cheesejuice.domain.entity.makebook.book

import com.cheesejuice.core.common.PageType
import com.cheesejuice.domain.entity.readbook.book.PageLogicEntity

data class EditablePageLogicEntity(
    var pageId : Long,
    var pageTitle : String,
    var type : Int = PageType.NORMAL.ordinal,
    var choiceItems : MutableList<EditableChoiceItemEntity> = mutableListOf()
)

fun PageLogicEntity.toEditable() = EditablePageLogicEntity(
    pageId = pageId,
    pageTitle = pageTitle,
    type = type,
    choiceItems = choiceItems.map { it.toEditable() }.toMutableList()
)