package com.cheesejuice.fancymansion.domain.usecase.library.bookFile

import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.domain.interfaceRepository.MakeBookRepository
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
            initUserDir(userId = com.cheesejuice.core.common.LOCAL_USER_ID)
        }
    }
}