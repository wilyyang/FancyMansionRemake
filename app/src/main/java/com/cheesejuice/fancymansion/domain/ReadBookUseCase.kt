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
    suspend fun initReadData(config: Config): Long {
        val isExist = bookRepository.isReadDataExist(config.bookId)
        if (!isExist) {
            val readData = ReadData(
                bookId = config.bookId,
                savePage = config.defaultStartPageId,
                listCount = listOf(ReadCount(config.defaultStartPageId, 1))
            )
            bookRepository.insertReadData(readData)
        }
        return bookRepository.getSavePageId(config.bookId)
    }

    suspend fun getConfig(bookId : String, userId : String) : Config? {
        return bookRepository.getConfigFromFile(bookId, userId)
    }

    suspend fun getLogic(bookId : String, userId : String) : Logic? {
        return bookRepository.getLogicFromFile(bookId, userId)
    }

    suspend fun getPage(bookId: String, userId: String, pageId: Long, logic : Logic) : Page? {
        val pageImage = bookRepository.getImageFromFile(bookId, userId, pageId)
        val pageContent = bookRepository.getPageContentFromFile(bookId, userId, pageId)
        val pageLogic = logic.logics.find { it.pageId == pageId }
        return if(pageContent != null && pageLogic != null && pageImage != null) Page(pageContent, pageLogic, pageImage) else null
    }
}