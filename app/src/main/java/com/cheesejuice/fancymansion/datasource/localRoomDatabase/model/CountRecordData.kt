package com.cheesejuice.fancymansion.datasource.localRoomDatabase.model

import androidx.room.Entity
import androidx.room.ForeignKey
import com.cheesejuice.fancymansion.data.mapper.user.CountRecordMapper

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

fun CountRecordData.asMapper() = CountRecordMapper(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    elementId = elementId,
    count = count
)

fun CountRecordMapper.asData() = CountRecordData(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    elementId = elementId,
    count = count
)