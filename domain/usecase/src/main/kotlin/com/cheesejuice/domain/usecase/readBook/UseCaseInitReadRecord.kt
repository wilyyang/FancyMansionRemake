package com.cheesejuice.domain.usecase.readBook

import com.cheesejuice.core.common.di.DispatcherIO
import com.cheesejuice.domain.entity.readbook.book.ConfigEntity
import com.cheesejuice.domain.entity.readbook.record.ReadRecordEntity
import com.cheesejuice.domain.entity.readbook.record.UserInfoEntity
import com.cheesejuice.domain.interfaceRepository.ReadBookRepository
import com.cheesejuice.domain.interfaceRepository.UserRepository
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
        if (!userRepository.isUserInfoExist(userId)) {
            userRepository.updateUserId(userId = userId)
            userRepository.insertUserInfo(UserInfoEntity(userId = userId))
        }

        readBookRepository.run {
            if (initBook) {
                deleteReadRecordFromId(userId, config.readMode, config.bookId)
            }

            getReadRecord(userId = userId, readMode = config.readMode, bookId = config.bookId) ?: let {
                val newReadRecord = ReadRecordEntity(
                    userId = userId, readMode = config.readMode, bookId = config.bookId,
                    savePage = config.defaultStartPageId
                )
                deleteCountRecordFromBookId(userId = userId, readMode = config.readMode, bookId = config.bookId)
                insertReadRecord(readRecord = newReadRecord)
                newReadRecord
            }
        }
    }
}