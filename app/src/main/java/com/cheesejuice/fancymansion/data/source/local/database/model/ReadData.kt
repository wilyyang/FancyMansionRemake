package com.cheesejuice.fancymansion.data.source.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity
@Serializable
data class ReadData(
    @PrimaryKey val bookId : Long,
    val savePage : Long? = null,
    val listCount : List<ReadCount> = listOf()
)

@Entity
@Serializable
data class ReadCount(
    @PrimaryKey val readId : Long,
    val count : Int = 0
)