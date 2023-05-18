package com.cheesejuice.fancymansion.core.domain.library.book_file

import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.core.common.LOCAL_USER_ID
import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.core.common.SAMPLE_BOOK_ID
import com.cheesejuice.fancymansion.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.core.common.sample.Sample
import com.cheesejuice.fancymansion.core.data.repository.MakeBookRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseMakeSample @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val makeBookRepository : MakeBookRepository
) {
    suspend operator fun invoke() = withContext(dispatcher) {
        makeBookRepository.run {
            val userId = LOCAL_USER_ID
            val readMode = ReadMode.edit
            val bookId = SAMPLE_BOOK_ID

            initBookDir(userId, readMode, bookId)

            makeConfigFile(Sample.book.config)
            makeLogicFile(Sample.book.logic, userId, readMode, bookId)

            for (pageContent in Sample.book.pageContents) {
                makePageFile(pageContent, userId, readMode, bookId)
            }
            val array = arrayOf(
                "image_1.gif" to R.raw.image_1,
                "image_2.gif" to R.raw.image_2,
                "image_3.gif" to R.raw.image_3,
                "image_4.gif" to R.raw.image_4,
                "image_5.gif" to R.raw.image_5,
                "image_6.gif" to R.raw.image_6,
                "fish_cat.jpg" to R.raw.fish_cat,
                "game_end.jpg" to R.raw.game_end
            )
            array.forEach {
                makeBookRepository.makeImageFromResource(userId, readMode, bookId, it.first, it.second)
            }
        }
    }
}