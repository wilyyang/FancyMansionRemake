package com.cheesejuice.fancymansion.data.repository.mapper.book

data class LogicEntity(
    val bookId : String,
    val logics : List<PageLogicEntity> = listOf()
)