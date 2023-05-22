package com.cheesejuice.data.interfaceDatasource

import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.data.mapper.book.ConfigMapper
import com.cheesejuice.data.mapper.book.LogicMapper
import com.cheesejuice.data.mapper.book.PageContentMapper
import java.io.File

interface BookLocalStorageSource {
    /**
     * Init
     */
    suspend fun initRootDir(remove : Boolean = false) : Boolean

    suspend fun initUserDir(userId : String, remove : Boolean = false) : Boolean

    suspend fun initBookDir(userId : String, readMode : ReadMode, bookId : String, remove : Boolean = false) : Boolean

    /**
     * Make File
     */
    suspend fun makeConfigFile(config : ConfigMapper) : Boolean

    suspend fun makeLogicFile(logic : LogicMapper, userId : String, readMode : ReadMode, bookId : String) : Boolean

    suspend fun makePageContentFile(page : PageContentMapper, userId : String, readMode : ReadMode, bookId : String) : Boolean

    suspend fun makeImageFileFromResource(userId : String, readMode : ReadMode, bookId : String, imageName : String, resourceId : Int)

    /**
     * Get Object
     */
    suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : ConfigMapper?

    suspend fun getCoverImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?

    suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : LogicMapper?

    suspend fun getPageContentFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContentMapper?

    suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
}