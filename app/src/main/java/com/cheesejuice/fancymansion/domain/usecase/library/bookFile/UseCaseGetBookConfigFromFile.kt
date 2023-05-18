package com.cheesejuice.fancymansion.domain.usecase.library.bookFile

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.domain.interfaceRepository.ReadBookRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseGetBookConfigFromFile @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val readBookRepository : ReadBookRepository
) {
    suspend operator fun invoke(userId : String, readMode : ReadMode, bookId : String) = withContext(dispatcher) {
        readBookRepository.getConfigFromFile(userId = userId, readMode = readMode, bookId = bookId)
    }
}