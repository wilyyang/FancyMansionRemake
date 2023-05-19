package com.cheesejuice.fancymansion.data.repository

import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.fancymansion.domain.interfaceRepository.ReadBookRepository
import com.cheesejuice.fancymansion.data.mapper.book.ConfigMapper
import com.cheesejuice.fancymansion.data.mapper.book.LogicMapper
import com.cheesejuice.fancymansion.data.mapper.book.PageContentMapper
import com.cheesejuice.fancymansion.data.mapper.user.CountRecordMapper
import com.cheesejuice.fancymansion.data.mapper.user.ReadRecordMapper
import com.cheesejuice.fancymansion.data.interfaceDatasource.RecordLocalDatabaseSource
import com.cheesejuice.fancymansion.data.interfaceDatasource.BookLocalStorageSource
import com.cheesejuice.fancymansion.di.diDatasource.AppStorage
import com.cheesejuice.fancymansion.di.diDatasource.RoomDatabase
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReadBookRepositoryImpl @Inject constructor(
    @AppStorage private val bookStorageSource : BookLocalStorageSource,
    @RoomDatabase private val recordDatabaseSource : RecordLocalDatabaseSource
) : ReadBookRepository {

    override suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : ConfigMapper?
    = bookStorageSource.getConfigFromFile(userId, readMode, bookId)
    override suspend fun getCoverFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    = bookStorageSource.getCoverImageFromFile(userId, readMode, bookId, image)
    override suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : LogicMapper?
    = bookStorageSource.getLogicFromFile(userId, readMode, bookId)
    override suspend fun getPageFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContentMapper?
    = bookStorageSource.getPageContentFromFile(userId, readMode, bookId, pageId)
    override suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    = bookStorageSource.getImageFromFile(userId, readMode, bookId, image)

    override suspend fun insertReadEntity(readRecordMapper : ReadRecordMapper) : Long
        = recordDatabaseSource.insertReadRecord(readRecordMapper)
    override suspend fun getReadEntity(userId : String, readMode : String, bookId : String) : ReadRecordMapper?
        = recordDatabaseSource.getReadRecord(userId, readMode, bookId)
    override suspend fun updateReadEntity(readRecordMapper : ReadRecordMapper)
        = recordDatabaseSource.updateReadRecord(readRecordMapper)
    override suspend fun deleteReadEntityFromId(userId : String, readMode : String, bookId : String)
        = recordDatabaseSource.deleteReadRecordFromId(userId, readMode, bookId)

    override suspend fun insertCountEntity(countRecordMapper : CountRecordMapper) : Long
        = recordDatabaseSource.insertCountRecord(countRecordMapper)
    override suspend fun isCountEntityExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
        = recordDatabaseSource.isCountRecordExist(userId, readMode, bookId, elementId)
    override suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long) : Int?
        = recordDatabaseSource.getElementCount(userId, readMode, bookId, elementId)
    override suspend fun incrementCountEntity(userId : String, readMode : String, bookId : String, elementId : Long)
        = recordDatabaseSource.incrementCountRecord(userId, readMode, bookId, elementId)
    override suspend fun deleteCountEntityFromBookId(userId : String, readMode : String, bookId : String)
        = recordDatabaseSource.deleteCountRecordFromBookId(userId, readMode, bookId)
}