package com.cheesejuice.fancymansion.data.repository

import com.cheesejuice.fancymansion.data.source.local.database.DatabaseDao
import com.cheesejuice.fancymansion.data.source.local.database.model.ReadData
import javax.inject.Inject

class ReadBookRepository @Inject constructor(
    private val databaseDao : DatabaseDao
) {

    suspend fun insertReadData(bookId : Long){
        databaseDao.insertReadData(ReadData(bookId = bookId))
    }
}