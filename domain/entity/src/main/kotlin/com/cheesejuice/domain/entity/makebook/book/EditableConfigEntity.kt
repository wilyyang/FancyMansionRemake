package com.cheesejuice.domain.entity.makebook.book

import com.cheesejuice.core.common.DEFAULT_END_PAGE_ID
import com.cheesejuice.core.common.DEFAULT_START_PAGE_ID
import com.cheesejuice.core.common.ReadMode
import com.cheesejuice.domain.entity.readbook.book.ConfigEntity

data class EditableConfigEntity(
    var bookId : String = "",

    var version : Long = 0L,
    var publishCode : String = "",
    var updateTime : Long = System.currentTimeMillis(),

    var userId : String = "",
    var user : String = "",
    var email : String = "",

    var writer : String = "",
    var illustrator : String = "",

    var title : String = "",
    var description : String = "",
    var coverImage : String = "",
    var tagList : MutableList<String> = mutableListOf(),

    var readMode : String = ReadMode.edit.name,
    var defaultStartPageId : Long = DEFAULT_START_PAGE_ID,
    var defaultEndPageId : Long = DEFAULT_END_PAGE_ID,

    var downloads : Int = 0,
    var good : Int = 0,
    var report : Int = 0
)

fun ConfigEntity.toEditable() = EditableConfigEntity(
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