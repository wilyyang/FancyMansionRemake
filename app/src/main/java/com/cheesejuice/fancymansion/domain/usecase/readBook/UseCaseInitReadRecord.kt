package com.cheesejuice.fancymansion.domain.usecase.readBook

import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.fancymansion.domain.entity.readbook.book.ConfigEntity
import com.cheesejuice.fancymansion.domain.entity.readbook.record.ReadRecordEntity
import com.cheesejuice.fancymansion.domain.entity.readbook.record.UserInfoEntity
import com.cheesejuice.fancymansion.domain.interfaceRepository.ReadBookRepository
import com.cheesejuice.fancymansion.domain.interfaceRepository.UserRepository
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
    suspend operator fun invoke(userId : String, config : ConfigEntity, initBook : Boolean) = withContext(dispatcher) {
        if (!userRepository.isUserEntityExist(userId)) {
            userRepository.updateUserId(userId = userId)
            userRepository.insertUserEntity(UserInfoEntity(userId = userId))
        }

        readBookRepository.run {
            if (initBook) {
                deleteReadEntityFromId(userId, config.readMode, config.bookId)
            }

            getReadEntity(userId = userId, readMode = config.readMode, bookId = config.bookId) ?: let {
                val newReadRecordMapper = ReadRecordEntity(
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