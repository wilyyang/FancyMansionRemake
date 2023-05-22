package com.cheesejuice.data.repository

import com.cheesejuice.data.interfaceDatasource.RecordLocalDatabaseSource
import com.cheesejuice.data.interfaceDatasource.UserPreferencesSource
import com.cheesejuice.data.mapper.user.asMapper
import com.cheesejuice.domain.entity.readbook.record.UserInfoEntity
import com.cheesejuice.domain.interfaceRepository.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val userPreferencesSource : UserPreferencesSource,
    private val recordDatabaseSource : RecordLocalDatabaseSource
) : UserRepository {

    override suspend fun updateUserId(userId : String){
        userPreferencesSource.setUserId(userId)
    }

    /**
     * Room Database
     */
    override suspend fun insertUserEntity(userInfoMapper : UserInfoEntity) : Long
    = recordDatabaseSource.insertUserInfo(userInfoMapper.asMapper())
    override suspend fun isUserEntityExist(userId : String) : Boolean
    = recordDatabaseSource.isUserInfoExist(userId)
}