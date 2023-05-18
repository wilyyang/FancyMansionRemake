package com.cheesejuice.fancymansion.core.data.source.local_storage.model

import com.cheesejuice.fancymansion.core.entity.book.LogicEntity

data class LogicData(
    val bookId : String,
    var logics : List<PageLogicData> = listOf()
)

fun LogicData.asMapper() = LogicEntity(
    bookId = bookId,
    logics = logics.map { it.asMapper() }.toMutableList()
)

fun LogicEntity.asData() = LogicData(
    bookId = bookId,
    logics = logics.map { it.asData() }
)