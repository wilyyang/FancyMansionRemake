package com.cheesejuice.fancymansion.core.entity.book

data class LogicEntity(
    val bookId : String,
    val logics : List<PageLogicEntity> = listOf()
)