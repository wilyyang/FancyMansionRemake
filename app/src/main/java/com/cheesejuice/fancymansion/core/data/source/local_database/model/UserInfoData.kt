package com.cheesejuice.fancymansion.core.data.source.local_database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cheesejuice.fancymansion.core.entity.user.UserEntity

@Entity
data class UserInfoData(
    @PrimaryKey val userId : String
)

fun UserInfoData.asMapper() = UserEntity(
    userId = userId
)

fun UserEntity.asData() = UserInfoData(
    userId = userId
)