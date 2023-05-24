package com.cheesejuice.domain.usecase.user

import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.domain.entity.readbook.record.UserInfoEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.cheesejuice.domain.interfaceRepository.MakeBookRepository
import com.cheesejuice.domain.interfaceRepository.UserRepository

@ViewModelScoped
class UseCaseInitUserInfo @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val userRepository : UserRepository,
    private val makeBookRepository : MakeBookRepository
) {
    suspend operator fun invoke(userId : String) = withContext(dispatcher) {
        userRepository.run {
            insertUserInfo(UserInfoEntity(userId = userId))
            updatePreferencesUserId(userId = userId)
        }

        makeBookRepository.run {
            initRootDir()
            initUserDir(userId = userId)
        }
    }
}