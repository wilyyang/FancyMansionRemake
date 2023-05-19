package com.cheesejuice.fancymansion.data.mapper.user

data class ReadRecordMapper(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val savePage : Long
)