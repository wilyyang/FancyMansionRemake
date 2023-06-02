package com.cheesejuice.domain.interfaceRepository

import com.cheesejuice.domain.entity.readbook.record.UserInfoEntity
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getPreferencesUserIdFlow() : Flow<String>
    suspend fun updatePreferencesUserId(userId : String)

    suspend fun getIsFirstExecute() : Boolean
    suspend fun setIsFirstExecute(value : Boolean)

    suspend fun insertUserInfo(userInfo : UserInfoEntity) : Long
    suspend fun isUserInfoExist(userId : String) : Boolean
}