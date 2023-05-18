package com.cheesejuice.fancymansion.core.data.source.local_database

import com.cheesejuice.fancymansion.core.entity.user.CountEntity
import com.cheesejuice.fancymansion.core.entity.user.ReadEntity
import com.cheesejuice.fancymansion.core.entity.user.UserEntity

interface RecordLocalDatabaseSource {
    /**
     * User
     */
    // Insert
    suspend fun insertUserInfo(userInfo : UserEntity) : Long

    // Get
    suspend fun isUserInfoExist(userId : String) : Boolean
    suspend fun getUserInfo(userId : String) : UserEntity?

    // Update
    suspend fun updateUserInfo(userInfo : UserEntity)

    // Delete
    suspend fun deleteUserInfo(userInfo : UserEntity)
    suspend fun deleteUserInfoFromId(userId : String)

    /**
     * Read
     */
    // Insert
    suspend fun insertReadRecord(readRecord : ReadEntity) : Long

    // Get
    suspend fun isReadRecordExist(userId : String, readMode : String, bookId : String) : Boolean
    suspend fun getReadRecord(userId : String, readMode : String, bookId : String) : ReadEntity?

    // Update
    suspend fun updateReadRecord(readRecord : ReadEntity)

    // Delete
    suspend fun deleteReadRecord(readRecord : ReadEntity)
    suspend fun deleteReadRecordFromUserId(userId : String)
    suspend fun deleteReadRecordFromId(userId : String, readMode : String, bookId : String)

    /**
     * Count
     */
    // Insert
    suspend fun insertCountRecord(countRecord : CountEntity) : Long

    // Get
    suspend fun isCountRecordExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    suspend fun getCountRecord(userId : String, readMode : String, bookId : String, elementId : Long) : CountEntity?
    suspend fun getCountRecordList(userId : String, readMode : String, bookId : String) : List<CountEntity>
    suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long) : Int?

    // Update
    suspend fun updateCountRecord(countRecord : CountEntity)
    suspend fun incrementCountRecord(userId : String, readMode : String, bookId : String, elementId : Long)

    // Delete
    suspend fun deleteCountRecord(countRecord : CountEntity)
    suspend fun deleteCountRecordFromUserId(userId : String)
    suspend fun deleteCountRecordFromBookId(userId : String, readMode : String, bookId : String)
    suspend fun deleteCountRecordFromId(userId : String, readMode : String, bookId : String, elementId : Long)
}