package com.cheesejuice.fancymansion.data.mapper.book

import com.cheesejuice.fancymansion.domain.entity.readbook.book.LogicEntity

data class LogicMapper(
    val bookId : String,
    val logics : List<PageLogicMapper> = listOf()
)

fun LogicMapper.asEntity() = LogicEntity(
    bookId = bookId,
    logics = logics.map { it.asEntity() }.toMutableList()
)

fun LogicEntity.asMapper() = LogicMapper(
    bookId = bookId,
    logics = logics.map { it.asMapper() }
)