package com.cheesejuice.domain.interfaceRepository

import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.domain.entity.book.ConfigEntity
import com.cheesejuice.domain.entity.readbook.book.LogicEntity
import com.cheesejuice.domain.entity.readbook.book.PageContentEntity
import com.cheesejuice.domain.entity.readbook.record.CountRecordEntity
import com.cheesejuice.domain.entity.readbook.record.ReadRecordEntity
import java.io.File

interface ReadBookRepository {
    suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : ConfigEntity?
    suspend fun getCoverImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : LogicEntity?
    suspend fun getPageContentFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContentEntity?
    suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?

    suspend fun insertReadRecord(readRecord : ReadRecordEntity) : Long
    suspend fun getReadRecord(userId : String, readMode : String, bookId : String) : ReadRecordEntity?
    suspend fun updateReadRecord(readRecord : ReadRecordEntity)
    suspend fun updateReadRecordSavePage(userId : String, readMode : String, bookId : String, savePageId : Long)
    suspend fun deleteReadRecordFromId(userId : String, readMode : String, bookId : String)

    suspend fun insertCountRecord(countRecord : CountRecordEntity) : Long
    suspend fun isCountRecordExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long) : Int?
    suspend fun incrementCountRecord(userId : String, readMode : String, bookId : String, elementId : Long)
    suspend fun deleteCountRecordFromBookId(userId : String, readMode : String, bookId : String)
}