package com.cheesejuice.fancymansion.core.entity.book

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

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
data class ReadCount(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val elementId : Long,
    val count : Int = 0
)