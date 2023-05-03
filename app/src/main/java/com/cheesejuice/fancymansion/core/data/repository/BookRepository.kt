package com.cheesejuice.fancymansion.core.data.repository

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.data.source.database.dao.DatabaseDao
import com.cheesejuice.fancymansion.core.data.source.storage.BookStorageSource
import com.cheesejuice.fancymansion.core.data.source.storage.di.LocalBookStorage
import com.cheesejuice.fancymansion.core.entity.book.ConfigEntity
import com.cheesejuice.fancymansion.core.entity.book.LogicEntity
import com.cheesejuice.fancymansion.core.entity.book.PageContentEntity
import com.cheesejuice.fancymansion.core.entity.book.ReadCount
import com.cheesejuice.fancymansion.core.entity.book.ReadData
import com.cheesejuice.fancymansion.core.entity.book.UserData
import javax.inject.Inject

class BookRepository @Inject constructor(
    @LocalBookStorage private val storageSource : BookStorageSource,
    private val databaseDao : DatabaseDao
) {
    /**
     * File IO
     */
    suspend fun initRootDir(remove:Boolean = false)
    = storageSource.initRootDir(remove)
    suspend fun initUserDir(userId : String, remove:Boolean = false)
    = storageSource.initUserDir(userId, remove)
    suspend fun initBookDir(userId : String, readMode : ReadMode, bookId : String, remove : Boolean = false)
    = storageSource.initBookDir(userId, readMode, bookId, remove)
    suspend fun makeConfigFile(config : ConfigEntity)
    = storageSource.makeConfigFile(config)
    suspend fun makeLogicFile(logic : LogicEntity, userId : String, readMode : ReadMode, bookId : String)
    = storageSource.makeLogicFile(logic, userId, readMode, bookId)
    suspend fun makePageFile(page : PageContentEntity, userId : String, readMode : ReadMode, bookId : String)
    = storageSource.makePageFile(page, userId, readMode, bookId)
    suspend fun makeImageFromResource(userId : String, readMode : ReadMode, bookId : String, imageName : String, resourceId : Int)
    = storageSource.makeImageFromResource(userId, readMode, bookId, imageName, resourceId)
    suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String)
    = storageSource.getConfigFromFile(userId, readMode, bookId)
    suspend fun getCoverFromFile(userId : String, readMode : ReadMode, bookId : String, image : String)
    = storageSource.getCoverFromFile(userId, readMode, bookId, image)
    suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String)
    = storageSource.getLogicFromFile(userId, readMode, bookId)
    suspend fun getPageFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long)
    = storageSource.getPageFromFile(userId, readMode, bookId, pageId)
    suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String)
    = storageSource.getImageFromFile(userId, readMode, bookId, image)

    /**
     * UserData
     */
    suspend fun insertUserData(userData: UserData)
    = databaseDao.insertUserData(userData)
    suspend fun isUserDataExist(userId : String)
    = databaseDao.isUserDataExist(userId)

    /**
     * ReadData
     */
    suspend fun insertReadData(readData: ReadData)
    = databaseDao.insertReadData(readData)
    suspend fun getReadData(userId : String, readMode : String, bookId : String)
    = databaseDao.getReadData(userId, readMode, bookId)
    suspend fun updateReadData(readData: ReadData)
    = databaseDao.updateReadData(readData)
    suspend fun deleteReadDataFromId(userId : String, readMode : String, bookId : String)
    = databaseDao.deleteReadDataFromId(userId, readMode, bookId)

    /**
     * ReadCount
     */
    suspend fun insertReadCount(readCount: ReadCount)
    = databaseDao.insertReadCount(readCount)
    suspend fun isReadCountExist(userId : String, readMode : String, bookId : String, elementId : Long)
    = databaseDao.isReadCountExist(userId, readMode, bookId, elementId)
    suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long)
    = databaseDao.getElementCount(userId, readMode, bookId, elementId)
    suspend fun incrementReadCount(userId : String, readMode : String, bookId : String, elementId : Long)
    = databaseDao.incrementReadCount(userId, readMode, bookId, elementId)
    suspend fun deleteReadCountFromBookId(userId : String, readMode : String, bookId : String)
    = databaseDao.deleteReadCountFromBookId(userId, readMode, bookId)
}