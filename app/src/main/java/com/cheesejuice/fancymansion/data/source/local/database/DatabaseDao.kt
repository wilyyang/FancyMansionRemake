package com.cheesejuice.fancymansion.data.source.local.database

import androidx.room.*
import com.cheesejuice.fancymansion.data.model.ReadData

@Dao
interface DatabaseDao {
    @Query("SELECT EXISTS(SELECT * FROM ReadData WHERE bookId = :bookId)")
    suspend fun isReadDataExist(userId : String, bookId : String) : Boolean

    @Query("SELECT savePage FROM ReadData WHERE bookId = :bookId")
    suspend fun getSavePageId(userId : String, bookId : String) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReadData(userId : String, readData: ReadData) : Long
}