package com.cheesejuice.domain.usecase.readBook

import com.cheesejuice.core.common.DEFAULT_END_PAGE_ID
import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.domain.entity.readbook.book.ChoiceItemEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseDecideRoute @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val useCaseCheckConditions : UseCaseCheckConditions
) {
    suspend operator fun invoke(userId : String, readMode : String, bookId : String, choice : ChoiceItemEntity) = withContext(dispatcher) {
        choice.routes.find { route ->
            useCaseCheckConditions(userId, readMode, bookId, route.routeConditions)
        }?.routePageId ?: DEFAULT_END_PAGE_ID
    }
}