package com.cheesejuice.fancymansion.core.domain.library.record

import com.cheesejuice.fancymansion.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.core.data.repository.UserRepository
import com.cheesejuice.fancymansion.core.entity.user.CountEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseRecordReadElement @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val userRepository : UserRepository
) {
    suspend operator fun invoke(userId : String, readMode: String, bookId : String, elementId : Long, isStartPage : Boolean = false) = withContext(dispatcher) {
        if(userRepository.isCountEntityExist(userId, readMode, bookId, elementId)){
            if(!isStartPage){
                userRepository.incrementCountEntity(userId, readMode, bookId, elementId)
            }
        } else {
            userRepository.insertCountEntity(CountEntity(userId, readMode, bookId, elementId, 1))
        }
        Unit
    }
}