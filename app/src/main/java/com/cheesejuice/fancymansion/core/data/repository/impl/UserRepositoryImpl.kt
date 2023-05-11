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
    = recordDatabaseSource.insertUserEntity(userEntity)
    override suspend fun isUserEntityExist(userId : String) : Boolean
    = recordDatabaseSource.isUserEntityExist(userId)

    override suspend fun insertReadEntity(readEntity : ReadEntity) : Long
    = recordDatabaseSource.insertReadEntity(readEntity)
    override suspend fun getReadEntity(userId : String, readMode : String, bookId : String) : ReadEntity?
    = recordDatabaseSource.getReadEntity(userId, readMode, bookId)
    override suspend fun updateReadEntity(readEntity : ReadEntity)
    = recordDatabaseSource.updateReadEntity(readEntity)
    override suspend fun deleteReadEntityFromId(userId : String, readMode : String, bookId : String)
    = recordDatabaseSource.deleteReadEntityFromId(userId, readMode, bookId)

    override suspend fun insertCountEntity(countEntity : CountEntity) : Long
    = recordDatabaseSource.insertCountEntity(countEntity)
    override suspend fun isCountEntityExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    = recordDatabaseSource.isCountEntityExist(userId, readMode, bookId, elementId)
    override suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long) : Int?
    = recordDatabaseSource.getElementCount(userId, readMode, bookId, elementId)
    override suspend fun incrementCountEntity(userId : String, readMode : String, bookId : String, elementId : Long)
    = recordDatabaseSource.incrementCountEntity(userId, readMode, bookId, elementId)
    override suspend fun deleteCountEntityFromBookId(userId : String, readMode : String, bookId : String)
    = recordDatabaseSource.deleteCountEntityFromBookId(userId, readMode, bookId)
}