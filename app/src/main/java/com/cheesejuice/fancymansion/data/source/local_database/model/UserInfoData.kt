package com.cheesejuice.fancymansion.data.source.local_database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cheesejuice.fancymansion.data.repository.mapper.user.UserEntity

@Entity(tableName = "UserInfo")
data class UserInfoData(
    @PrimaryKey val userId : String
)

fun UserInfoData.asMapper() = UserEntity(
    userId = userId
)

fun UserEntity.asData() = UserInfoData(
    userId = userId
)