package com.cheesejuice.datasource.localRoomDatabase.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cheesejuice.data.mapper.user.UserInfoMapper

@Entity(tableName = "UserInfo")
data class UserInfoData(
    @PrimaryKey val userId : String
)

fun UserInfoData.asMapper() = UserInfoMapper(
    userId = userId
)

fun UserInfoMapper.asData() = UserInfoData(
    userId = userId
)