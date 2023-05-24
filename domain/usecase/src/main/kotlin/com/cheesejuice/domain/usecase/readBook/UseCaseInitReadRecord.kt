package com.cheesejuice.domain.usecase.readBook

import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.domain.entity.readbook.book.ConfigEntity
import com.cheesejuice.domain.entity.readbook.record.ReadRecordEntity
import com.cheesejuice.domain.interfaceRepository.ReadBookRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseInitReadRecord @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val readBookRepository : ReadBookRepository
) {
    suspend operator fun invoke(config : ConfigEntity)
    = invoke(userId = config.userId, readMode = config.readMode, bookId = config.bookId, savePage = config.defaultStartPageId)


    suspend operator fun invoke(userId : String, readMode : String, bookId : String, savePage : Long) = withContext(dispatcher) {
        readBookRepository.run {
            deleteReadRecordFromId(userId, readMode, bookId)

            val newReadRecord = ReadRecordEntity(
                userId = userId,
                readMode = readMode,
                bookId = bookId,
                savePage = savePage
            )
            insertReadRecord(readRecord = newReadRecord)
            newReadRecord
        }
    }
}