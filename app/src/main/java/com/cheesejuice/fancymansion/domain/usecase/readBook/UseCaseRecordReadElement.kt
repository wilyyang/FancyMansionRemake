package com.cheesejuice.fancymansion.domain.usecase.readBook

import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.domain.entity.readbook.record.CountRecordEntity
import com.cheesejuice.fancymansion.domain.interfaceRepository.ReadBookRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseRecordReadElement @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val readBookRepository : ReadBookRepository
) {
    suspend operator fun invoke(userId : String, readMode : String, bookId : String, elementId : Long, isStartPage : Boolean = false) =
        withContext(dispatcher) {
            if (readBookRepository.isCountEntityExist(userId, readMode, bookId, elementId)) {
                if (!isStartPage) {
                    readBookRepository.incrementCountEntity(userId, readMode, bookId, elementId)
                }
            } else {
                readBookRepository.insertCountEntity(CountRecordEntity(userId, readMode, bookId, elementId, 1))
            }
            Unit
        }
}