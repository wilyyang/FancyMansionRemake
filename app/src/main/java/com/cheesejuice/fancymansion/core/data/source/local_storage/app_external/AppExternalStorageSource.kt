package com.cheesejuice.fancymansion.core.data.source.local_storage.app_external

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.data.source.local_storage.LocalStorageSource
import com.cheesejuice.fancymansion.core.data.source.local_storage.app_external.dao.*
import com.cheesejuice.fancymansion.core.data.source.local_storage.model.asData
import com.cheesejuice.fancymansion.core.data.source.local_storage.model.asEntity
import com.cheesejuice.fancymansion.core.entity.book.ConfigEntity
import com.cheesejuice.fancymansion.core.entity.book.LogicEntity
import com.cheesejuice.fancymansion.core.entity.book.PageContentEntity
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppExternalStorageSource @Inject constructor(private val storageDao : AppExternalStorageDao) : LocalStorageSource{

    /**
     * Init
     */
    override suspend fun initRootDir(remove : Boolean) = storageDao.initRootDir(remove)

    override suspend fun initUserDir(userId : String, remove : Boolean) = storageDao.initUserDir(userId, remove)

    override suspend fun initBookDir(userId : String, readMode : ReadMode, bookId : String, remove : Boolean)
    = storageDao.initBookDir(userId, readMode, bookId, remove)

    /**
     * Make File
     */
    override suspend fun makeConfigFile(config : ConfigEntity)
    = storageDao.makeConfigFile(config.asData())

    override suspend fun makeLogicFile(logic : LogicEntity, userId : String, readMode : ReadMode, bookId : String)
    = storageDao.makeLogicFile(logic.asData(), userId, readMode, bookId)

    override suspend fun makePageFile(page : PageContentEntity, userId : String, readMode : ReadMode, bookId : String)
    = storageDao.makePageFile(page.asData(), userId, readMode, bookId)

    override suspend fun makeImageFromResource(userId : String, readMode : ReadMode, bookId : String, imageName : String, resourceId : Int)
    = storageDao.makeImageFromResource(userId, readMode, bookId, imageName, resourceId)

    /**
     * Get Object
     */
    override suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : ConfigEntity?
    = storageDao.getConfigFromFile(userId, readMode, bookId)?.asEntity()

    override suspend fun getCoverFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    = storageDao.getCoverFromFile(userId, readMode, bookId, image)

    override suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : LogicEntity?
    = storageDao.getLogicFromFile(userId, readMode, bookId)?.asEntity()


    override suspend fun getPageFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContentEntity?
    = storageDao.getPageFromFile(userId, readMode, bookId, pageId)?.asEntity()

    override suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
    = storageDao.getImageFromFile(userId, readMode, bookId, image)
}