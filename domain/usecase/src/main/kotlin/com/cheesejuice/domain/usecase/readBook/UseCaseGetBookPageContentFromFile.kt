package com.cheesejuice.domain.usecase.readBook

import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.domain.interfaceRepository.ReadBookRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseGetBookPageContentFromFile @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val readBookRepository : ReadBookRepository
) {
    suspend operator fun invoke(userId : String, readMode : ReadMode, bookId : String, pageId : Long) =
        withContext(dispatcher) {
            readBookRepository.getPageContentFromFile(userId = userId, readMode = readMode, bookId = bookId, pageId = pageId)
        }
}