package com.cheesejuice.fancymansion

inline fun <R> String.ifNotBlank(defaultValue: (String) -> R): R? =
    if (isNotBlank()) defaultValue(this) else null