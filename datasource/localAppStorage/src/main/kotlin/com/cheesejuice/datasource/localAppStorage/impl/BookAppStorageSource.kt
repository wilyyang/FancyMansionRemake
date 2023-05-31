package com.cheesejuice.datasource.localAppStorage.impl

import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.data.interfaceDatasource.BookLocalStorageSource
import com.cheesejuice.data.mapper.book.ConfigMapper
import com.cheesejuice.data.mapper.book.LogicMapper
import com.cheesejuice.data.mapper.book.PageContentMapper
import com.cheesejuice.datasource.localAppStorage.impl.dao.AppStorageDao
import com.cheesejuice.datasource.localAppStorage.model.asData
import com.cheesejuice.datasource.localAppStorage.model.asMapper
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookAppStorageSource @Inject constructor(private val storageDao : AppStorageDao) : BookLocalStorageSource {

    /**
     * Init
     */
    override suspend fun initRootDir(remove : Boolean) = storageDao.initRootDir(remove)

    override suspend fun initUserDir(userId : String, remove : Boolean) = storageDao.initUserDir(userId, remove)

    override suspend fun initBookDir(userId : String, readMode : ReadMode, bookId : String, remove : Boolean) =
        storageDao.initBookDir(userId, readMode, bookId, remove)

    /**
     * Make File
     */
    override suspend fun makeConfigFile(config : ConfigMapper) = storageDao.makeConfigFile(config.asData())

    override suspend fun makeLogicFile(logic : LogicMapper, userId : String, readMode : ReadMode, bookId : String) =
        storageDao.makeLogicFile(logic.asData(), userId, readMode, bookId)

    override suspend fun makePageContentFile(page : PageContentMapper, userId : String, readMode : ReadMode, bookId : String) =
        storageDao.makePageContentFile(page.asData(), userId, readMode, bookId)

    override suspend fun makeImageFileFromResource(
        userId : String,
        readMode : ReadMode,
        bookId : String,
        imageName : String,
        resourceId : Int
    ) = storageDao.makeImageFileFromResource(userId, readMode, bookId, imageName, resourceId)

    /**
     * Get Object
     */
    override suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : ConfigMapper? =
        storageDao.getConfigFromFile(userId, readMode, bookId)?.asMapper()

    override suspend fun getCoverImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File? =
        storageDao.getCoverImageFromFile(userId, readMode, bookId, image)

    override suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : LogicMapper? =
        storageDao.getLogicFromFile(userId, readMode, bookId)?.asMapper()

    override suspend fun getPageContentFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContentMapper? =
        storageDao.getPageContentFromFile(userId, readMode, bookId, pageId)?.asMapper()

    override suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File? =
        storageDao.getImageFromFile(userId, readMode, bookId, image)
}