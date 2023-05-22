package com.cheesejuice.fancymansion.domain.usecase.makeBook

import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.domain.interfaceRepository.MakeBookRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.cheesejuice.core.common.LOCAL_USER_ID

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