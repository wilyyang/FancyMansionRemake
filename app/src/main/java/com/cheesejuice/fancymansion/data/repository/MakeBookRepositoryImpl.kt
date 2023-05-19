package com.cheesejuice.fancymansion.data.repository

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.domain.interfaceRepository.MakeBookRepository
import com.cheesejuice.fancymansion.data.mapper.book.ConfigMapper
import com.cheesejuice.fancymansion.data.mapper.book.LogicMapper
import com.cheesejuice.fancymansion.data.mapper.book.PageContentMapper
import com.cheesejuice.fancymansion.data.interfaceDatasource.BookLocalStorageSource
import com.cheesejuice.fancymansion.di.diDatasource.AppStorage
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

    override suspend fun makeConfigFile(config : ConfigMapper) : Boolean
    = bookStorageSource.makeConfigFile(config)
    override suspend fun makeLogicFile(logic : LogicMapper, userId : String, readMode : ReadMode, bookId : String) : Boolean
    = bookStorageSource.makeLogicFile(logic, userId, readMode, bookId)
    override suspend fun makePageFile(page : PageContentMapper, userId : String, readMode : ReadMode, bookId : String) : Boolean
    = bookStorageSource.makePageContentFile(page, userId, readMode, bookId)
    override suspend fun makeImageFromResource(userId : String, readMode : ReadMode, bookId : String, imageName : String, resourceId : Int)
    = bookStorageSource.makeImageFileFromResource(userId, readMode, bookId, imageName, resourceId)
}