package com.cheesejuice.fancymansion.data.mapper.user

import com.cheesejuice.fancymansion.domain.entity.readbook.record.ReadRecordEntity

data class ReadRecordMapper(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val savePage : Long
)

fun ReadRecordMapper.asEntity() = ReadRecordEntity(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    savePage = savePage
)

fun ReadRecordEntity.asMapper() = ReadRecordMapper(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    savePage = savePage
)