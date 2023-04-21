package com.cheesejuice.fancymansion.data.source.local.database

import androidx.room.*
import com.cheesejuice.fancymansion.data.source.local.database.model.ReadData
import kotlinx.coroutines.flow.Flow

@Dao
interface DatabaseDao {
    @Query("SELECT * FROM ReadData WHERE bookId = :bookId")
    fun getReadDataFlow(bookId: String): Flow<ReadData?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReadData(readData: ReadData) : Long
}