package com.cheesejuice.fancymansion.core.data.repository.impl

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.data.repository.BookRepository
import com.cheesejuice.fancymansion.core.data.source.local_storage.LocalStorageSource
import com.cheesejuice.fancymansion.core.data.source.local_storage.di.AppExternalStorage
import com.cheesejuice.fancymansion.core.entity.book.ConfigEntity
import com.cheesejuice.fancymansion.core.entity.book.LogicEntity
import com.cheesejuice.fancymansion.core.entity.book.PageContentEntity
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookRepositoryImpl @Inject constructor(
    @AppExternalStorage private val storageSource : LocalStorageSource
) : BookRepository {

    /**
     * App External Storage IO
     */
    override suspend fun initRootDir(remove:Boolean) : Boolean
    = storageSource.initRootDir(remove)
    override suspend fun initUserDir(userId : String, remove:Boolean) : Boolean
    = storageSource.initUserDir(userId, remove)
    override suspend fun initBookDir(userId : String, readMode : ReadMode, bookId : String, remove : Boolean) : Boolean
    = storageSource.initBookDir(userId, readMode, bookId, remove)

    override suspend fun makeConfigFile(config : ConfigEntity) : Boolean
    = storageSource.makeConfigFile(config)
    override suspend fun makeLogicFile(logic : LogicEntity, userId : String, readMode : ReadMode, bookId : String) : Boolean
    = storageSource.makeLogicFile(logic, userId, readMode, bookId)
    override suspend fun makePageFile(page : PageContentEntity, userId : String, readMode : ReadMode, bookId : String) : Boolean
    = storageSource.makePageFile(page, userId, readMode, bookId)
    override suspend fun makeImageFromResource(userId : String, readMode : ReadMode, bookId : String, imageName : String, resourceId : Int)
    = storageSource.makeImageFromResource(userId, readMode, bookId, imageName, resourceId)

    override suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : ConfigEntity?
    = storageSource.getConfigFromFile(userId, readMode, bookId)
    override suspend fun getCoverFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    = storageSource.getCoverFromFile(userId, readMode, bookId, image)
    override suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : LogicEntity?
    = storageSource.getLogicFromFile(userId, readMode, bookId)
    override suspend fun getPageFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContentEntity?
    = storageSource.getPageFromFile(userId, readMode, bookId, pageId)
    override suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    = storageSource.getImageFromFile(userId, readMode, bookId, image)
}