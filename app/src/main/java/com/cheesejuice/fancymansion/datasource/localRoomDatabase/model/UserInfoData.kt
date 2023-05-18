package com.cheesejuice.fancymansion.datasource.localRoomDatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cheesejuice.fancymansion.data.mapper.user.UserEntity

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