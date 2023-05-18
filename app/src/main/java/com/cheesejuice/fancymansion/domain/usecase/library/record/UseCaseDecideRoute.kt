package com.cheesejuice.fancymansion.domain.usecase.library.record

import com.cheesejuice.fancymansion.core.common.DEFAULT_END_PAGE_ID
import com.cheesejuice.fancymansion.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.data.mapper.book.ChoiceItemEntity
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