package com.cheesejuice.fancymansion.core.data.source.database

import androidx.room.TypeConverter
import com.cheesejuice.fancymansion.core.entity.book.ReadCount
import com.cheesejuice.fancymansion.core.entity.book.ReadData
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listReadDataToJson(value: List<ReadData>) = Gson().toJson(value)

    @TypeConverter
    fun listReadDataFromJson(json: String) = Gson().fromJson(json, Array<ReadData>::class.java).toList()

    @TypeConverter
    fun listReadCountToJson(value: List<ReadCount>) = Gson().toJson(value)

    @TypeConverter
    fun listReadCountFromJson(json: String) = Gson().fromJson(json, Array<ReadCount>::class.java).toList()
}