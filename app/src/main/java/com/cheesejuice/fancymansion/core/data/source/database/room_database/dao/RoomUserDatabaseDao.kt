package com.cheesejuice.fancymansion.core.data.source.database.room_database.dao

import androidx.room.*
import com.cheesejuice.fancymansion.core.data.source.database.model.*

@Dao
interface RoomUserDatabaseDao {
    /**
     * UserDataDao
     */

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserData(userData: UserData) : Long

    // Get
    @Query("SELECT EXISTS(SELECT * FROM UserData WHERE userId = :userId)")
    suspend fun isUserDataExist(userId : String) : Boolean
    @Query("SELECT * FROM UserData WHERE userId = :userId")
    suspend fun getUserData(userId : String): UserData?

    // Update
    @Update
    suspend fun updateUserData(userData: UserData)

    // Delete
    @Delete
    suspend fun deleteUserData(userData: UserData)
    @Query("DELETE FROM UserData WHERE userId = :userId")
    suspend fun deleteUserDataFromId(userId : String)


    /**
     * ReadDataDao
     */

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReadData(readData: ReadData) : Long

    // Get
    @Query("SELECT EXISTS(SELECT * FROM ReadData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId)")
    suspend fun isReadDataExist(userId : String, readMode : String, bookId : String) : Boolean
    @Query("SELECT * FROM ReadData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId")
    suspend fun getReadData(userId : String, readMode : String, bookId : String): ReadData?

    // Update
    @Update
    suspend fun updateReadData(readData: ReadData)

    // Delete
    @Delete
    suspend fun deleteReadData(readData: ReadData)
    @Query("DELETE FROM ReadData WHERE userId = :userId")
    suspend fun deleteReadDataFromUserId(userId : String)
    @Query("DELETE FROM ReadData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId")
    suspend fun deleteReadDataFromId(userId : String, readMode : String, bookId : String)

    /**
     * CountDataDao
     */

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountData(countData: CountData) : Long

    // Get
    @Query("SELECT EXISTS(SELECT * FROM CountData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId)")
    suspend fun isCountDataExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    @Query("SELECT * FROM CountData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId")
    suspend fun getCountData(userId : String, readMode : String, bookId : String, elementId : Long): CountData?
    @Query("SELECT * FROM CountData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId")
    suspend fun getCountDataList(userId : String, readMode : String, bookId : String): List<CountData>
    @Query("SELECT count FROM CountData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId")
    suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long): Int?

    // Update
    @Update
    suspend fun updateCountData(countData: CountData)
    @Query("UPDATE CountData SET count = count + 1 WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId")
    suspend fun incrementCountData(userId : String, readMode : String, bookId : String, elementId : Long)

    // Delete
    @Delete
    suspend fun deleteCountData(countData: CountData)
    @Query("DELETE FROM CountData WHERE userId = :userId")
    suspend fun deleteCountDataFromUserId(userId : String)
    @Query("DELETE FROM CountData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId")
    suspend fun deleteCountDataFromBookId(userId : String, readMode : String, bookId : String)
    @Query("DELETE FROM CountData WHERE userId = :userId AND readMode = :readMode AND bookId = :bookId AND elementId = :elementId")
    suspend fun deleteCountDataFromId(userId : String, readMode : String, bookId : String, elementId : Long)
}