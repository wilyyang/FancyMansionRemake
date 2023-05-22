package com.cheesejuice.fancymansion.data.mapper.user

import com.cheesejuice.fancymansion.domain.entity.readbook.record.UserInfoEntity

data class UserInfoMapper(
    val userId : String
)

fun UserInfoMapper.asEntity() = UserInfoEntity(
    userId = userId
)

fun UserInfoEntity.asMapper() = UserInfoMapper(
    userId = userId
)