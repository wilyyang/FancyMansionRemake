package com.cheesejuice.fancymansion.core.domain.library.record

import com.cheesejuice.fancymansion.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.core.data.repository.UserRepository
import com.cheesejuice.fancymansion.core.entity.book.ConfigEntity
import com.cheesejuice.fancymansion.core.entity.user.ReadEntity
import com.cheesejuice.fancymansion.core.entity.user.UserEntity
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseGetReadRecord @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val userRepository : UserRepository
) {
    suspend operator fun invoke(userId : String, config: ConfigEntity, initBook: Boolean) = withContext(dispatcher) {
        if(!userRepository.isUserEntityExist(userId)){
            userRepository.insertUserEntity(UserEntity(userId = userId))
        }

        if(initBook){
            userRepository.deleteReadEntityFromId(userId, config.readMode, config.bookId)
        }

        val readData = userRepository.getReadEntity(userId = userId, readMode = config.readMode, bookId = config.bookId)?:let{
            val newReadEntity = ReadEntity(
                userId = userId, readMode = config.readMode, bookId = config.bookId,
                savePage = config.defaultStartPageId
            )
            userRepository.deleteCountEntityFromBookId(userId = userId, readMode = config.readMode, bookId = config.bookId)
            userRepository.insertReadEntity(readEntity = newReadEntity)
            newReadEntity
        }
        readData
    }
}