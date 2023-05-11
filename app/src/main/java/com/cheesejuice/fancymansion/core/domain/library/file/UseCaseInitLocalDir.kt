package com.cheesejuice.fancymansion.core.domain.library.file

import com.cheesejuice.fancymansion.core.common.LOCAL_USER_ID
import com.cheesejuice.fancymansion.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.core.data.repository.BookRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseInitLocalDir @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val bookRepository : BookRepository
) {
    suspend operator fun invoke() = withContext(dispatcher) {
        bookRepository.run {
            initRootDir()
            initUserDir(userId = LOCAL_USER_ID)
        }
    }
}