package com.cheesejuice.fancymansion.core.data.repository.impl

import com.cheesejuice.fancymansion.core.data.repository.UserRepository
import com.cheesejuice.fancymansion.core.data.source.local_database.LocalDatabaseSource
import com.cheesejuice.fancymansion.core.data.source.local_database.di.RoomDatabase
import com.cheesejuice.fancymansion.core.entity.user.CountEntity
import com.cheesejuice.fancymansion.core.entity.user.ReadEntity
import com.cheesejuice.fancymansion.core.entity.user.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    @RoomDatabase private val databaseSource : LocalDatabaseSource
) : UserRepository {

    /**
     * Room Database
     */
    override suspend fun insertUserEntity(userEntity : UserEntity) : Long
    = databaseSource.insertUserEntity(userEntity)
    override suspend fun isUserEntityExist(userId : String) : Boolean
    = databaseSource.isUserEntityExist(userId)

    override suspend fun insertReadEntity(readEntity : ReadEntity) : Long
    = databaseSource.insertReadEntity(readEntity)
    override suspend fun getReadEntity(userId : String, readMode : String, bookId : String) : ReadEntity?
    = databaseSource.getReadEntity(userId, readMode, bookId)
    override suspend fun updateReadEntity(readEntity : ReadEntity)
    = databaseSource.updateReadEntity(readEntity)
    override suspend fun deleteReadEntityFromId(userId : String, readMode : String, bookId : String)
    = databaseSource.deleteReadEntityFromId(userId, readMode, bookId)

    override suspend fun insertCountEntity(countEntity : CountEntity) : Long
    = databaseSource.insertCountEntity(countEntity)
    override suspend fun isCountEntityExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    = databaseSource.isCountEntityExist(userId, readMode, bookId, elementId)
    override suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long) : Int?
    = databaseSource.getElementCount(userId, readMode, bookId, elementId)
    override suspend fun incrementCountEntity(userId : String, readMode : String, bookId : String, elementId : Long)
    = databaseSource.incrementCountEntity(userId, readMode, bookId, elementId)
    override suspend fun deleteCountEntityFromBookId(userId : String, readMode : String, bookId : String)
    = databaseSource.deleteCountEntityFromBookId(userId, readMode, bookId)
}