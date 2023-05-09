package com.cheesejuice.fancymansion.core.data.source.local_database

import com.cheesejuice.fancymansion.core.entity.user.CountEntity
import com.cheesejuice.fancymansion.core.entity.user.ReadEntity
import com.cheesejuice.fancymansion.core.entity.user.UserEntity

interface LocalDatabaseSource {
    /**
     * User
     */
    // Insert
    suspend fun insertUserEntity(userEntity: UserEntity) : Long

    // Get
    suspend fun isUserEntityExist(userId : String) : Boolean
    suspend fun getUserEntity(userId : String): UserEntity?

    // Update
    suspend fun updateUserEntity(userEntity: UserEntity)

    // Delete
    suspend fun deleteUserEntity(userEntity: UserEntity)
    suspend fun deleteUserEntityFromId(userId : String)


    /**
     * Read
     */
    // Insert
    suspend fun insertReadEntity(readEntity: ReadEntity) : Long

    // Get
    suspend fun isReadEntityExist(userId : String, readMode : String, bookId : String) : Boolean
    suspend fun getReadEntity(userId : String, readMode : String, bookId : String): ReadEntity?

    // Update
    suspend fun updateReadEntity(readEntity: ReadEntity)

    // Delete
    suspend fun deleteReadEntity(readEntity: ReadEntity)
    suspend fun deleteReadEntityFromUserId(userId : String)
    suspend fun deleteReadEntityFromId(userId : String, readMode : String, bookId : String)

    /**
     * Count
     */
    // Insert
    suspend fun insertCountEntity(countEntity: CountEntity) : Long

    // Get
    suspend fun isCountEntityExist(userId : String, readMode : String, bookId : String, elementId : Long) : Boolean
    suspend fun getCountEntity(userId : String, readMode : String, bookId : String, elementId : Long): CountEntity?
    suspend fun getCountEntityList(userId : String, readMode : String, bookId : String): List<CountEntity>
    suspend fun getElementCount(userId : String, readMode : String, bookId : String, elementId : Long): Int?

    // Update
    suspend fun updateCountEntity(countEntity: CountEntity)
    suspend fun incrementCountEntity(userId : String, readMode : String, bookId : String, elementId : Long)

    // Delete
    suspend fun deleteCountEntity(countEntity: CountEntity)
    suspend fun deleteCountEntityFromUserId(userId : String)
    suspend fun deleteCountEntityFromBookId(userId : String, readMode : String, bookId : String)
    suspend fun deleteCountEntityFromId(userId : String, readMode : String, bookId : String, elementId : Long)
}