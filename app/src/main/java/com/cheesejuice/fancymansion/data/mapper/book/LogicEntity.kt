package com.cheesejuice.fancymansion.data.mapper.book

data class LogicEntity(
    val bookId : String,
    val logics : List<PageLogicEntity> = listOf()
)