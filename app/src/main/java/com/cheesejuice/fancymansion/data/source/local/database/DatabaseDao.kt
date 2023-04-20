package com.cheesejuice.fancymansion.data.source.local.database

import androidx.room.*
import com.cheesejuice.fancymansion.data.source.local.database.model.ReadData

@Dao
interface DatabaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReadData(readData: ReadData) : Long
}