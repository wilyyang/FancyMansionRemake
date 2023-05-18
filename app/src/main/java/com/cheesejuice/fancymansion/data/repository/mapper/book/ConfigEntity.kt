package com.cheesejuice.fancymansion.data.repository.mapper.book

import com.cheesejuice.fancymansion.core.common.DEFAULT_END_PAGE_ID
import com.cheesejuice.fancymansion.core.common.DEFAULT_START_PAGE_ID
import com.cheesejuice.fancymansion.core.common.ReadMode

data class ConfigEntity(
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