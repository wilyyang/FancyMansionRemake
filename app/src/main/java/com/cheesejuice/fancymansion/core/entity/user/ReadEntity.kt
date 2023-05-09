package com.cheesejuice.fancymansion.core.entity.user

data class ReadEntity(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val savePage : Long
)