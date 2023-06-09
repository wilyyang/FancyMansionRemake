package com.cheesejuice.domain.usecase.readBook

import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.domain.entity.readbook.book.LogicEntity
import com.cheesejuice.domain.entity.readbook.book.PageEntity
import com.cheesejuice.domain.interfaceRepository.ReadBookRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseGetBookPageFromFile @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val readBookRepository : ReadBookRepository,
    private val useCaseCheckConditions : UseCaseCheckConditions
) {
    suspend operator fun invoke(userId : String, readMode : ReadMode, bookId : String, pageId : Long, logic : LogicEntity? = null) =
        withContext(dispatcher) {
            val pageContent = readBookRepository.getPageContentFromFile(userId = userId, readMode = readMode, bookId = bookId, pageId = pageId)

            val pageLogic =
                (logic ?: readBookRepository.getLogicFromFile(userId = userId, readMode = readMode, bookId = bookId))?.let { logic ->
                    logic.logics.find { it.pageId == pageId }?.let { pageLogic ->

                        pageLogic.copy(
                            choiceItems = pageLogic.choiceItems.filter { choiceItem ->
                                useCaseCheckConditions(userId, readMode.name, bookId, choiceItem.showConditions)
                            }
                        )

                    }
                }

            val pageImage = pageContent?.let {
                readBookRepository.getImageFromFile(
                    userId = userId,
                    readMode = readMode,
                    bookId = bookId,
                    image = it.pageImage
                )
            }

            if (pageContent != null && pageLogic != null && pageImage != null) PageEntity(pageContent, pageLogic, pageImage) else null
        }
}