package com.cheesejuice.fancymansion.core.domain.library.file

import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.core.common.LOCAL_USER_ID
import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.common.SAMPLE_BOOK_ID
import com.cheesejuice.fancymansion.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.core.common.sample.Sample
import com.cheesejuice.fancymansion.core.data.repository.BookRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseMakeSample @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val bookRepository : BookRepository
) {
    suspend operator fun invoke() = withContext(dispatcher) {
        bookRepository.run {
            val userId = LOCAL_USER_ID
            val readMode = ReadMode.edit
            val bookId = SAMPLE_BOOK_ID

            initBookDir(userId, readMode, bookId)

            makeConfigFile(Sample.book.config)
            makeLogicFile(Sample.book.logic, userId, readMode, bookId)

            for(pageContent in Sample.book.pageContents){
                makePageFile(pageContent, userId, readMode, bookId)
            }
            val array = arrayOf("image_1.gif", "image_2.gif", "image_3.gif", "image_4.gif", "image_5.gif", "image_6.gif", "fish_cat.jpg", "game_end.jpg")
            val arrayId = arrayOf(R.raw.image_1, R.raw.image_2, R.raw.image_3, R.raw.image_4, R.raw.image_5, R.raw.image_6, R.raw.fish_cat, R.raw.game_end)
            array.forEachIndexed { index, imageName ->
                bookRepository.makeImageFromResource(userId, readMode, bookId, imageName, arrayId[index])
            }
        }
    }
}