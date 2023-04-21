package com.cheesejuice.fancymansion.module.throwable

import com.cheesejuice.fancymansion.module.log.Log

enum class Error{
    NOT_FOUND_BOOK
}

object ThrowableManager {

    fun sendError(error : Error, throwable : Throwable) {
        Log.send(throwable.message)
    }

    fun sendError(error : Error, message : String?) {
        Log.send(message)
    }
}