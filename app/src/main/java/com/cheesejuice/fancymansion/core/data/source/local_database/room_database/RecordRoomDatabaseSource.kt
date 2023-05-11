package com.cheesejuice.fancymansion.core.data.source.local_database.room_database

import com.cheesejuice.fancymansion.core.data.source.local_database.RecordLocalDatabaseSource
import com.cheesejuice.fancymansion.core.data.source.local_database.model.asData
import com.cheesejuice.fancymansion.core.data.source.local_database.model.asEntity
import com.cheesejuice.fancymansion.core.data.source.local_database.room_database.dao.RoomDatabaseDao
import com.cheesejuice.fancymansion.core.entity.user.CountEntity
import com.cheesejuice.fancymansion.core.entity.user.ReadEntity
import com.cheesejuice.fancymansion.core.entity.user.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecordRoomDatabaseSource @Inject constructor(private val databaseDao : RoomDatabaseDao) : RecordLocalDatabaseSource {

    /**
     * User
     */
    // Insert
    override suspend fun insertUserEntity(userEntity: UserEntity) : Long
    = databaseDao.insertUserData(userEntity.asData())

    // Get
    override suspend fun isUserEntityExist(userId : String) : Boolean
    = databaseDao.isUserDataExist(userId)

    override suspend fun getUserEntity(userId : String): UserEntity?
    = databaseDao.getUserData(userId)?.asEntity()

    // Update
    override suspend fun updateUserEntity(userEntity: UserEntity)
    = databaseDao.updateUserData(userEntity.asData())

    // Delete
    override suspend fun deleteUserEntity(userEntity: UserEntity)
    = databaseDao.deleteUserData(userEntity.asData())

    override suspend fun deleteUserEntityFromId(userId : String)
    = databaseDao.deleteUserDataFromId(userId)


    /**
     * Read
     */
    // Insert
    override suspend fun insertReadEntity(readEntity: ReadEntity) : Long
    = databaseDao.insertReadData(readEntity.asData())

    // Get
    override suspend fun isReadEntityExist(userId : String, readMode : String, bookId : String) : Boolean
    = databaseDao.isReadDataExist(userId, readMode, bookId)

    override suspend fun getReadEntity(userId : String, readMode : String, bookId : String): ReadEntity?
    = databaseDao.getReadData(userId, readMode, bookId)?.asEntity()

    // Update
    override suspend fun updateReadEntity(readEntity: ReadEntity)
    = databaseDao.updateReadData(readEntity.asData())

    // Delete
    override suspend fun deleteReadEntity(readEntity: ReadEntity)
    = databaseDao.deleteReadData(readEntity.asData())

    override suspend fun deleteReadEntityFromUserId(userId : String)
    = databaseDao.deleteReadDataFromUserId(userId)

    override suspend fun deleteReadEntityFromId(userId : String, readMode : String, bookId : String)
    = databaseDao.deleteReadDataFromId(userId, readMode, bookId)

    /**
     * Count
     */
    // Insert
    override suspend fun insertCountEntity(countEntity: CountEntity) : Long
    = databaseDao.insertCountData(countEntity.asData())

    // Get
    override suspend fun isCountEntityExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    = databaseDao.isCountDataExist(userId, readMode, bookId, elementId)

    override suspend fun getCountEntity(userId : String, readMode : String, bookId : String, elementId : Long): CountEntity?
    = databaseDao.getCountData(userId, readMode, bookId, elementId)?.asEntity()

    override suspend fun getCountEntityList(userId : String, readMode : String, bookId : String): List<CountEntity>
    = databaseDao.getCountDataList(userId, readMode, bookId).map { it.asEntity() }

    override suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long): Int?
    = databaseDao.getElementCount(userId, readMode, bookId, elementId)

    // Update
    override suspend fun updateCountEntity(countEntity: CountEntity)
    = databaseDao.updateCountData(countEntity.asData())

    override suspend fun incrementCountEntity(userId : String, readMode : String, bookId : String, elementId : Long)
    = databaseDao.incrementCountData(userId, readMode, bookId, elementId)

    // Delete
    override suspend fun deleteCountEntity(countEntity: CountEntity)
    = databaseDao.deleteCountData(countEntity.asData())

    override suspend fun deleteCountEntityFromUserId(userId : String)
    = databaseDao.deleteCountDataFromUserId(userId)

    override suspend fun deleteCountEntityFromBookId(userId : String, readMode : String, bookId : String)
    = databaseDao.deleteCountDataFromBookId(userId, readMode, bookId)

    override suspend fun deleteCountEntityFromId(userId : String, readMode : String, bookId : String, elementId : Long)
    = databaseDao.deleteCountDataFromId(userId, readMode, bookId, elementId)
}