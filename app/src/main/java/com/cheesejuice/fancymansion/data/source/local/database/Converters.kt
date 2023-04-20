package com.cheesejuice.fancymansion.data.source.local.database

import androidx.room.TypeConverter
import com.cheesejuice.fancymansion.data.source.local.database.model.ReadCount
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    @TypeConverter
    fun listReadCountToJson(value: List<ReadCount>) = Json.encodeToString(value)

    @TypeConverter
    fun jsonToListReadCount(value: String) = Json.decodeFromString<List<ReadCount>>(value)
}