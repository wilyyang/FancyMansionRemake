package com.cheesejuice.fancymansion.data.source.local_database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import com.cheesejuice.fancymansion.data.repository.mapper.user.CountEntity

@Entity(
    tableName = "CountRecord",
    primaryKeys = ["userId", "readMode", "bookId", "elementId"],
    foreignKeys = [
        ForeignKey(
            entity = ReadRecordData::class,
            parentColumns = ["userId", "readMode", "bookId"],
            childColumns = ["userId", "readMode", "bookId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CountRecordData(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val elementId : Long,
    val count : Int = 0
)

fun CountRecordData.asMapper() = CountEntity(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    elementId = elementId,
    count = count
)

fun CountEntity.asData() = CountRecordData(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    elementId = elementId,
    count = count
)