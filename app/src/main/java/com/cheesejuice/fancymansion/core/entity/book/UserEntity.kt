package com.cheesejuice.fancymansion.core.entity.book

data class UserEntity(
    val userId : String
)

data class ReadEntity(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val savePage : Long
)

data class CountEntity(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val elementId : Long,
    val count : Int = 0
)