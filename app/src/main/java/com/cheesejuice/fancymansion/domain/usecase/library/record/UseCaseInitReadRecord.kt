package com.cheesejuice.fancymansion.domain.usecase.library.record

import com.cheesejuice.fancymansion.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.domain.interfaceRepository.ReadBookRepository
import com.cheesejuice.fancymansion.domain.interfaceRepository.UserRepository
import com.cheesejuice.fancymansion.data.mapper.book.ConfigMapper
import com.cheesejuice.fancymansion.data.mapper.user.ReadRecordMapper
import com.cheesejuice.fancymansion.data.mapper.user.UserInfoMapper
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class UseCaseInitReadRecord @Inject constructor(
    @DispatcherIO private val dispatcher : CoroutineDispatcher,
    private val userRepository : UserRepository,
    private val readBookRepository : ReadBookRepository
) {
    suspend operator fun invoke(userId : String, config : ConfigMapper, initBook : Boolean) = withContext(dispatcher) {
        if (!userRepository.isUserEntityExist(userId)) {
            userRepository.updateUserId(userId = userId)
            userRepository.insertUserEntity(UserInfoMapper(userId = userId))
        }

        readBookRepository.run {
            if (initBook) {
                deleteReadEntityFromId(userId, config.readMode, config.bookId)
            }

            getReadEntity(userId = userId, readMode = config.readMode, bookId = config.bookId) ?: let {
                val newReadRecordMapper = ReadRecordMapper(
                    userId = userId, readMode = config.readMode, bookId = config.bookId,
                    savePage = config.defaultStartPageId
                )
                deleteCountEntityFromBookId(userId = userId, readMode = config.readMode, bookId = config.bookId)
                insertReadEntity(readRecordMapper = newReadRecordMapper)
                newReadRecordMapper
            }
        }
    }
}