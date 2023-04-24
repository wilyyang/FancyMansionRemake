package com.cheesejuice.fancymansion.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserData(
    @PrimaryKey val userId : String,
    val listReadData : List<ReadData> = listOf()
)

@Entity
data class ReadData(
    @PrimaryKey val bookId : String,
    val savePage : Long,
    val listCount : List<ReadCount> = listOf()
)

@Entity
data class ReadCount(
    @PrimaryKey val pageId : Long,
    val count : Int = 0
)