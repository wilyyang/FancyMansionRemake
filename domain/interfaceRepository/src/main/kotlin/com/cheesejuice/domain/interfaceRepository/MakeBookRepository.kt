package com.cheesejuice.domain.interfaceRepository

import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.domain.entity.readbook.book.ConfigEntity
import com.cheesejuice.domain.entity.readbook.book.LogicEntity
import com.cheesejuice.domain.entity.readbook.book.PageContentEntity

interface MakeBookRepository {

    suspend fun initRootDir(remove : Boolean = false) : Boolean
    suspend fun initUserDir(userId : String, remove : Boolean = false) : Boolean
    suspend fun initBookDir(userId : String, readMode : ReadMode, bookId : String, remove : Boolean = false) : Boolean

    suspend fun makeConfigFile(config : ConfigEntity) : Boolean
    suspend fun makeLogicFile(logic : LogicEntity, userId : String, readMode : ReadMode, bookId : String) : Boolean
    suspend fun makePageContentFile(page : PageContentEntity, userId : String, readMode : ReadMode, bookId : String) : Boolean
    suspend fun makeImageFileFromResource(userId : String, readMode : ReadMode, bookId : String, imageName : String, resourceId : Int)
}