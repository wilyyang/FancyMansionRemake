package com.cheesejuice.fancymansion.domain.interfaceRepository

import com.cheesejuice.fancymansion.domain.entity.readbook.record.UserInfoEntity

interface UserRepository {
    suspend fun updateUserId(userId : String)
    suspend fun insertUserEntity(userInfoMapper : UserInfoEntity) : Long
    suspend fun isUserEntityExist(userId : String) : Boolean
}