package com.cheesejuice.domain.usecase.user

import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.domain.interfaceRepository.UserRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseGetUserId @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val userRepository : UserRepository
) {
    suspend operator fun invoke() = withContext(dispatcher) {
        userRepository.run {
            getPreferencesUserIdFlow().first()
        }
    }

    fun getUserIdFlow() = userRepository.getPreferencesUserIdFlow()
}