package com.cheesejuice.fancymansion.data.source.local.storage

import android.content.Context
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.ReadMode
import com.cheesejuice.fancymansion.data.model.Config
import com.cheesejuice.fancymansion.data.model.Logic
import com.cheesejuice.fancymansion.data.model.PageContent
import com.cheesejuice.fancymansion.data.source.local.Sample
import com.cheesejuice.fancymansion.util.tryBooleanScope
import com.cheesejuice.fancymansion.util.tryNullableScope
import com.google.gson.Gson
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream

class StorageSource constructor(private val context: Context){
    private val dirRoot = File(context.getExternalFilesDir(null), dirRootName)

    // make Sample
    suspend fun makeSample(userId : String, readMode: ReadMode, bookId : String) {
        initRootDir()
        initBookDir(userId, readMode, bookId)

        makeConfigFile(Sample.book.config)
        makeLogicFile(Sample.book.logic, userId, readMode, bookId)

        for(pageContent in Sample.book.pageContents){
            makePageFile(pageContent, userId, readMode, bookId)
        }
        val array = arrayOf("image_1.gif", "image_2.gif", "image_3.gif", "image_4.gif", "image_5.gif", "image_6.gif", "fish_cat.jpg", "game_end.jpg")
        val arrayId = arrayOf(R.raw.image_1, R.raw.image_2, R.raw.image_3, R.raw.image_4, R.raw.image_5, R.raw.image_6, R.raw.fish_cat, R.raw.game_end)
        array.forEachIndexed { index, imageName ->
            val file = fileMediaImage(dirRoot, userId, readMode, bookId, imageName)
            val input: InputStream = context.resources.openRawResource(arrayId[index])
            val out = FileOutputStream(file)
            val buff = ByteArray(1024)
            var read = 0
            try {
                while (input.read(buff).also { read = it } > 0) {
                    out.write(buff, 0, read)
                }
            } finally {
                input.close()
                out.close()
            }
        }
    }

    // make file
    suspend fun initRootDir() = tryBooleanScope{
        if(dirRoot.exists()){
            true
        }else{
            dirRoot.mkdirs()
        }
    }

    suspend fun initBookDir(userId : String, readMode : ReadMode, bookId : String) = tryBooleanScope {
        dirBook(dirRoot, userId, readMode, bookId)?.let {
            if (it.exists()) {
                it.deleteRecursively()
            }
            (it.mkdir() &&
                dirContent(dirRoot, userId, readMode, bookId)!!.mkdir() &&
                dirMedia(dirRoot, userId, readMode, bookId)!!.mkdir() &&
                dirPage(dirRoot, userId, readMode, bookId)!!.mkdir())
        } ?: false
    }

    suspend fun makeConfigFile(config : Config) = tryBooleanScope {
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

    suspend fun makeLogicFile(logic : Logic, userId : String, readMode : ReadMode, bookId : String) = tryBooleanScope {
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

    suspend fun makePageFile(page : PageContent, userId : String, readMode : ReadMode, bookId : String) = tryBooleanScope {
        filePage(dirRoot, userId, readMode, bookId, page.pageId)?.let {
            if (it.exists()) {
                it.delete()
            }

            FileOutputStream(it).use { stream ->
                stream.write(Gson().toJson(page).toByteArray())
            }
            it.exists()
        } ?: false
    }

    // get file
    suspend fun getConfigFromFile(userId : String, readMode : ReadMode, bookId : String) : Config? = tryNullableScope {
        fileConfig(dirRoot, userId, readMode, bookId)?.let {
            if (it.exists()) {
                val configJson = FileInputStream(it).bufferedReader().use { stream -> stream.readText() }
                Gson().fromJson(configJson, Config::class.java)
            }else{
                null
            }
        }
    }

    suspend fun getCoverFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File? = tryNullableScope {
        fileCover(dirRoot, userId, readMode, bookId, image)
    }

    suspend fun getLogicFromFile(userId : String, readMode : ReadMode, bookId : String) : Logic? = tryNullableScope {
        fileLogic(dirRoot, userId, readMode, bookId)?.let {
            if (it.exists()) {
                val logicJson = FileInputStream(it).bufferedReader().use { stream -> stream.readText() }
                Gson().fromJson(logicJson, Logic::class.java)
            }else{
                null
            }
        }
    }
    suspend fun getPageFromFile(userId : String, readMode : ReadMode, bookId : String, pageId : Long) : PageContent? = tryNullableScope {
        filePage(dirRoot, userId, readMode, bookId, pageId)?.let {
            if (it.exists()) {
                val pageJson = FileInputStream(it).bufferedReader().use { stream -> stream.readText() }
                Gson().fromJson(pageJson, PageContent::class.java)
            }else{
                null
            }
        }
    }

    suspend fun getImageFromFile(userId : String, readMode : ReadMode, bookId : String, image : String) : File? = tryNullableScope {
        fileMediaImage(dirRoot, userId, readMode, bookId, image)
    }
}