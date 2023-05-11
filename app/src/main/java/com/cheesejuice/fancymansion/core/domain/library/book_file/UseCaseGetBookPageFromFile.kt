package com.cheesejuice.fancymansion.core.domain.library.book_file

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.core.data.repository.BookRepository
import com.cheesejuice.fancymansion.core.domain.library.record.UseCaseCheckConditions
import com.cheesejuice.fancymansion.core.entity.book.LogicEntity
import com.cheesejuice.fancymansion.core.entity.book.PageEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseGetBookPageFromFile @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val bookRepository : BookRepository,
    private val useCaseCheckConditions : UseCaseCheckConditions
) {
    suspend operator fun invoke(userId : String, readMode : ReadMode, bookId : String, pageId : Long, logic : LogicEntity? = null) =
        withContext(dispatcher) {
            val pageContent = bookRepository.getPageFromFile(userId = userId, readMode = readMode, bookId = bookId, pageId = pageId)

            val pageLogic =
                (logic ?: bookRepository.getLogicFromFile(userId = userId, readMode = readMode, bookId = bookId))?.let { logic ->
                    logic.logics.find { it.pageId == pageId }?.let { pageLogic ->

                        pageLogic.copy(
                            choiceItems = pageLogic.choiceItems.filter { choiceItem ->
                                useCaseCheckConditions(userId, readMode.name, bookId, choiceItem.showConditions)
                            }
                        )

                    }
                }

            val pageImage = pageContent?.let {
                bookRepository.getImageFromFile(
                    userId = userId,
                    readMode = readMode,
                    bookId = bookId,
                    image = it.pageImage
                )
            }

            if (pageContent != null && pageLogic != null && pageImage != null) PageEntity(pageContent, pageLogic, pageImage) else null
        }
}