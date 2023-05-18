package com.cheesejuice.fancymansion.core.data.repository

import com.cheesejuice.fancymansion.core.entity.user.UserEntity

interface UserRepository {
    suspend fun updateUserId(userId : String)
    suspend fun insertUserEntity(userEntity : UserEntity) : Long
    suspend fun isUserEntityExist(userId : String) : Boolean
}