package com.cheesejuice.fancymansion.module.log

import android.util.Log
import com.cheesejuice.fancymansion.BuildConfig
import com.cheesejuice.fancymansion.LOG_TAG

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Logging



enum class LogType {
    D,
    E,
    I
}

object Log {
    fun send(message : String, tag : String = LOG_TAG, type : LogType = LogType.D) {
        if (BuildConfig.BUILD_TYPE != "release") {
            when (type) {
                LogType.D -> Log.d(tag, message)
                LogType.E -> Log.e(tag, message)
                LogType.I -> Log.i(tag, message)
            }
        }
    }

    fun sendLong(message : String, tag : String = LOG_TAG, type : LogType = LogType.D) {
        if (BuildConfig.BUILD_TYPE != "release") {
            message.chunked(3000).onEachIndexed { index, s ->
                send(message = "${if (index == 0) "" else " \n"}$s", tag = tag, type = type)
            }
        }
    }
}