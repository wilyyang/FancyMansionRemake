package com.cheesejuice.fancymansion.core.data.source.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import com.cheesejuice.fancymansion.core.entity.book.CountEntity
import com.cheesejuice.fancymansion.core.entity.book.ReadEntity
import com.cheesejuice.fancymansion.core.entity.book.UserEntity

@Entity
data class UserData(
    @PrimaryKey val userId : String
)

@Entity(
    primaryKeys = ["userId", "readMode", "bookId"],
    foreignKeys = [
        ForeignKey(
            entity = UserData::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = CASCADE
        )
    ]
)
data class ReadData(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val savePage : Long
)

@Entity(
    primaryKeys = ["userId", "readMode", "bookId", "elementId"],
    foreignKeys = [
        ForeignKey(
            entity = ReadData::class,
            parentColumns = ["userId", "readMode", "bookId"],
            childColumns = ["userId", "readMode", "bookId"],
            onDelete = CASCADE
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


fun UserData.asEntity() = UserEntity(
    userId = userId
)

fun ReadData.asEntity() = ReadEntity(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    savePage = savePage
)

fun CountData.asEntity() = CountEntity(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    elementId = elementId,
    count = count
)


fun UserEntity.asData() = UserData(
    userId = userId
)

fun ReadEntity.asData() = ReadData(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    savePage = savePage
)

fun CountEntity.asData() = CountData(
    userId = userId,
    readMode = readMode,
    bookId = bookId,
    elementId = elementId,
    count = count
)


