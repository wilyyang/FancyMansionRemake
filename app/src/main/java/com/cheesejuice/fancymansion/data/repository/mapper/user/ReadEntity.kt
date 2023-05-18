package com.cheesejuice.fancymansion.data.repository.mapper.user

data class ReadEntity(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val savePage : Long
)