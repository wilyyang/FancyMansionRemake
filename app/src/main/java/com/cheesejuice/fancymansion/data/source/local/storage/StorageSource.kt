package com.cheesejuice.fancymansion.data.source.local.storage

import android.content.Context
import com.cheesejuice.fancymansion.data.model.Config
import com.cheesejuice.fancymansion.data.model.Logic
import com.cheesejuice.fancymansion.data.model.PageContent
import com.cheesejuice.fancymansion.data.source.local.Sample
import java.io.File

class StorageSource constructor(private val context: Context){
    suspend fun getConfigFromFile(userId : String, bookId : String) : Config? {
        return Sample.book.config
    }

    suspend fun getLogicFromFile(userId : String, bookId : String) : Logic? {
        return Sample.book.logic
    }

    suspend fun getPageContentFromFile(userId : String, bookId : String, pageId : Long) : PageContent? {
        val pageContent = Sample.book.pageContents.find {
            it.pageId == pageId
        }
        return pageContent
    }

    suspend fun getImageFromFile(userId : String, bookId : String, pageId : Long) : File? {
        val pageContent = Sample.book.pageContents.find {
            it.pageId == pageId
        }

        return File(pageContent?.pageImage?:"")
    }
}