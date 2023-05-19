package com.cheesejuice.fancymansion.data.interfaceDatasource

import com.cheesejuice.fancymansion.data.mapper.user.CountRecordMapper
import com.cheesejuice.fancymansion.data.mapper.user.ReadRecordMapper
import com.cheesejuice.fancymansion.data.mapper.user.UserInfoMapper

interface RecordLocalDatabaseSource {
    /**
     * User
     */
    // Insert
    suspend fun insertUserInfo(userInfo : UserInfoMapper) : Long

    // Get
    suspend fun isUserInfoExist(userId : String) : Boolean
    suspend fun getUserInfo(userId : String) : UserInfoMapper?

    // Update
    suspend fun updateUserInfo(userInfo : UserInfoMapper)

    // Delete
    suspend fun deleteUserInfo(userInfo : UserInfoMapper)
    suspend fun deleteUserInfoFromId(userId : String)

    /**
     * Read
     */
    // Insert
    suspend fun insertReadRecord(readRecord : ReadRecordMapper) : Long

    // Get
    suspend fun isReadRecordExist(userId : String, readMode : String, bookId : String) : Boolean
    suspend fun getReadRecord(userId : String, readMode : String, bookId : String) : ReadRecordMapper?

    // Update
    suspend fun updateReadRecord(readRecord : ReadRecordMapper)

    // Delete
    suspend fun deleteReadRecord(readRecord : ReadRecordMapper)
    suspend fun deleteReadRecordFromUserId(userId : String)
    suspend fun deleteReadRecordFromId(userId : String, readMode : String, bookId : String)

    /**
     * Count
     */
    // Insert
    suspend fun insertCountRecord(countRecord : CountRecordMapper) : Long

    // Get
    suspend fun isCountRecordExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    suspend fun getCountRecord(userId : String, readMode : String, bookId : String, elementId : Long) : CountRecordMapper?
    suspend fun getCountRecordList(userId : String, readMode : String, bookId : String) : List<CountRecordMapper>
    suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long) : Int?

    // Update
    suspend fun updateCountRecord(countRecord : CountRecordMapper)
    suspend fun incrementCountRecord(userId : String, readMode : String, bookId : String, elementId : Long)

    // Delete
    suspend fun deleteCountRecord(countRecord : CountRecordMapper)
    suspend fun deleteCountRecordFromUserId(userId : String)
    suspend fun deleteCountRecordFromBookId(userId : String, readMode : String, bookId : String)
    suspend fun deleteCountRecordFromId(userId : String, readMode : String, bookId : String, elementId : Long)
}