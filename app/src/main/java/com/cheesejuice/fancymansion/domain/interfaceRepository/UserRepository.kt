package com.cheesejuice.fancymansion.domain.interfaceRepository

import com.cheesejuice.fancymansion.data.mapper.user.UserInfoMapper

interface UserRepository {
    suspend fun updateUserId(userId : String)
    suspend fun insertUserEntity(userInfoMapper : UserInfoMapper) : Long
    suspend fun isUserEntityExist(userId : String) : Boolean
}