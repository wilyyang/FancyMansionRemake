package com.cheesejuice.fancymansion.data.source.local_storage.app_external.dao

import android.content.Context
import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.common.util.tryBooleanScope
import com.cheesejuice.fancymansion.core.common.util.tryNullableScope
import com.cheesejuice.fancymansion.data.source.local_storage.model.*
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppStorageDao @Inject internal constructor(
    @ApplicationContext private val context : Context
) {
    private val dirRoot = File(context.getExternalFilesDir(null), dirRootName)

    /**
     * Init
     */
    suspend fun initRootDir(remove : Boolean) = tryBooleanScope {
        dirRoot.let {
            if (it.exists() && remove) it.deleteRecursively()
            if (it.exists()) {
                true
            } else {
                it.mkdirs()
            }
        }
    }

    suspend fun initUserDir(userId : String, remove : Boolean) = tryBooleanScope{
        dirUser(dirRoot, userId)?.let{
            if (it.exists() && remove) it.deleteRecursively()
            if (it.exists()) {
                true
            } else {
                (it.mkdirs() &&
                    dirRead(dirRoot, userId, ReadMode.read_only)?.mkdir() ?: false &&
                    dirRead(dirRoot, userId, ReadMode.edit)?.mkdir() ?: false)
            }
        }?: false
    }

    suspend fun initBookDir(userId : String, readMode : ReadMode, bookId : String, remove : Boolean) = tryBooleanScope {
        dirBook(dirRoot, userId, readMode, bookId)?.let {
            if (it.exists() && remove) it.deleteRecursively()
            if (it.exists()) {
                true
            } else {
                (it.mkdir() &&
                    dirContent(dirRoot, userId, readMode, bookId)!!.mkdir() &&
                    dirMedia(dirRoot, userId, readMode, bookId)!!.mkdir() &&
                    dirPage(dirRoot, userId, readMode, bookId)!!.mkdir())
            }
        } ?: false
    }

    /**
     * Make File
     */
    suspend fun makeConfigFile(config : ConfigData) = tryBooleanScope {
        fileConfig(dirRoot, config.userId, ReadMode.from(config.readMode), config.bookId)?.let {
            if (it.exists()) {
                it.delete()
            }

            FileOutputStream(it).use { stream ->
                stream.write(Gson().toJson(config).toByteArray())
            }
            it.exists()
        } ?: false
    }

    suspend fun makeLogicFile(logic : LogicData, userId : String, readMode : ReadMode, bookId : String) = tryBooleanScope {
        fileLogic(dirRoot, userId, readMode, bookId)?.let {
            if (it.exists()) {
                it.delete()
            }

            FileOutputStream(it).use { stream ->
                stream.write(Gson().toJson(logic).toByteArray())
            }
            it.exists()
        } ?: false
    }

    suspend fun makePageContentFile(pageContent : PageContentData, userId : String, readMode : ReadMode, bookId : String) = tryBooleanScope {
        filePage(dirRoot, userId, readMode, bookId, pageContent.pageId)?.let {
            if (it.exists()) {
                it.delete()
            }

            FileOutputStream(it).use { stream ->
                stream.write(Gson().toJson(pageContent).toByteArray())
            }
            it.exists()
        } ?: false
    }

    suspend fun makeImageFileFromResource(userId : String, readMode : ReadMode, bookId : String, imageName : String, resourceId : Int) {
        fileMediaImage(dirRoot, userId, readMode, bookId, imageName)?.let { file ->
            val inputStream : InputStream = context.resources.openRawResource(resourceId)
            val outputStream = FileOutputStream(file)
            val buff = ByteArray(1024)

            var read = 0
            try {
                while (inputStream.read(buff).also { read = it } > 0) {
                    outputStream.write(buff, 0, read)
                }
            } finally {
                inputStream.close()
                outputStream.close()
            }
        }
    }

    /**
     * Get Object
     */
    suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : ConfigData? = tryNullableScope {
        fileConfig(dirRoot, userId, readMode, bookId)?.let {
            if (it.exists()) {
                val configJson = FileInputStream(it).bufferedReader().use { stream -> stream.readText() }
                Gson().fromJson(configJson, ConfigData::class.java)
            }else{
                null
            }
        }
    }

    suspend fun getCoverImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File? = tryNullableScope {
        fileCover(dirRoot, userId, readMode, bookId, image)
    }

    suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : LogicData? = tryNullableScope {
        fileLogic(dirRoot, userId, readMode, bookId)?.let {
            if (it.exists()) {
                val logicJson = FileInputStream(it).bufferedReader().use { stream -> stream.readText() }
                Gson().fromJson(logicJson, LogicData::class.java)
            }else{
                null
            }
        }
    }
    suspend fun getPageContentFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContentData? = tryNullableScope {
        filePage(dirRoot, userId, readMode, bookId, pageId)?.let {
            if (it.exists()) {
                val pageJson = FileInputStream(it).bufferedReader().use { stream -> stream.readText() }
                Gson().fromJson(pageJson, PageContentData::class.java)
            }else{
                null
            }
        }
    }

    suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File? = tryNullableScope {
        fileMediaImage(dirRoot, userId, readMode, bookId, image)
    }
}