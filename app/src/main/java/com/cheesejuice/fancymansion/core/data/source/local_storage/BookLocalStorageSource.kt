package com.cheesejuice.fancymansion.core.data.source.local_storage

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.entity.book.ConfigEntity
import com.cheesejuice.fancymansion.core.entity.book.LogicEntity
import com.cheesejuice.fancymansion.core.entity.book.PageContentEntity
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
    suspend fun makeConfigFile(config : ConfigEntity) : Boolean

    suspend fun makeLogicFile(logic : LogicEntity, userId : String, readMode : ReadMode, bookId : String) : Boolean

    suspend fun makePageContentFile(page : PageContentEntity, userId : String, readMode : ReadMode, bookId : String) : Boolean

    suspend fun makeImageFileFromResource(userId : String, readMode : ReadMode, bookId : String, imageName : String, resourceId : Int)

    /**
     * Get Object
     */
    suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : ConfigEntity?

    suspend fun getCoverImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?

    suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : LogicEntity?

    suspend fun getPageContentFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContentEntity?

    suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File?
}