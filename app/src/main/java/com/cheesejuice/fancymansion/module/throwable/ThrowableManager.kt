package com.cheesejuice.fancymansion.module.throwable

import com.cheesejuice.fancymansion.module.log.Log

object ThrowableManager {
    fun sendError(throwable : Throwable) {
        Log.send(throwable.message)
    }

    fun sendError(message : String?) {
        Log.send(message)
    }
}