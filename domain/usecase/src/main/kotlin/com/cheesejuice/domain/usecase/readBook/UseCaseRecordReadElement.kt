package com.cheesejuice.domain.usecase.readBook

import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.domain.entity.readbook.record.CountRecordEntity
import com.cheesejuice.domain.interfaceRepository.ReadBookRepository
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
            if (readBookRepository.isCountRecordExist(userId, readMode, bookId, elementId)) {
                // 레코드가 있으나 시작페이지일 경우, 이미 카운트되어 있으므로 증가시키지 않음
                if (!isStartPage) {
                    readBookRepository.incrementCountRecord(userId, readMode, bookId, elementId)
                }
            } else {
                // 레코드가 없으면 카운트 1의 레코드 생성
                readBookRepository.insertCountRecord(CountRecordEntity(userId, readMode, bookId, elementId, 1))
            }
            Unit
        }
}