package com.cheesejuice.data.mapper.user

import com.cheesejuice.domain.entity.readbook.record.CountRecordEntity

data class CountRecordMapper(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val elementId : Long,
    val count : Int = 0
)

fun CountRecordMapper.asEntity() = CountRecordEntity(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    elementId = elementId,
    count = count
)

fun CountRecordEntity.asMapper() = CountRecordMapper(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    elementId = elementId,
    count = count
)