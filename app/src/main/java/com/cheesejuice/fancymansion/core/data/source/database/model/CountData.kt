package com.cheesejuice.fancymansion.core.data.source.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import com.cheesejuice.fancymansion.core.entity.user.CountEntity

@Entity(
    primaryKeys = ["userId", "readMode", "bookId", "elementId"],
    foreignKeys = [
        ForeignKey(
            entity = ReadData::class,
            parentColumns = ["userId", "readMode", "bookId"],
            childColumns = ["userId", "readMode", "bookId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CountData(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val elementId : Long,
    val count : Int = 0
)

fun CountData.asEntity() = CountEntity(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    elementId = elementId,
    count = count
)

fun CountEntity.asData() = CountData(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    elementId = elementId,
    count = count
)