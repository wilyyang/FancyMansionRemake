package com.cheesejuice.fancymansion.domain.library.book_file

import com.cheesejuice.fancymansion.core.common.LOCAL_USER_ID
import com.cheesejuice.fancymansion.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.data.repository.MakeBookRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseInitLocalDir @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val makeBookRepository : MakeBookRepository
) {
    suspend operator fun invoke() = withContext(dispatcher) {
        makeBookRepository.run {
            initRootDir()
            initUserDir(userId = LOCAL_USER_ID)
        }
    }
}