package com.cheesejuice.fancymansion.core.data.source.storage

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.common.util.ifNotBlank
import java.io.File

const val dirRootName = "book"
const val fileConfigName = "config.json"
const val dirContentName = "content"
const val fileLogicName = "logic.json"
const val dirMediaName = "media"
const val dirPageName = "page"
fun  pageFormat(pageId : Long) = "page_$pageId.json"

/**
 * /book/userId
 */
fun dirUser(root:File, userId : String) = userId.ifNotBlank {
    File(root, it)
}
/**
 * /book/userId/ [read_only | edit] /
 */
fun dirRead(root:File, userId : String, readMode : ReadMode) = dirUser(root, userId)?.let {
    File(it, readMode.name)
}
/**
 * /book/userId/ [read_only | edit] /bookId/
 */
fun dirBook(root:File, userId : String, readMode : ReadMode, bookId : String) = bookId.ifNotBlank {
    dirRead(root, userId, readMode)?.let {
        File(it, bookId)
    }
}
/**
 * /book/userId/ [read_only | edit] /bookId/cover.jpg
 */
fun fileCover(root:File, userId : String, readMode : ReadMode, bookId : String, coverFileName : String) = coverFileName.ifNotBlank {
    dirBook(root, userId, readMode, bookId)?.let {
        File(it, coverFileName)
    }
}
/**
 * /book/userId/ [read_only | edit] /bookId/config.json
 */
fun fileConfig(root:File, userId : String, readMode : ReadMode, bookId : String) = dirBook(root, userId, readMode, bookId)?.let {
    File(it, fileConfigName)
}
/**
 * /book/userId/ [read_only | edit] /bookId/content/
 */
fun dirContent(root:File, userId : String, readMode : ReadMode, bookId : String) = dirBook(root, userId, readMode, bookId)?.let {
    File(it, dirContentName)
}
/**
 * /book/userId/ [read_only | edit] /bookId/content/logic.json
 */
fun fileLogic(root:File, userId : String, readMode : ReadMode, bookId : String) = dirContent(root, userId, readMode, bookId)?.let {
    File(it, fileLogicName)
}
/**
 * /book/userId/ [read_only | edit] /bookId/content/media/
 */
fun dirMedia(root:File, userId : String, readMode : ReadMode, bookId : String) = dirContent(root, userId, readMode, bookId)?.let {
    File(it, dirMediaName)
}
/**
 * /book/userId/ [read_only | edit] /bookId/content/media/image.jpg
 */
fun fileMediaImage(root : File, userId : String, readMode : ReadMode, bookId : String, image : String) = image.ifNotBlank {
    dirMedia(root, userId, readMode, bookId)?.let {
        File(it, image)
    }
}
/**
 * /book/userId/ [read_only | edit] /bookId/content/page/
 */
fun dirPage(root:File, userId : String, readMode : ReadMode, bookId : String) = dirContent(root, userId, readMode, bookId)?.let {
    File(it, dirPageName)
}
/**
 * /book/userId/ [read_only | edit] /bookId/content/page/page_1.json
 */
fun filePage(root : File, userId : String, readMode : ReadMode, bookId : String, pageId : Long)
= dirPage(root, userId, readMode, bookId)?.let {
    File(it, pageFormat(pageId))
}