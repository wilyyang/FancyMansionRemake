package com.cheesejuice.fancymansion.core.data.repository

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.data.source.local_database.LocalDatabaseSource
import com.cheesejuice.fancymansion.core.data.source.local_database.di.RoomDatabase
import com.cheesejuice.fancymansion.core.data.source.local_storage.LocalStorageSource
import com.cheesejuice.fancymansion.core.data.source.local_storage.di.AppExternalStorage
import com.cheesejuice.fancymansion.core.entity.book.ConfigEntity
import com.cheesejuice.fancymansion.core.entity.book.LogicEntity
import com.cheesejuice.fancymansion.core.entity.book.PageContentEntity
import com.cheesejuice.fancymansion.core.entity.user.CountEntity
import com.cheesejuice.fancymansion.core.entity.user.ReadEntity
import com.cheesejuice.fancymansion.core.entity.user.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepository @Inject constructor(
    @AppExternalStorage private val storageSource : LocalStorageSource,
    @RoomDatabase private val databaseSource : LocalDatabaseSource
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
     * UserEntity
     */
    suspend fun insertUserEntity(userEntity: UserEntity)
    = databaseSource.insertUserEntity(userEntity)
    suspend fun isUserEntityExist(userId : String)
    = databaseSource.isUserEntityExist(userId)

    /**
     * ReadEntity
     */
    suspend fun insertReadEntity(readEntity: ReadEntity)
    = databaseSource.insertReadEntity(readEntity)
    suspend fun getReadEntity(userId : String, readMode : String, bookId : String)
    = databaseSource.getReadEntity(userId, readMode, bookId)
    suspend fun updateReadEntity(readEntity: ReadEntity)
    = databaseSource.updateReadEntity(readEntity)
    suspend fun deleteReadEntityFromId(userId : String, readMode : String, bookId : String)
    = databaseSource.deleteReadEntityFromId(userId, readMode, bookId)

    /**
     * CountEntity
     */
    suspend fun insertCountEntity(countEntity: CountEntity)
    = databaseSource.insertCountEntity(countEntity)
    suspend fun isCountEntityExist(userId : String, readMode : String, bookId : String, elementId : Long)
    = databaseSource.isCountEntityExist(userId, readMode, bookId, elementId)
    suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long)
    = databaseSource.getElementCount(userId, readMode, bookId, elementId)
    suspend fun incrementCountEntity(userId : String, readMode : String, bookId : String, elementId : Long)
    = databaseSource.incrementCountEntity(userId, readMode, bookId, elementId)
    suspend fun deleteCountEntityFromBookId(userId : String, readMode : String, bookId : String)
    = databaseSource.deleteCountEntityFromBookId(userId, readMode, bookId)
}