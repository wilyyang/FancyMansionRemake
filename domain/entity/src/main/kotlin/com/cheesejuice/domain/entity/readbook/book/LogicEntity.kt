package com.cheesejuice.domain.entity.readbook.book

data class LogicEntity(
    val bookId : String,
    val logics : List<PageLogicEntity> = listOf()
)