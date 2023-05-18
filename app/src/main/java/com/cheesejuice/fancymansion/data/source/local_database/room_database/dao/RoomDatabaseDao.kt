package com.cheesejuice.fancymansion.data.source.local_database.room_database.dao

import androidx.room.*
import com.cheesejuice.fancymansion.data.source.local_database.model.*

@Dao
interface RoomDatabaseDao {
    /**
     * UserInfoDao
     */

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserInfo(userInfo: UserInfoData) : Long

    // Get
    @Query("SELECT EXISTS(SELECT * FROM UserInfo WHERE userId = :userId)")
    suspend fun isUserInfoExist(userId : String) : Boolean
    @Query("SELECT * FROM UserInfo WHERE userId = :userId")
    suspend fun getUserInfo(userId : String): UserInfoData?

    // Update
    @Update
    suspend fun updateUserInfo(userInfo: UserInfoData)

    // Delete
    @Delete
    suspend fun deleteUserInfo(userInfo: UserInfoData)
    @Query("DELETE FROM UserInfo WHERE userId = :userId")
    suspend fun deleteUserInfoFromId(userId : String)


    /**
     * ReadRecordDao
     */

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReadRecord(readRecord: ReadRecordData) : Long

    // Get
    @Query("SELECT EXISTS(SELECT * FROM ReadRecord WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId)")
    suspend fun isReadRecordExist(userId : String, readMode : String, bookId : String) : Boolean
    @Query("SELECT * FROM ReadRecord WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId")
    suspend fun getReadRecord(userId : String, readMode : String, bookId : String): ReadRecordData?

    // Update
    @Update
    suspend fun updateReadRecord(readRecord: ReadRecordData)

    // Delete
    @Delete
    suspend fun deleteReadRecord(readRecord: ReadRecordData)
    @Query("DELETE FROM ReadRecord WHERE userId = :userId")
    suspend fun deleteReadRecordFromUserId(userId : String)
    @Query("DELETE FROM ReadRecord WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId")
    suspend fun deleteReadRecordFromId(userId : String, readMode : String, bookId : String)

    /**
     * CountRecordDao
     */

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountRecord(countRecord: CountRecordData) : Long

    // Get
    @Query("SELECT EXISTS(SELECT * FROM CountRecord WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId)")
    suspend fun isCountRecordExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    @Query("SELECT * FROM CountRecord WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId")
    suspend fun getCountRecord(userId : String, readMode : String, bookId : String, elementId : Long): CountRecordData?
    @Query("SELECT * FROM CountRecord WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId")
    suspend fun getCountRecordList(userId : String, readMode : String, bookId : String): List<CountRecordData>
    @Query("SELECT count FROM CountRecord WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId")
    suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long): Int?

    // Update
    @Update
    suspend fun updateCountRecord(countRecord: CountRecordData)
    @Query("UPDATE CountRecord SET count = count + 1 WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId")
    suspend fun incrementCountRecord(userId : String, readMode : String, bookId : String, elementId : Long)

    // Delete
    @Delete
    suspend fun deleteCountRecord(countRecord: CountRecordData)
    @Query("DELETE FROM CountRecord WHERE userId = :userId")
    suspend fun deleteCountRecordFromUserId(userId : String)
    @Query("DELETE FROM CountRecord WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId")
    suspend fun deleteCountRecordFromBookId(userId : String, readMode : String, bookId : String)
    @Query("DELETE FROM CountRecord WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId")
    suspend fun deleteCountRecordFromId(userId : String, readMode : String, bookId : String, elementId : Long)
}