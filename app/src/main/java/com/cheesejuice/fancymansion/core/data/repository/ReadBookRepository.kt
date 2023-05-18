package com.cheesejuice.fancymansion.core.data.repository

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.entity.book.ConfigEntity
import com.cheesejuice.fancymansion.core.entity.book.LogicEntity
import com.cheesejuice.fancymansion.core.entity.book.PageContentEntity
import com.cheesejuice.fancymansion.core.entity.user.CountEntity
import com.cheesejuice.fancymansion.core.entity.user.ReadEntity
import java.io.File

interface ReadBookRepository {
    suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : ConfigEntity?
    suspend fun getCoverFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : LogicEntity?
    suspend fun getPageFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContentEntity?
    suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?

    suspend fun insertReadEntity(readEntity : ReadEntity) : Long
    suspend fun getReadEntity(userId : String, readMode : String, bookId : String) : ReadEntity?
    suspend fun updateReadEntity(readEntity : ReadEntity)
    suspend fun deleteReadEntityFromId(userId : String, readMode : String, bookId : String)

    suspend fun insertCountEntity(countEntity : CountEntity) : Long
    suspend fun isCountEntityExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long) : Int?
    suspend fun incrementCountEntity(userId : String, readMode : String, bookId : String, elementId : Long)
    suspend fun deleteCountEntityFromBookId(userId : String, readMode : String, bookId : String)
}