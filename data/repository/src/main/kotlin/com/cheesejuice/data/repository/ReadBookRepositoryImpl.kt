package com.cheesejuice.data.repository

import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.data.interfaceDatasource.BookLocalStorageSource
import com.cheesejuice.data.interfaceDatasource.RecordLocalDatabaseSource
import com.cheesejuice.data.mapper.book.asEntity
import com.cheesejuice.data.mapper.user.asEntity
import com.cheesejuice.data.mapper.user.asMapper
import com.cheesejuice.domain.entity.readbook.book.ConfigEntity
import com.cheesejuice.domain.entity.readbook.book.LogicEntity
import com.cheesejuice.domain.entity.readbook.book.PageContentEntity
import com.cheesejuice.domain.entity.readbook.record.CountRecordEntity
import com.cheesejuice.domain.entity.readbook.record.ReadRecordEntity
import com.cheesejuice.domain.interfaceRepository.ReadBookRepository
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReadBookRepositoryImpl @Inject constructor(
    private val bookStorageSource : BookLocalStorageSource,
    private val recordDatabaseSource : RecordLocalDatabaseSource
) : ReadBookRepository {

    override suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : ConfigEntity?
    = bookStorageSource.getConfigFromFile(userId, readMode, bookId)?.asEntity()
    override suspend fun getCoverImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    = bookStorageSource.getCoverImageFromFile(userId, readMode, bookId, image)
    override suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : LogicEntity?
    = bookStorageSource.getLogicFromFile(userId, readMode, bookId)?.asEntity()
    override suspend fun getPageContentFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContentEntity?
    = bookStorageSource.getPageContentFromFile(userId, readMode, bookId, pageId)?.asEntity()
    override suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    = bookStorageSource.getImageFromFile(userId, readMode, bookId, image)

    override suspend fun insertReadRecord(readRecord : ReadRecordEntity) : Long
        = recordDatabaseSource.insertReadRecord(readRecord.asMapper())
    override suspend fun getReadRecord(userId : String, readMode : String, bookId : String) : ReadRecordEntity?
        = recordDatabaseSource.getReadRecord(userId, readMode, bookId)?.asEntity()
    override suspend fun updateReadRecord(readRecord : ReadRecordEntity)
        = recordDatabaseSource.updateReadRecord(readRecord.asMapper())
    override suspend fun deleteReadRecordFromId(userId : String, readMode : String, bookId : String)
        = recordDatabaseSource.deleteReadRecordFromId(userId, readMode, bookId)

    override suspend fun insertCountRecord(countRecord : CountRecordEntity) : Long
        = recordDatabaseSource.insertCountRecord(countRecord.asMapper())
    override suspend fun isCountRecordExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
        = recordDatabaseSource.isCountRecordExist(userId, readMode, bookId, elementId)
    override suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long) : Int?
        = recordDatabaseSource.getElementCount(userId, readMode, bookId, elementId)
    override suspend fun incrementCountRecord(userId : String, readMode : String, bookId : String, elementId : Long)
        = recordDatabaseSource.incrementCountRecord(userId, readMode, bookId, elementId)
    override suspend fun deleteCountRecordFromBookId(userId : String, readMode : String, bookId : String)
        = recordDatabaseSource.deleteCountRecordFromBookId(userId, readMode, bookId)
}