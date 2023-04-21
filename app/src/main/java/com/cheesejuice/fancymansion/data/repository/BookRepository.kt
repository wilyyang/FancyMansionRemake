package com.cheesejuice.fancymansion.data.repository

import com.cheesejuice.fancymansion.data.model.Config
import com.cheesejuice.fancymansion.data.model.Logic
import com.cheesejuice.fancymansion.data.model.PageContent
import com.cheesejuice.fancymansion.data.source.local.database.DatabaseDao
import com.cheesejuice.fancymansion.data.source.local.database.model.ReadData
import com.cheesejuice.fancymansion.data.source.local.storage.StorageSource
import kotlinx.coroutines.flow.Flow
import java.io.File
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val storageSource : StorageSource,
    private val databaseDao : DatabaseDao
) {
    /**
     * Book
     */
    suspend fun getConfigFromFile(bookId : String, userId : String) : Config? {
        return storageSource.getConfigFromFile(bookId, userId)
    }

    suspend fun getLogicFromFile(bookId : String, userId : String) : Logic? {
        return storageSource.getLogicFromFile(bookId, userId)
    }

    suspend fun getImageFromFile(bookId : String, userId : String, pageId : Long) : File? {
        return storageSource.getImageFromFile(bookId, userId, pageId)
    }

    suspend fun getPageContentFromFile(bookId : String, userId : String, pageId : Long) : PageContent? {
        return storageSource.getPageContentFromFile(bookId, userId, pageId)
    }


    /**
     * ReadData
     */
    suspend fun insertReadData(readData: ReadData){
        databaseDao.insertReadData(readData)
    }
    fun getReadDataFlow(bookId : String): Flow<ReadData?> {
        return databaseDao.getReadDataFlow(bookId = bookId)
    }
}