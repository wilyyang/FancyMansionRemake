package com.cheesejuice.fancymansion.data.source.local.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity
data class UserData(
    @PrimaryKey val userId : String
)

@Entity(
    primaryKeys = ["userId", "bookId"],
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
    val bookId : String,
    val savePage : Long
)

@Entity(
    primaryKeys = ["userId", "bookId", "elementId"],
    foreignKeys = [
        ForeignKey(
            entity = ReadData::class,
            parentColumns = ["userId", "bookId"],
            childColumns = ["userId", "bookId"],
            onDelete = CASCADE
        )
    ]
)
data class ReadCount(
    val userId : String,
    val bookId : String,
    val elementId : Long,
    val count : Int = 0
)