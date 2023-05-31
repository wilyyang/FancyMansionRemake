package com.cheesejuice.core.common.throwable

import kotlin.coroutines.cancellation.CancellationException

class FileNotFoundCancellationException(override val message : String? = null) : CancellationException()