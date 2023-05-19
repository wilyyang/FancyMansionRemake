package com.cheesejuice.fancymansion.domain.usecase.library.record

import com.cheesejuice.fancymansion.core.common.Comparison
import com.cheesejuice.fancymansion.core.common.NOT_ASSIGN_COUNT
import com.cheesejuice.fancymansion.core.common.NOT_ASSIGN_ID
import com.cheesejuice.fancymansion.core.common.Relation
import com.cheesejuice.fancymansion.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.domain.interfaceRepository.ReadBookRepository
import com.cheesejuice.fancymansion.data.mapper.book.ConditionMapper
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseCheckConditions @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val readBookRepository : ReadBookRepository
) {
    suspend operator fun invoke(userId : String, readMode : String, bookId : String, conditions : List<ConditionMapper>) =
        withContext(dispatcher) {
            var relationResult = true
            var nextRelation = Relation.AND
            for (condition in conditions) {
                // 현재 조건을 검사 한다
                val conditionResult = checkCondition(userId, readMode, bookId, condition)

                // 현재 조건 결과와 이전 결과의 관계 결과를 도출 한다
                relationResult = nextRelation.check(relationResult, conditionResult)

                // 현재 조건으로부터 관계 연산자를 가져 온다
                nextRelation = Relation.from(condition.nextRelation)

                // 관계 결과가 true 이고 다음 관계가 OR인 경우 최종 반환 한다
                if (relationResult && nextRelation == Relation.OR) break
            }
            relationResult
        }

    private suspend fun checkCondition(userId : String, readMode : String, bookId : String, condition : ConditionMapper) : Boolean =
        condition.run {
            val targetCount1 = readBookRepository.getElementCount(userId, readMode, bookId, targetId1) ?: 0
            val targetCount2 = if (targetId2 == NOT_ASSIGN_ID) {
                targetCount
            } else {
                readBookRepository.getElementCount(userId, readMode, bookId, this.targetId2) ?: 0
            }

            if (targetCount2 != NOT_ASSIGN_COUNT) {
                Comparison.from(comparison).compare(targetCount1, targetCount2)
            } else {
                false
            }
        }
}