package com.cheesejuice.fancymansion.domain.usecase.readBook

import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.data.mapper.book.ChoiceItemMapper
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseDecideRoute @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val useCaseCheckConditions : UseCaseCheckConditions
) {
    suspend operator fun invoke(userId : String, readMode : String, bookId : String, choice : ChoiceItemMapper) = withContext(dispatcher) {
        choice.routes.find { route ->
            useCaseCheckConditions(userId, readMode, bookId, route.routeConditions)
        }?.routePageId ?: com.cheesejuice.core.common.DEFAULT_END_PAGE_ID
    }
}