package com.cheesejuice.fancymansion.data.source.local_storage.model

import com.cheesejuice.fancymansion.core.common.DEFAULT_END_PAGE_ID
import com.cheesejuice.fancymansion.core.common.DEFAULT_START_PAGE_ID
import com.cheesejuice.fancymansion.core.common.ReadMode
import com.cheesejuice.fancymansion.data.repository.mapper.book.ConfigEntity

data class ConfigData(
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

fun ConfigData.asMapper() = ConfigEntity(
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

fun ConfigEntity.asData() = ConfigData(
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