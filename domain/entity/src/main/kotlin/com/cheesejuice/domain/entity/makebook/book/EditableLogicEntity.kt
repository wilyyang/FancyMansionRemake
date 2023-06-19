package com.cheesejuice.domain.entity.makebook.book

import com.cheesejuice.domain.entity.readbook.book.LogicEntity

data class EditableLogicEntity(
    var bookId : String,
    var logics : MutableList<EditablePageLogicEntity> = mutableListOf()
)

fun LogicEntity.toEditable() = EditableLogicEntity(
    bookId = bookId,
    logics = logics.map { it.toEditable() }.toMutableList()
)