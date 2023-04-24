package com.cheesejuice.fancymansion.data.repository

import com.cheesejuice.fancymansion.data.model.Config
import com.cheesejuice.fancymansion.data.model.Logic
import com.cheesejuice.fancymansion.data.model.PageContent
import com.cheesejuice.fancymansion.data.source.local.database.DatabaseDao
import com.cheesejuice.fancymansion.data.model.ReadData
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
    suspend fun getConfigFromFile(userId : String, bookId : String) : Config? {
        return storageSource.getConfigFromFile(userId = userId, bookId = bookId)
    }

    suspend fun getLogicFromFile(userId : String, bookId : String) : Logic? {
        return storageSource.getLogicFromFile(userId = userId, bookId = bookId)
    }

    suspend fun getImageFromFile(userId : String, bookId : String, pageId : Long) : File? {
        return storageSource.getImageFromFile(userId = userId, bookId = bookId, pageId = pageId)
    }

    suspend fun getPageContentFromFile(userId : String, bookId : String, pageId : Long) : PageContent? {
        return storageSource.getPageContentFromFile(userId = userId, bookId = bookId, pageId = pageId)
    }


    /**
     * ReadData
     */

    suspend fun isReadDataExist(userId : String, bookId : String): Boolean {
        return databaseDao.isReadDataExist(userId = userId, bookId = bookId)
    }

    suspend fun getSavePageId(userId : String, bookId : String) : Long {
        return databaseDao.getSavePageId(userId = userId, bookId = bookId)
    }

    suspend fun insertReadData(userId : String, readData: ReadData) {
        databaseDao.insertReadData(userId = userId, readData = readData)
    }
}