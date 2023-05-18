package com.cheesejuice.fancymansion.core.data.source.local_database.room_database.dao

import androidx.room.*
import com.cheesejuice.fancymansion.core.data.source.local_database.model.*

@Dao
interface RoomDatabaseDao {
    /**
     * UserDataDao
     */

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(userInfo: UserInfoData) : Long

    // Get
    @Query("SELECT EXISTS(SELECT * FROM UserData WHERE userId = :userId)")
    suspend fun isUserDataExist(userId : String) : Boolean
    @Query("SELECT * FROM UserData WHERE userId = :userId")
    suspend fun getUserData(userId : String): UserInfoData?

    // Update
    @Update
    suspend fun updateUserData(userInfo: UserInfoData)

    // Delete
    @Delete
    suspend fun deleteUserData(userInfo: UserInfoData)
    @Query("DELETE FROM UserData WHERE userId = :userId")
    suspend fun deleteUserDataFromId(userId : String)


    /**
     * ReadDataDao
     */

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReadData(readRecord: ReadRecordData) : Long

    // Get
    @Query("SELECT EXISTS(SELECT * FROM ReadData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId)")
    suspend fun isReadDataExist(userId : String, readMode : String, bookId : String) : Boolean
    @Query("SELECT * FROM ReadData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId")
    suspend fun getReadData(userId : String, readMode : String, bookId : String): ReadRecordData?

    // Update
    @Update
    suspend fun updateReadData(readRecord: ReadRecordData)

    // Delete
    @Delete
    suspend fun deleteReadData(readRecord: ReadRecordData)
    @Query("DELETE FROM ReadData WHERE userId = :userId")
    suspend fun deleteReadDataFromUserId(userId : String)
    @Query("DELETE FROM ReadData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId")
    suspend fun deleteReadDataFromId(userId : String, readMode : String, bookId : String)

    /**
     * CountDataDao
     */

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountData(countRecord: CountRecordData) : Long

    // Get
    @Query("SELECT EXISTS(SELECT * FROM CountData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId)")
    suspend fun isCountDataExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    @Query("SELECT * FROM CountData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId")
    suspend fun getCountData(userId : String, readMode : String, bookId : String, elementId : Long): CountRecordData?
    @Query("SELECT * FROM CountData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId")
    suspend fun getCountDataList(userId : String, readMode : String, bookId : String): List<CountRecordData>
    @Query("SELECT count FROM CountData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId")
    suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long): Int?

    // Update
    @Update
    suspend fun updateCountData(countRecord: CountRecordData)
    @Query("UPDATE CountData SET count = count + 1 WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId")
    suspend fun incrementCountData(userId : String, readMode : String, bookId : String, elementId : Long)

    // Delete
    @Delete
    suspend fun deleteCountData(countRecord: CountRecordData)
    @Query("DELETE FROM CountData WHERE userId = :userId")
    suspend fun deleteCountDataFromUserId(userId : String)
    @Query("DELETE FROM CountData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId")
    suspend fun deleteCountDataFromBookId(userId : String, readMode : String, bookId : String)
    @Query("DELETE FROM CountData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId")
    suspend fun deleteCountDataFromId(userId : String, readMode : String, bookId : String, elementId : Long)
}