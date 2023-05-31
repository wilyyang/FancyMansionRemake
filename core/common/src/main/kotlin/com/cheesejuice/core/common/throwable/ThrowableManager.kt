package com.cheesejuice.core.common.throwable

import com.cheesejuice.core.common.log.Log
import com.cheesejuice.core.common.log.LogType
import com.cheesejuice.core.common.resource.StringResource

sealed class ErrorType {
    object Log : ErrorType()
    data class Dialog(val title : String, val message : String) : ErrorType()
}

object ThrowableManager {
    fun sendError(throwable : Throwable) : ErrorType {
        throwable.printStackTrace()
        Log.send(throwable.message, type = LogType.E)
        return ErrorType.Dialog(
            title = StringResource.error_title,
            message = throwable.message ?: StringResource.error_empty_message
        )
    }
}