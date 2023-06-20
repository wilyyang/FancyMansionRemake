package com.cheesejuice.data.mapper.book

import com.cheesejuice.core.common.DEFAULT_END_PAGE_ID
import com.cheesejuice.core.common.DEFAULT_START_PAGE_ID
import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.domain.entity.book.ConfigEntity

data class ConfigMapper(
    val bookId : String = "",

    val version : Long = 0L,
    val publishCode : String = "",
    val updateTime : Long = System.currentTimeMillis(),

    val userId : String = "",
    val user : String = "",
    val email : String = "",

    val writer : String = "",
    val illustrator : String = "",

    val title : String = "",
    val description : String = "",
    val coverImage : String = "",
    val tagList : List<String> = listOf(),

    val readMode : String = ReadMode.edit.name,
    val defaultStartPageId : Long = DEFAULT_START_PAGE_ID,
    val defaultEndPageId : Long = DEFAULT_END_PAGE_ID,

    val downloads : Int = 0,
    val good : Int = 0,
    val report : Int = 0
)

fun ConfigMapper.asEntity() = ConfigEntity(
    bookId = bookId,

    version = version,
    publishCode = publishCode,
    updateTime = updateTime,

    userId = userId,
    user = user,
    email = email,

    writer = writer,
    illustrator = illustrator,

    title = title,
    description = description,
    coverImage = coverImage,
    tagList = tagList.toMutableList(),

    readMode = readMode,
    defaultStartPageId = defaultStartPageId,
    defaultEndPageId = defaultEndPageId,

    downloads = downloads,
    good = good,
    report = report
)

fun ConfigEntity.asMapper() = ConfigMapper(
    bookId = bookId,

    version = version,
    publishCode = publishCode,
    updateTime = updateTime,

    userId = userId,
    user = user,
    email = email,

    writer = writer,
    illustrator = illustrator,

    title = title,
    description = description,
    coverImage = coverImage,
    tagList = tagList,

    readMode = readMode,
    defaultStartPageId = defaultStartPageId,
    defaultEndPageId = defaultEndPageId,

    downloads = downloads,
    good = good,
    report = report
)