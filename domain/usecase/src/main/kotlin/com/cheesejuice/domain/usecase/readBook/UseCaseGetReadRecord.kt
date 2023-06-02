package com.cheesejuice.domain.usecase.readBook

import com.cheesejuice.core.common.NOT_ASSIGN_SAVE_PAGE
import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.domain.entity.readbook.book.ConfigEntity
import com.cheesejuice.domain.interfaceRepository.ReadBookRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseGetReadRecord @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val readBookRepository : ReadBookRepository,
    private val useCaseInitReadRecord : UseCaseInitReadRecord
) {
    suspend operator fun invoke(userId : String, config : ConfigEntity)
    = invoke(userId = userId, readMode = config.readMode, bookId = config.bookId)

    suspend operator fun invoke(userId : String, readMode : String, bookId : String, savePage : Long = NOT_ASSIGN_SAVE_PAGE) =
        withContext(dispatcher) {
            readBookRepository.run {
                getReadRecord(userId = userId, readMode = readMode, bookId = bookId)
                    ?: useCaseInitReadRecord(userId = userId, readMode = readMode, bookId = bookId, savePage = savePage)
            }
        }
}