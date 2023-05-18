package com.cheesejuice.fancymansion.core.data.repository.impl

import com.cheesejuice.fancymansion.core.data.repository.UserRepository
import com.cheesejuice.fancymansion.core.data.source.local_database.RecordLocalDatabaseSource
import com.cheesejuice.fancymansion.core.data.source.local_database.di.RoomDatabase
import com.cheesejuice.fancymansion.core.data.source.preferences.UserPreferencesSource
import com.cheesejuice.fancymansion.core.data.source.preferences.di.DataStore
import com.cheesejuice.fancymansion.core.entity.user.UserEntity
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
    override suspend fun insertUserEntity(userEntity : UserEntity) : Long
    = recordDatabaseSource.insertUserInfo(userEntity)
    override suspend fun isUserEntityExist(userId : String) : Boolean
    = recordDatabaseSource.isUserInfoExist(userId)
}