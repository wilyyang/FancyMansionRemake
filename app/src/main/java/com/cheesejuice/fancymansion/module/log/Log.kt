package com.cheesejuice.fancymansion.module.log

import android.util.Log
import com.cheesejuice.fancymansion.BuildConfig
import com.cheesejuice.fancymansion.LOG_TAG

enum class LogType {
    D,
    E,
    I
}

object Log {
    fun send(message : String?, tag : String = LOG_TAG, type : LogType = LogType.D) {
        val finalMessage = message?:"Message is null"
        if (BuildConfig.BUILD_TYPE != "release") {
            when (type) {
                LogType.D -> Log.d(tag, finalMessage)
                LogType.E -> Log.e(tag, finalMessage)
                LogType.I -> Log.i(tag, finalMessage)
            }
        }
    }
}