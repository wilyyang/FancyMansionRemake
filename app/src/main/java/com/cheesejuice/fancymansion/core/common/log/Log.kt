package com.cheesejuice.fancymansion.core.common.log

import android.util.Log

const val LOG_TAG = "CraneLog"
enum class LogType {
    D,
    E,
    I
}

object Log {
    fun send(message : String?, tag : String = LOG_TAG, type : LogType = LogType.D) {
        val finalMessage = message?:"Message is null"
        when (type) {
            LogType.D -> Log.d(tag, finalMessage)
            LogType.E -> Log.e(tag, finalMessage)
            LogType.I -> Log.i(tag, finalMessage)
        }
    }
}