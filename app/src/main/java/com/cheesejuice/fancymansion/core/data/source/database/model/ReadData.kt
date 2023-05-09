package com.cheesejuice.fancymansion.core.data.source.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import com.cheesejuice.fancymansion.core.entity.user.ReadEntity

@Entity(
    primaryKeys = ["userId", "readMode", "bookId"],
    foreignKeys = [
        ForeignKey(
            entity = UserData::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ReadData(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val savePage : Long
)

fun ReadData.asEntity() = ReadEntity(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    savePage = savePage
)

fun ReadEntity.asData() = ReadData(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    savePage = savePage
)