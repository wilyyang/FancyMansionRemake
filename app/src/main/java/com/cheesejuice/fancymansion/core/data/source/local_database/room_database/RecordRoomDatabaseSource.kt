package com.cheesejuice.fancymansion.core.data.source.local_database.room_database

import com.cheesejuice.fancymansion.core.data.source.local_database.RecordLocalDatabaseSource
import com.cheesejuice.fancymansion.core.data.source.local_database.model.asData
import com.cheesejuice.fancymansion.core.data.source.local_database.model.asMapper
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
    override suspend fun insertUserInfo(userInfo : UserEntity) : Long = databaseDao.insertUserInfo(userInfo.asData())

    // Get
    override suspend fun isUserInfoExist(userId : String) : Boolean = databaseDao.isUserInfoExist(userId)

    override suspend fun getUserInfo(userId : String) : UserEntity? = databaseDao.getUserInfo(userId)?.asMapper()

    // Update
    override suspend fun updateUserInfo(userInfo : UserEntity) = databaseDao.updateUserInfo(userInfo.asData())

    // Delete
    override suspend fun deleteUserInfo(userInfo : UserEntity) = databaseDao.deleteUserInfo(userInfo.asData())

    override suspend fun deleteUserInfoFromId(userId : String) = databaseDao.deleteUserInfoFromId(userId)

    /**
     * Read
     */
    // Insert
    override suspend fun insertReadRecord(readRecord : ReadEntity) : Long = databaseDao.insertReadRecord(readRecord.asData())

    // Get
    override suspend fun isReadRecordExist(userId : String, readMode : String, bookId : String) : Boolean =
        databaseDao.isReadRecordExist(userId, readMode, bookId)

    override suspend fun getReadRecord(userId : String, readMode : String, bookId : String) : ReadEntity? =
        databaseDao.getReadRecord(userId, readMode, bookId)?.asMapper()

    // Update
    override suspend fun updateReadRecord(readRecord : ReadEntity) = databaseDao.updateReadRecord(readRecord.asData())

    // Delete
    override suspend fun deleteReadRecord(readRecord : ReadEntity) = databaseDao.deleteReadRecord(readRecord.asData())

    override suspend fun deleteReadRecordFromUserId(userId : String) = databaseDao.deleteReadRecordFromUserId(userId)

    override suspend fun deleteReadRecordFromId(userId : String, readMode : String, bookId : String) =
        databaseDao.deleteReadRecordFromId(userId, readMode, bookId)

    /**
     * Count
     */
    // Insert
    override suspend fun insertCountRecord(countRecord : CountEntity) : Long = databaseDao.insertCountRecord(countRecord.asData())

    // Get
    override suspend fun isCountRecordExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean =
        databaseDao.isCountRecordExist(userId, readMode, bookId, elementId)

    override suspend fun getCountRecord(userId : String, readMode : String, bookId : String, elementId : Long) : CountEntity? =
        databaseDao.getCountRecord(userId, readMode, bookId, elementId)?.asMapper()

    override suspend fun getCountRecordList(userId : String, readMode : String, bookId : String) : List<CountEntity> =
        databaseDao.getCountRecordList(userId, readMode, bookId).map { it.asMapper() }

    override suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long) : Int? =
        databaseDao.getElementCount(userId, readMode, bookId, elementId)

    // Update
    override suspend fun updateCountRecord(countRecord : CountEntity) = databaseDao.updateCountRecord(countRecord.asData())

    override suspend fun incrementCountRecord(userId : String, readMode : String, bookId : String, elementId : Long) =
        databaseDao.incrementCountRecord(userId, readMode, bookId, elementId)

    // Delete
    override suspend fun deleteCountRecord(countRecord : CountEntity) = databaseDao.deleteCountRecord(countRecord.asData())

    override suspend fun deleteCountRecordFromUserId(userId : String) = databaseDao.deleteCountRecordFromUserId(userId)

    override suspend fun deleteCountRecordFromBookId(userId : String, readMode : String, bookId : String) =
        databaseDao.deleteCountRecordFromBookId(userId, readMode, bookId)

    override suspend fun deleteCountRecordFromId(userId : String, readMode : String, bookId : String, elementId : Long) =
        databaseDao.deleteCountRecordFromId(userId, readMode, bookId, elementId)
}