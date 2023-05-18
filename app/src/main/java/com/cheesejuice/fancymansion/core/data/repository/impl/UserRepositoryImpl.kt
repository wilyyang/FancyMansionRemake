package com.cheesejuice.fancymansion.core.data.repository.impl

import com.cheesejuice.fancymansion.core.data.repository.UserRepository
import com.cheesejuice.fancymansion.core.data.source.local_database.RecordLocalDatabaseSource
import com.cheesejuice.fancymansion.core.data.source.local_database.di.RoomDatabase
import com.cheesejuice.fancymansion.core.entity.user.CountEntity
import com.cheesejuice.fancymansion.core.entity.user.ReadEntity
import com.cheesejuice.fancymansion.core.entity.user.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    @RoomDatabase private val recordDatabaseSource : RecordLocalDatabaseSource
) : UserRepository {

    /**
     * Room Database
     */
    override suspend fun insertUserEntity(userEntity : UserEntity) : Long
    = recordDatabaseSource.insertUserInfo(userEntity)
    override suspend fun isUserEntityExist(userId : String) : Boolean
    = recordDatabaseSource.isUserInfoExist(userId)

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