package com.cheesejuice.fancymansion.domain.interfaceRepository

import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.fancymansion.data.mapper.book.ConfigMapper
import com.cheesejuice.fancymansion.data.mapper.book.LogicMapper
import com.cheesejuice.fancymansion.data.mapper.book.PageContentMapper

interface MakeBookRepository {

    suspend fun initRootDir(remove : Boolean = false) : Boolean
    suspend fun initUserDir(userId : String, remove : Boolean = false) : Boolean
    suspend fun initBookDir(userId : String, readMode : ReadMode, bookId : String, remove : Boolean = false) : Boolean

    suspend fun makeConfigFile(config : ConfigMapper) : Boolean
    suspend fun makeLogicFile(logic : LogicMapper, userId : String, readMode : ReadMode, bookId : String) : Boolean
    suspend fun makePageFile(page : PageContentMapper, userId : String, readMode : ReadMode, bookId : String) : Boolean
    suspend fun makeImageFromResource(userId : String, readMode : ReadMode, bookId : String, imageName : String, resourceId : Int)
}