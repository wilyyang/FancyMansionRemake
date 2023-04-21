package com.cheesejuice.fancymansion.data.source.local.storage

import android.content.Context
import com.cheesejuice.fancymansion.data.model.Config
import com.cheesejuice.fancymansion.data.model.Logic
import com.cheesejuice.fancymansion.data.model.PageContent
import com.cheesejuice.fancymansion.data.source.local.Sample
import java.io.File

class StorageSource constructor(private val context: Context){
    suspend fun getConfigFromFile(bookId : String, userId : String) : Config? {
        return Sample.book.config
    }

    suspend fun getLogicFromFile(bookId : String, userId : String) : Logic? {
        return Sample.book.logic
    }

    suspend fun getPageContentFromFile(bookId : String, userId : String, pageId : Long) : PageContent? {
        val pageContent = Sample.book.pageContents.find {
            it.pageId == pageId
        }
        return pageContent
    }

    suspend fun getImageFromFile(bookId : String, userId : String, pageId : Long) : File? {
        val pageContent = Sample.book.pageContents.find {
            it.pageId == pageId
        }

        return File(pageContent?.pageImage?:"")
    }
}