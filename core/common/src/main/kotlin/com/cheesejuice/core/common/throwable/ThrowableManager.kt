package com.cheesejuice.core.common.throwable

import com.cheesejuice.core.common.log.Log
import com.cheesejuice.core.common.log.LogType

sealed class ShowErrorType(val throwable : Throwable) {
    class Log(throwable : Throwable) : ShowErrorType(throwable)
    class Dialog(throwable : Throwable) : ShowErrorType(throwable)
}

object ThrowableManager {
    fun handleError(throwable : Throwable) : ShowErrorType {
        throwable.printStackTrace()
        Log.send(throwable.message, type = LogType.E)
        return ShowErrorType.Dialog(throwable = throwable)
    }
}