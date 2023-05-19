package com.cheesejuice.core.common.util

import com.cheesejuice.core.common.throwable.ThrowableManager

inline fun <R> String.ifNotBlank(value : (String) -> R) : R? =
    if (this.isNotBlank()) value(this) else null

inline fun tryBooleanScope(block : () -> Boolean) : Boolean {
    return try {
        block()
    } catch (exception : Exception) {
        ThrowableManager.sendError(exception)
        false
    }
}

inline fun <R> tryNullableScope(block : () -> R?) : R? {
    return try {
        block()
    } catch (exception : Exception) {
        ThrowableManager.sendError(exception)
        null
    }
}