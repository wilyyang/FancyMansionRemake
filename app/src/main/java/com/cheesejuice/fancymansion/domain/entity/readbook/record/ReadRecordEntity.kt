package com.cheesejuice.fancymansion.domain.entity.readbook.record

data class ReadRecordEntity(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val savePage : Long
)