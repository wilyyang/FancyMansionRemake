package com.cheesejuice.fancymansion.core.data.repository

import com.cheesejuice.fancymansion.core.entity.user.CountEntity
import com.cheesejuice.fancymansion.core.entity.user.ReadEntity
import com.cheesejuice.fancymansion.core.entity.user.UserEntity

interface UserRepository {

    suspend fun insertUserEntity(userEntity : UserEntity) : Long
    suspend fun isUserEntityExist(userId : String) : Boolean

    suspend fun insertReadEntity(readEntity : ReadEntity) : Long
    suspend fun getReadEntity(userId : String, readMode : String, bookId : String) : ReadEntity?
    suspend fun updateReadEntity(readEntity : ReadEntity)
    suspend fun deleteReadEntityFromId(userId : String, readMode : String, bookId : String)

    suspend fun insertCountEntity(countEntity : CountEntity) : Long
    suspend fun isCountEntityExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long) : Int?
    suspend fun incrementCountEntity(userId : String, readMode : String, bookId : String, elementId : Long)
    suspend fun deleteCountEntityFromBookId(userId : String, readMode : String, bookId : String)
}