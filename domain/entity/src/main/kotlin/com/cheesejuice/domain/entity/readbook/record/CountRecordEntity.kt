package com.cheesejuice.domain.entity.readbook.record

data class CountRecordEntity(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val elementId : Long,
    val count : Int = 0
)