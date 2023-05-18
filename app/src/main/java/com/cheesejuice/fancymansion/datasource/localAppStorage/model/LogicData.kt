package com.cheesejuice.fancymansion.datasource.localAppStorage.model

import com.cheesejuice.fancymansion.data.mapper.book.LogicEntity

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