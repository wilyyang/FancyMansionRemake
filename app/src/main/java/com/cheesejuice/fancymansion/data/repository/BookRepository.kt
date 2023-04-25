package com.cheesejuice.fancymansion.data.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cheesejuice.fancymansion.data.model.Config
import com.cheesejuice.fancymansion.data.model.Logic
import com.cheesejuice.fancymansion.data.model.PageContent
import com.cheesejuice.fancymansion.data.model.ReadCount
import com.cheesejuice.fancymansion.data.model.ReadData
import com.cheesejuice.fancymansion.data.model.UserData
import com.cheesejuice.fancymansion.data.source.local.database.DatabaseDao
import com.cheesejuice.fancymansion.data.source.local.storage.StorageSource
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
     * UserData
     */
    suspend fun insertUserData(userData: UserData) : Long{
        return databaseDao.insertUserData(userData)
    }
    suspend fun isUserDataExist(userId : String) : Boolean{
        return databaseDao.isUserDataExist(userId)
    }

    /**
     * ReadData
     */
    suspend fun insertReadData(readData: ReadData) : Long{
        return databaseDao.insertReadData(readData)
    }
    suspend fun getReadData(userId : String, bookId : String): ReadData?{
        return databaseDao.getReadData(userId, bookId)
    }
    suspend fun updateReadData(readData: ReadData){
        return databaseDao.updateReadData(readData)
    }
    suspend fun deleteReadDataFromId(userId : String, bookId : String){
        return databaseDao.deleteReadDataFromId(userId, bookId)
    }

    /**
     * ReadCount
     */
    suspend fun insertReadCount(readCount: ReadCount) : Long{
        return databaseDao.insertReadCount(readCount)
    }
    suspend fun isReadCountExist(userId : String, bookId : String, elementId : Long) : Boolean{
        return databaseDao.isReadCountExist(userId, bookId, elementId)
    }
    suspend fun getElementCount(userId : String, bookId : String, elementId : Long): Int? {
        return databaseDao.getElementCount(userId, bookId, elementId)
    }
    suspend fun incrementReadCount(userId : String, bookId : String, elementId : Long){
        return databaseDao.incrementReadCount(userId, bookId, elementId)
    }
    suspend fun deleteReadCountFromBookId(userId : String, bookId : String){
        return databaseDao.deleteReadCountFromBookId(userId, bookId)
    }
}