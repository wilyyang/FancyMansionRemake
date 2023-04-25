package com.cheesejuice.fancymansion.data.source.local.database

import androidx.room.*
import com.cheesejuice.fancymansion.data.model.ReadCount
import com.cheesejuice.fancymansion.data.model.ReadData
import com.cheesejuice.fancymansion.data.model.UserData

@Dao
interface DatabaseDao {
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
    @Query("SELECT EXISTS(SELECT * FROM ReadData WHERE userId = :userId AND bookId = :bookId)")
    suspend fun isReadDataExist(userId : String, bookId : String) : Boolean
    @Query("SELECT * FROM ReadData WHERE userId = :userId AND bookId = :bookId")
    suspend fun getReadData(userId : String, bookId : String): ReadData?

    // Update
    @Update
    suspend fun updateReadData(readData: ReadData)

    // Delete
    @Delete
    suspend fun deleteReadData(readData: ReadData)
    @Query("DELETE FROM ReadData WHERE userId = :userId")
    suspend fun deleteReadDataFromUserId(userId : String)
    @Query("DELETE FROM ReadData WHERE userId = :userId AND bookId = :bookId")
    suspend fun deleteReadDataFromId(userId : String, bookId : String)

    /**
     * ReadCountDao
     */

    // Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReadCount(readCount: ReadCount) : Long

    // Get
    @Query("SELECT EXISTS(SELECT * FROM ReadCount WHERE userId = :userId AND bookId = :bookId AND elementId = :elementId)")
    suspend fun isReadCountExist(userId : String, bookId : String, elementId : Long) : Boolean
    @Query("SELECT * FROM ReadCount WHERE userId = :userId AND bookId = :bookId AND elementId = :elementId")
    suspend fun getReadCount(userId : String, bookId : String, elementId : Long): ReadCount?
    @Query("SELECT * FROM ReadCount WHERE userId = :userId AND bookId = :bookId")
    suspend fun getReadCountList(userId : String, bookId : String): List<ReadCount>
    @Query("SELECT count FROM ReadCount WHERE userId = :userId AND bookId = :bookId AND elementId = :elementId")
    suspend fun getElementCount(userId : String, bookId : String, elementId : Long): Int?

    // Update
    @Update
    suspend fun updateReadCount(readCount: ReadCount)
    @Query("UPDATE ReadCount SET count = count + 1 WHERE userId = :userId AND bookId = :bookId AND elementId = :elementId")
    suspend fun incrementReadCount(userId : String, bookId : String, elementId : Long)

    // Delete
    @Delete
    suspend fun deleteReadCount(readCount: ReadCount)
    @Query("DELETE FROM ReadCount WHERE userId = :userId")
    suspend fun deleteReadCountFromUserId(userId : String)
    @Query("DELETE FROM ReadCount WHERE userId = :userId AND bookId = :bookId")
    suspend fun deleteReadCountFromBookId(userId : String, bookId : String)
    @Query("DELETE FROM ReadCount WHERE userId = :userId AND bookId = :bookId AND elementId = :elementId")
    suspend fun deleteReadCountFromId(userId : String, bookId : String, elementId : Long)
}