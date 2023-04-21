package com.cheesejuice.fancymansion.domain

import com.cheesejuice.fancymansion.data.model.Config
import com.cheesejuice.fancymansion.data.model.Logic
import com.cheesejuice.fancymansion.data.model.Page
import com.cheesejuice.fancymansion.data.model.PageContent
import com.cheesejuice.fancymansion.data.model.PageLogic
import com.cheesejuice.fancymansion.data.repository.BookRepository
import com.cheesejuice.fancymansion.data.source.local.Sample
import com.cheesejuice.fancymansion.data.source.local.database.model.ReadCount
import com.cheesejuice.fancymansion.data.source.local.database.model.ReadData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class ReadBookUseCase @Inject constructor(
    private val bookRepository : BookRepository
) {
    suspend fun getReadDataFlow(config : Config) : Flow<ReadData?> {
        val readDataFlow = bookRepository.getReadDataFlow(config.bookId)
        if(readDataFlow.first() == null){
            val readData = ReadData(
                bookId = config.bookId,
                savePage = config.defaultStartPageId,
                listCount = listOf(ReadCount(config.defaultStartPageId, 1))
            )
            bookRepository.insertReadData(readData)
        }
        return readDataFlow
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