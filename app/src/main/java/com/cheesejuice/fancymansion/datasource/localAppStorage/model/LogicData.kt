package com.cheesejuice.fancymansion.datasource.localAppStorage.model

import com.cheesejuice.fancymansion.data.mapper.book.LogicMapper

data class LogicData(
    val bookId : String,
    var logics : List<PageLogicData> = listOf()
)

fun LogicData.asMapper() = LogicMapper(
    bookId = bookId,
    logics = logics.map { it.asMapper() }.toMutableList()
)

fun LogicMapper.asData() = LogicData(
    bookId = bookId,
    logics = logics.map { it.asData() }
)