package com.cheesejuice.fancymansion.core.domain.library.record

import com.cheesejuice.fancymansion.core.common.DEFAULT_END_PAGE_ID
import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.core.data.repository.BookRepository
import com.cheesejuice.fancymansion.core.entity.book.ChoiceItemEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseDecideRoute @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val useCaseCheckConditions : UseCaseCheckConditions
) {
    suspend operator fun invoke(userId : String, readMode: String, bookId : String, choice : ChoiceItemEntity) = withContext(dispatcher) {
        var decideRouteId = DEFAULT_END_PAGE_ID
        for(route in choice.routes){
            if(useCaseCheckConditions(userId, readMode, bookId, route.routeConditions)){
                decideRouteId = route.routePageId
                break
            }
        }
        decideRouteId
    }
}