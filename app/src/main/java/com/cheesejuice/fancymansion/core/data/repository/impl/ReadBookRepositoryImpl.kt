package com.cheesejuice.fancymansion.core.data.repository.impl

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.data.repository.ReadBookRepository
import com.cheesejuice.fancymansion.core.data.source.local_database.RecordLocalDatabaseSource
import com.cheesejuice.fancymansion.core.data.source.local_database.di.RoomDatabase
import com.cheesejuice.fancymansion.core.data.source.local_storage.BookLocalStorageSource
import com.cheesejuice.fancymansion.core.data.source.local_storage.di.AppStorage
import com.cheesejuice.fancymansion.core.entity.book.ConfigEntity
import com.cheesejuice.fancymansion.core.entity.book.LogicEntity
import com.cheesejuice.fancymansion.core.entity.book.PageContentEntity
import com.cheesejuice.fancymansion.core.entity.user.CountEntity
import com.cheesejuice.fancymansion.core.entity.user.ReadEntity
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReadBookRepositoryImpl @Inject constructor(
    @AppStorage private val bookStorageSource : BookLocalStorageSource,
    @RoomDatabase private val recordDatabaseSource : RecordLocalDatabaseSource
) : ReadBookRepository {

    override suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : ConfigEntity?
    = bookStorageSource.getConfigFromFile(userId, readMode, bookId)
    override suspend fun getCoverFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    = bookStorageSource.getCoverImageFromFile(userId, readMode, bookId, image)
    override suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : LogicEntity?
    = bookStorageSource.getLogicFromFile(userId, readMode, bookId)
    override suspend fun getPageFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContentEntity?
    = bookStorageSource.getPageContentFromFile(userId, readMode, bookId, pageId)
    override suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    = bookStorageSource.getImageFromFile(userId, readMode, bookId, image)

    override suspend fun insertReadEntity(readEntity : ReadEntity) : Long
        = recordDatabaseSource.insertReadRecord(readEntity)
    override suspend fun getReadEntity(userId : String, readMode : String, bookId : String) : ReadEntity?
        = recordDatabaseSource.getReadRecord(userId, readMode, bookId)
    override suspend fun updateReadEntity(readEntity : ReadEntity)
        = recordDatabaseSource.updateReadRecord(readEntity)
    override suspend fun deleteReadEntityFromId(userId : String, readMode : String, bookId : String)
        = recordDatabaseSource.deleteReadRecordFromId(userId, readMode, bookId)

    override suspend fun insertCountEntity(countEntity : CountEntity) : Long
        = recordDatabaseSource.insertCountRecord(countEntity)
    override suspend fun isCountEntityExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
        = recordDatabaseSource.isCountRecordExist(userId, readMode, bookId, elementId)
    override suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long) : Int?
        = recordDatabaseSource.getElementCount(userId, readMode, bookId, elementId)
    override suspend fun incrementCountEntity(userId : String, readMode : String, bookId : String, elementId : Long)
        = recordDatabaseSource.incrementCountRecord(userId, readMode, bookId, elementId)
    override suspend fun deleteCountEntityFromBookId(userId : String, readMode : String, bookId : String)
        = recordDatabaseSource.deleteCountRecordFromBookId(userId, readMode, bookId)
}