package com.cheesejuice.fancymansion.domain

import com.cheesejuice.fancymansion.data.model.Config
import com.cheesejuice.fancymansion.data.model.Logic
import com.cheesejuice.fancymansion.data.model.Page
import com.cheesejuice.fancymansion.data.repository.BookRepository
import com.cheesejuice.fancymansion.data.model.ReadCount
import com.cheesejuice.fancymansion.data.model.ReadData
import javax.inject.Inject

class ReadBookUseCase @Inject constructor(
    private val bookRepository : BookRepository
) {
    suspend fun initReadData(userId : String, config: Config): Long {
        val isExist = bookRepository.isReadDataExist(userId = userId, bookId = config.bookId)
        if (!isExist) {
            val readData = ReadData(
                bookId = config.bookId,
                savePage = config.defaultStartPageId,
                listCount = listOf(ReadCount(config.defaultStartPageId, 1))
            )
            bookRepository.insertReadData(userId = userId, readData = readData)
        }
        return bookRepository.getSavePageId(userId = userId, bookId = config.bookId)
    }

    suspend fun getConfig(userId : String, bookId : String) : Config? {
        return bookRepository.getConfigFromFile(userId = userId, bookId = bookId)
    }

    suspend fun getLogic(userId : String, bookId : String) : Logic? {
        return bookRepository.getLogicFromFile(userId = userId, bookId = bookId)
    }

    suspend fun getPage(userId: String, bookId: String, pageId: Long, logic : Logic) : Page? {
        val pageImage = bookRepository.getImageFromFile(userId = userId, bookId = bookId, pageId = pageId)
        val pageContent = bookRepository.getPageContentFromFile(userId = userId, bookId = bookId, pageId = pageId)
        val pageLogic = logic.logics.find { it.pageId == pageId }
        return if(pageContent != null && pageLogic != null && pageImage != null) Page(pageContent, pageLogic, pageImage) else null
    }
}