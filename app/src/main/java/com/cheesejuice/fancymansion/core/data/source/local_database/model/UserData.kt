package com.cheesejuice.fancymansion.core.data.source.local_database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cheesejuice.fancymansion.core.entity.user.UserEntity

@Entity
data class UserData(
    @PrimaryKey val userId : String
)

fun UserData.asEntity() = UserEntity(
    userId = userId
)

fun UserEntity.asData() = UserData(
    userId = userId
)

