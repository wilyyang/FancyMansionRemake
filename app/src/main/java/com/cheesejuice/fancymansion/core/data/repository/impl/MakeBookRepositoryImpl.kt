package com.cheesejuice.fancymansion.core.data.repository.impl

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.data.repository.MakeBookRepository
import com.cheesejuice.fancymansion.core.data.repository.ReadBookRepository
import com.cheesejuice.fancymansion.core.data.source.local_storage.BookLocalStorageSource
import com.cheesejuice.fancymansion.core.data.source.local_storage.di.AppStorage
import com.cheesejuice.fancymansion.core.entity.book.ConfigEntity
import com.cheesejuice.fancymansion.core.entity.book.LogicEntity
import com.cheesejuice.fancymansion.core.entity.book.PageContentEntity
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MakeBookRepositoryImpl @Inject constructor(
    @AppStorage private val bookStorageSource : BookLocalStorageSource
) : MakeBookRepository {

    /**
     * App External Storage IO
     */
    override suspend fun initRootDir(remove:Boolean) : Boolean
    = bookStorageSource.initRootDir(remove)
    override suspend fun initUserDir(userId : String, remove:Boolean) : Boolean
    = bookStorageSource.initUserDir(userId, remove)
    override suspend fun initBookDir(userId : String, readMode : ReadMode, bookId : String, remove : Boolean) : Boolean
    = bookStorageSource.initBookDir(userId, readMode, bookId, remove)

    override suspend fun makeConfigFile(config : ConfigEntity) : Boolean
    = bookStorageSource.makeConfigFile(config)
    override suspend fun makeLogicFile(logic : LogicEntity, userId : String, readMode : ReadMode, bookId : String) : Boolean
    = bookStorageSource.makeLogicFile(logic, userId, readMode, bookId)
    override suspend fun makePageFile(page : PageContentEntity, userId : String, readMode : ReadMode, bookId : String) : Boolean
    = bookStorageSource.makePageContentFile(page, userId, readMode, bookId)
    override suspend fun makeImageFromResource(userId : String, readMode : ReadMode, bookId : String, imageName : String, resourceId : Int)
    = bookStorageSource.makeImageFileFromResource(userId, readMode, bookId, imageName, resourceId)
}