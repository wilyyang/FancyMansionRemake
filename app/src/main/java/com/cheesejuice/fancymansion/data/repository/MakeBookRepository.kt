package com.cheesejuice.fancymansion.data.repository

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.data.repository.mapper.book.ConfigEntity
import com.cheesejuice.fancymansion.data.repository.mapper.book.LogicEntity
import com.cheesejuice.fancymansion.data.repository.mapper.book.PageContentEntity

interface MakeBookRepository {

    suspend fun initRootDir(remove : Boolean = false) : Boolean
    suspend fun initUserDir(userId : String, remove : Boolean = false) : Boolean
    suspend fun initBookDir(userId : String, readMode : ReadMode, bookId : String, remove : Boolean = false) : Boolean

    suspend fun makeConfigFile(config : ConfigEntity) : Boolean
    suspend fun makeLogicFile(logic : LogicEntity, userId : String, readMode : ReadMode, bookId : String) : Boolean
    suspend fun makePageFile(page : PageContentEntity, userId : String, readMode : ReadMode, bookId : String) : Boolean
    suspend fun makeImageFromResource(userId : String, readMode : ReadMode, bookId : String, imageName : String, resourceId : Int)
}