package com.cheesejuice.fancymansion.data.repository

import com.cheesejuice.fancymansion.domain.interfaceRepository.UserRepository
import com.cheesejuice.fancymansion.data.interfaceDatasource.RecordLocalDatabaseSource
import com.cheesejuice.fancymansion.data.interfaceDatasource.UserPreferencesSource
import com.cheesejuice.fancymansion.data.mapper.user.asMapper
import com.cheesejuice.fancymansion.di.diDatasource.DataStore
import com.cheesejuice.fancymansion.di.diDatasource.RoomDatabase
import com.cheesejuice.fancymansion.domain.entity.readbook.record.UserInfoEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    @DataStore private val userPreferencesSource : UserPreferencesSource,
    @RoomDatabase private val recordDatabaseSource : RecordLocalDatabaseSource
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