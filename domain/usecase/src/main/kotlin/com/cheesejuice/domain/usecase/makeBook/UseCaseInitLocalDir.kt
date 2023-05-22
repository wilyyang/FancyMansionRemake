package com.cheesejuice.domain.usecase.makeBook

import com.cheesejuice.core.common.di.DispatcherIO
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.cheesejuice.core.common.LOCAL_USER_ID
import com.cheesejuice.domain.interfaceRepository.MakeBookRepository

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