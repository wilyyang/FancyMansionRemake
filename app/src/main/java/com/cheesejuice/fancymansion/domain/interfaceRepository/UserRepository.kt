package com.cheesejuice.fancymansion.domain.interfaceRepository

import com.cheesejuice.fancymansion.data.mapper.user.UserEntity

interface UserRepository {
    suspend fun updateUserId(userId : String)
    suspend fun insertUserEntity(userEntity : UserEntity) : Long
    suspend fun isUserEntityExist(userId : String) : Boolean
}