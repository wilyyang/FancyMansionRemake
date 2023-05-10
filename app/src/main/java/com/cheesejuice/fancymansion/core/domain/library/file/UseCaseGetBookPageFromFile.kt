package com.cheesejuice.fancymansion.core.domain.library.file

import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.core.data.repository.BookRepository
import com.cheesejuice.fancymansion.core.domain.library.record.UseCaseCheckConditions
import com.cheesejuice.fancymansion.core.entity.book.ChoiceItemEntity
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
    suspend operator fun invoke(userId : String, readMode: ReadMode, bookId : String, pageId: Long, logic : LogicEntity) = withContext(dispatcher) {
        val pageContent = bookRepository.getPageFromFile(userId = userId, readMode = readMode, bookId = bookId, pageId = pageId)
        val pageImage = pageContent?.let {
            bookRepository.getImageFromFile(
                userId = userId,
                readMode = readMode,
                bookId = bookId,
                image = it.pageImage
            )
        }

        val findPageLogic = logic.logics.find { it.pageId == pageId }
        val checkedChoiceList : MutableList<ChoiceItemEntity> = mutableListOf()
        findPageLogic?.let {
            for(choice in it.choiceItems){
                if(useCaseCheckConditions(userId, readMode.name, bookId, choice.showConditions)){
                    checkedChoiceList.add(choice)
                }
            }
        }
        val pageLogic = findPageLogic?.copy(choiceItems = checkedChoiceList)
        if(pageContent != null && pageLogic != null && pageImage != null) PageEntity(pageContent, pageLogic, pageImage) else null
    }
}