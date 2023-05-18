package com.cheesejuice.fancymansion.data.source.local_database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import com.cheesejuice.fancymansion.data.repository.mapper.user.ReadEntity

@Entity(
    tableName = "ReadRecord",
    primaryKeys = ["userId", "readMode", "bookId"],
    foreignKeys = [
        ForeignKey(
            entity = UserInfoData::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ReadRecordData(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val savePage : Long
)

fun ReadRecordData.asMapper() = ReadEntity(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    savePage = savePage
)

fun ReadEntity.asData() = ReadRecordData(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    savePage = savePage
)