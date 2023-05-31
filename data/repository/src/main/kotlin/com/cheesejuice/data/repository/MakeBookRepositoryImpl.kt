package com.cheesejuice.data.repository

import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.data.interfaceDatasource.BookLocalStorageSource
import com.cheesejuice.data.mapper.book.asMapper
import com.cheesejuice.domain.entity.readbook.book.ConfigEntity
import com.cheesejuice.domain.entity.readbook.book.LogicEntity
import com.cheesejuice.domain.entity.readbook.book.PageContentEntity
import com.cheesejuice.domain.interfaceRepository.MakeBookRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MakeBookRepositoryImpl @Inject constructor(
    private val bookStorageSource : BookLocalStorageSource
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
    = bookStorageSource.makeConfigFile(config.asMapper())
    override suspend fun makeLogicFile(logic : LogicEntity, userId : String, readMode : ReadMode, bookId : String) : Boolean
    = bookStorageSource.makeLogicFile(logic.asMapper(), userId, readMode, bookId)
    override suspend fun makePageContentFile(page : PageContentEntity, userId : String, readMode : ReadMode, bookId : String) : Boolean
    = bookStorageSource.makePageContentFile(page.asMapper(), userId, readMode, bookId)
    override suspend fun makeImageFileFromResource(userId : String, readMode : ReadMode, bookId : String, imageName : String, resourceId : Int)
    = bookStorageSource.makeImageFileFromResource(userId, readMode, bookId, imageName, resourceId)
}