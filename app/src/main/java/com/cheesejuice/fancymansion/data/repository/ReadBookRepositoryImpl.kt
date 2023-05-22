package com.cheesejuice.fancymansion.data.repository

import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.fancymansion.domain.interfaceRepository.ReadBookRepository
import com.cheesejuice.fancymansion.data.interfaceDatasource.RecordLocalDatabaseSource
import com.cheesejuice.fancymansion.data.interfaceDatasource.BookLocalStorageSource
import com.cheesejuice.fancymansion.data.mapper.book.asEntity
import com.cheesejuice.fancymansion.data.mapper.user.asEntity
import com.cheesejuice.fancymansion.data.mapper.user.asMapper
import com.cheesejuice.fancymansion.di.diDatasource.AppStorage
import com.cheesejuice.fancymansion.di.diDatasource.RoomDatabase
import com.cheesejuice.fancymansion.domain.entity.readbook.book.ConfigEntity
import com.cheesejuice.fancymansion.domain.entity.readbook.book.LogicEntity
import com.cheesejuice.fancymansion.domain.entity.readbook.book.PageContentEntity
import com.cheesejuice.fancymansion.domain.entity.readbook.record.CountRecordEntity
import com.cheesejuice.fancymansion.domain.entity.readbook.record.ReadRecordEntity
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReadBookRepositoryImpl @Inject constructor(
    @AppStorage private val bookStorageSource : BookLocalStorageSource,
    @RoomDatabase private val recordDatabaseSource : RecordLocalDatabaseSource
) : ReadBookRepository {

    override suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : ConfigEntity?
    = bookStorageSource.getConfigFromFile(userId, readMode, bookId)?.asEntity()
    override suspend fun getCoverFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    = bookStorageSource.getCoverImageFromFile(userId, readMode, bookId, image)
    override suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : LogicEntity?
    = bookStorageSource.getLogicFromFile(userId, readMode, bookId)?.asEntity()
    override suspend fun getPageFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContentEntity?
    = bookStorageSource.getPageContentFromFile(userId, readMode, bookId, pageId)?.asEntity()
    override suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    = bookStorageSource.getImageFromFile(userId, readMode, bookId, image)

    override suspend fun insertReadEntity(readRecordMapper : ReadRecordEntity) : Long
        = recordDatabaseSource.insertReadRecord(readRecordMapper.asMapper())
    override suspend fun getReadEntity(userId : String, readMode : String, bookId : String) : ReadRecordEntity?
        = recordDatabaseSource.getReadRecord(userId, readMode, bookId)?.asEntity()
    override suspend fun updateReadEntity(readRecordMapper : ReadRecordEntity)
        = recordDatabaseSource.updateReadRecord(readRecordMapper.asMapper())
    override suspend fun deleteReadEntityFromId(userId : String, readMode : String, bookId : String)
        = recordDatabaseSource.deleteReadRecordFromId(userId, readMode, bookId)

    override suspend fun insertCountEntity(countRecordMapper : CountRecordEntity) : Long
        = recordDatabaseSource.insertCountRecord(countRecordMapper.asMapper())
    override suspend fun isCountEntityExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
        = recordDatabaseSource.isCountRecordExist(userId, readMode, bookId, elementId)
    override suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long) : Int?
        = recordDatabaseSource.getElementCount(userId, readMode, bookId, elementId)
    override suspend fun incrementCountEntity(userId : String, readMode : String, bookId : String, elementId : Long)
        = recordDatabaseSource.incrementCountRecord(userId, readMode, bookId, elementId)
    override suspend fun deleteCountEntityFromBookId(userId : String, readMode : String, bookId : String)
        = recordDatabaseSource.deleteCountRecordFromBookId(userId, readMode, bookId)
}