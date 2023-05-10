package com.cheesejuice.fancymansion.core.domain.library.file

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.core.data.repository.BookRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseGetBookLogicFromFile @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val bookRepository : BookRepository
) {
    suspend operator fun invoke(userId : String, readMode: ReadMode, bookId : String) = withContext(dispatcher) {
        bookRepository.getLogicFromFile(userId = userId, readMode = readMode, bookId = bookId)
    }
}