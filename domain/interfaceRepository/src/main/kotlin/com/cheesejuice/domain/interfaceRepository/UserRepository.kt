package com.cheesejuice.domain.interfaceRepository

import com.cheesejuice.domain.entity.readbook.record.UserInfoEntity

interface UserRepository {
    suspend fun updateUserId(userId : String)
    suspend fun insertUserInfo(userInfo : UserInfoEntity) : Long
    suspend fun isUserInfoExist(userId : String) : Boolean
}