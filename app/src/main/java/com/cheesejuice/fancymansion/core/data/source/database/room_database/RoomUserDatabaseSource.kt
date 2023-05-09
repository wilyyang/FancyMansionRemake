package com.cheesejuice.fancymansion.core.data.source.database.room_database

import com.cheesejuice.fancymansion.core.data.source.database.UserDatabaseSource
import com.cheesejuice.fancymansion.core.data.source.database.model.asData
import com.cheesejuice.fancymansion.core.data.source.database.model.asEntity
import com.cheesejuice.fancymansion.core.data.source.database.room_database.dao.RoomUserDatabaseDao
import com.cheesejuice.fancymansion.core.entity.user.CountEntity
import com.cheesejuice.fancymansion.core.entity.user.ReadEntity
import com.cheesejuice.fancymansion.core.entity.user.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomUserDatabaseSource @Inject constructor(private val storageDao : RoomUserDatabaseDao) : UserDatabaseSource {

    /**
     * User
     */
    // Insert
    override suspend fun insertUserEntity(userEntity: UserEntity) : Long
    = storageDao.insertUserData(userEntity.asData())

    // Get
    override suspend fun isUserEntityExist(userId : String) : Boolean
    = storageDao.isUserDataExist(userId)

    override suspend fun getUserEntity(userId : String): UserEntity?
    = storageDao.getUserData(userId)?.asEntity()

    // Update
    override suspend fun updateUserEntity(userEntity: UserEntity)
    = storageDao.updateUserData(userEntity.asData())

    // Delete
    override suspend fun deleteUserEntity(userEntity: UserEntity)
    = storageDao.deleteUserData(userEntity.asData())

    override suspend fun deleteUserEntityFromId(userId : String)
    = storageDao.deleteUserDataFromId(userId)


    /**
     * Read
     */
    // Insert
    override suspend fun insertReadEntity(readEntity: ReadEntity) : Long
    = storageDao.insertReadData(readEntity.asData())

    // Get
    override suspend fun isReadEntityExist(userId : String, readMode : String, bookId : String) : Boolean
    = storageDao.isReadDataExist(userId, readMode, bookId)

    override suspend fun getReadEntity(userId : String, readMode : String, bookId : String): ReadEntity?
    = storageDao.getReadData(userId, readMode, bookId)?.asEntity()

    // Update
    override suspend fun updateReadEntity(readEntity: ReadEntity)
    = storageDao.updateReadData(readEntity.asData())

    // Delete
    override suspend fun deleteReadEntity(readEntity: ReadEntity)
    = storageDao.deleteReadData(readEntity.asData())

    override suspend fun deleteReadEntityFromUserId(userId : String)
    = storageDao.deleteReadDataFromUserId(userId)

    override suspend fun deleteReadEntityFromId(userId : String, readMode : String, bookId : String)
    = storageDao.deleteReadDataFromId(userId, readMode, bookId)

    /**
     * Count
     */
    // Insert
    override suspend fun insertCountEntity(countEntity: CountEntity) : Long
    = storageDao.insertCountData(countEntity.asData())

    // Get
    override suspend fun isCountEntityExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    = storageDao.isCountDataExist(userId, readMode, bookId, elementId)

    override suspend fun getCountEntity(userId : String, readMode : String, bookId : String, elementId : Long): CountEntity?
    = storageDao.getCountData(userId, readMode, bookId, elementId)?.asEntity()

    override suspend fun getCountEntityList(userId : String, readMode : String, bookId : String): List<CountEntity>
    = storageDao.getCountDataList(userId, readMode, bookId).map { it.asEntity() }

    override suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long): Int?
    = storageDao.getElementCount(userId, readMode, bookId, elementId)

    // Update
    override suspend fun updateCountEntity(countEntity: CountEntity)
    = storageDao.updateCountData(countEntity.asData())

    override suspend fun incrementCountEntity(userId : String, readMode : String, bookId : String, elementId : Long)
    = storageDao.incrementCountData(userId, readMode, bookId, elementId)

    // Delete
    override suspend fun deleteCountEntity(countEntity: CountEntity)
    = storageDao.deleteCountData(countEntity.asData())

    override suspend fun deleteCountEntityFromUserId(userId : String)
    = storageDao.deleteCountDataFromUserId(userId)

    override suspend fun deleteCountEntityFromBookId(userId : String, readMode : String, bookId : String)
    = storageDao.deleteCountDataFromBookId(userId, readMode, bookId)

    override suspend fun deleteCountEntityFromId(userId : String, readMode : String, bookId : String, elementId : Long)
    = storageDao.deleteCountDataFromId(userId, readMode, bookId, elementId)
}