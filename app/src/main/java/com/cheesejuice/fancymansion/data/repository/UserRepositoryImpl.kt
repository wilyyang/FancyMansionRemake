package com.cheesejuice.fancymansion.data.repository

import com.cheesejuice.fancymansion.domain.interfaceRepository.UserRepository
import com.cheesejuice.fancymansion.data.mapper.user.UserInfoMapper
import com.cheesejuice.fancymansion.data.interfaceDatasource.RecordLocalDatabaseSource
import com.cheesejuice.fancymansion.data.interfaceDatasource.UserPreferencesSource
import com.cheesejuice.fancymansion.datasource.preferencesDatastore.di.DataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    @DataStore private val userPreferencesSource : UserPreferencesSource,
    @com.cheesejuice.fancymansion.datasource.localRoomDatabase.di.RoomDatabase private val recordDatabaseSource : RecordLocalDatabaseSource
) : UserRepository {

    override suspend fun updateUserId(userId : String){
        userPreferencesSource.setUserId(userId)
    }

    /**
     * Room Database
     */
    override suspend fun insertUserEntity(userInfoMapper : UserInfoMapper) : Long
    = recordDatabaseSource.insertUserInfo(userInfoMapper)
    override suspend fun isUserEntityExist(userId : String) : Boolean
    = recordDatabaseSource.isUserInfoExist(userId)
}