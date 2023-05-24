package com.cheesejuice.data.repository

import com.cheesejuice.data.interfaceDatasource.RecordLocalDatabaseSource
import com.cheesejuice.data.interfaceDatasource.UserPreferencesSource
import com.cheesejuice.data.mapper.user.asMapper
import com.cheesejuice.domain.entity.readbook.record.UserInfoEntity
import com.cheesejuice.domain.interfaceRepository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userPreferencesSource : UserPreferencesSource,
    private val recordDatabaseSource : RecordLocalDatabaseSource
) : UserRepository {

    override fun getPreferencesUserIdFlow() : Flow<String> {
        return userPreferencesSource.getUserIdFlow()
    }

    override suspend fun updatePreferencesUserId(userId : String) {
        userPreferencesSource.setUserId(userId)
    }

    /**
     * Room Database
     */
    override suspend fun insertUserInfo(userInfo : UserInfoEntity) : Long = recordDatabaseSource.insertUserInfo(userInfo.asMapper())
    override suspend fun isUserInfoExist(userId : String) : Boolean = recordDatabaseSource.isUserInfoExist(userId)
}