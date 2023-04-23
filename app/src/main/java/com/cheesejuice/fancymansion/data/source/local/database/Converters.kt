package com.cheesejuice.fancymansion.data.source.local.database

import androidx.room.TypeConverter
import com.cheesejuice.fancymansion.data.model.ReadCount
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listReadCountToJson(value: List<ReadCount>) = Gson().toJson(value)

    @TypeConverter
    fun listReadCountFromJson(json: String) = Gson().fromJson(json, Array<ReadCount>::class.java).toList()
}