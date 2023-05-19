package com.cheesejuice.fancymansion.domain.usecase.readBook

import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.domain.interfaceRepository.ReadBookRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseGetBookLogicFromFile @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val readBookRepository : ReadBookRepository
) {
    suspend operator fun invoke(userId : String, readMode : com.cheesejuice.core.common.ReadMode, bookId : String) = withContext(dispatcher) {
        readBookRepository.getLogicFromFile(userId = userId, readMode = readMode, bookId = bookId)
    }
}