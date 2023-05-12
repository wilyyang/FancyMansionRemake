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
class UseCaseInitReadRecord @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val userRepository : UserRepository
) {
    suspend operator fun invoke(userId : String, config: ConfigEntity, initBook: Boolean) = withContext(dispatcher) {
        userRepository.run {
            if(!isUserEntityExist(userId)){
                insertUserEntity(UserEntity(userId = userId))
            }

            if(initBook){
                deleteReadEntityFromId(userId, config.readMode, config.bookId)
            }

            getReadEntity(userId = userId, readMode = config.readMode, bookId = config.bookId)?:let{
                val newReadEntity = ReadEntity(
                    userId = userId, readMode = config.readMode, bookId = config.bookId,
                    savePage = config.defaultStartPageId
                )
                deleteCountEntityFromBookId(userId = userId, readMode = config.readMode, bookId = config.bookId)
                insertReadEntity(readEntity = newReadEntity)
                newReadEntity
            }
        }
    }
}