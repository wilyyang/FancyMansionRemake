package com.cheesejuice.fancymansion.data.mapper.book

data class LogicMapper(
    val bookId : String,
    val logics : List<PageLogicMapper> = listOf()
)