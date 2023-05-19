package com.cheesejuice.fancymansion.domain.interfaceRepository

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.data.mapper.book.ConfigMapper
import com.cheesejuice.fancymansion.data.mapper.book.LogicMapper
import com.cheesejuice.fancymansion.data.mapper.book.PageContentMapper
import com.cheesejuice.fancymansion.data.mapper.user.CountRecordMapper
import com.cheesejuice.fancymansion.data.mapper.user.ReadRecordMapper
import java.io.File

interface ReadBookRepository {
    suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : ConfigMapper?
    suspend fun getCoverFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : LogicMapper?
    suspend fun getPageFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContentMapper?
    suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?

    suspend fun insertReadEntity(readRecordMapper : ReadRecordMapper) : Long
    suspend fun getReadEntity(userId : String, readMode : String, bookId : String) : ReadRecordMapper?
    suspend fun updateReadEntity(readRecordMapper : ReadRecordMapper)
    suspend fun deleteReadEntityFromId(userId : String, readMode : String, bookId : String)

    suspend fun insertCountEntity(countRecordMapper : CountRecordMapper) : Long
    suspend fun isCountEntityExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long) : Int?
    suspend fun incrementCountEntity(userId : String, readMode : String, bookId : String, elementId : Long)
    suspend fun deleteCountEntityFromBookId(userId : String, readMode : String, bookId : String)
}