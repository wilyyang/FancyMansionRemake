package com.cheesejuice.domain.interfaceRepository

import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.domain.entity.readbook.book.ConfigEntity
import com.cheesejuice.domain.entity.readbook.book.LogicEntity
import com.cheesejuice.domain.entity.readbook.book.PageContentEntity
import com.cheesejuice.domain.entity.readbook.record.CountRecordEntity
import com.cheesejuice.domain.entity.readbook.record.ReadRecordEntity
import java.io.File

interface ReadBookRepository {
    suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : ConfigEntity?
    suspend fun getCoverFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : LogicEntity?
    suspend fun getPageFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContentEntity?
    suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?

    suspend fun insertReadEntity(readRecordMapper : ReadRecordEntity) : Long
    suspend fun getReadEntity(userId : String, readMode : String, bookId : String) : ReadRecordEntity?
    suspend fun updateReadEntity(readRecordMapper : ReadRecordEntity)
    suspend fun deleteReadEntityFromId(userId : String, readMode : String, bookId : String)

    suspend fun insertCountEntity(countRecordMapper : CountRecordEntity) : Long
    suspend fun isCountEntityExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long) : Int?
    suspend fun incrementCountEntity(userId : String, readMode : String, bookId : String, elementId : Long)
    suspend fun deleteCountEntityFromBookId(userId : String, readMode : String, bookId : String)
}