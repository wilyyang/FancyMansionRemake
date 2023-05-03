package com.cheesejuice.fancymansion.core.data.source.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.cheesejuice.fancymansion.core.data.source.database.dao.DatabaseDao
import com.cheesejuice.fancymansion.core.entity.book.ReadCount
import com.cheesejuice.fancymansion.core.entity.book.ReadData
import com.cheesejuice.fancymansion.core.entity.book.UserData

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        UserData::class,
        ReadData::class,
        ReadCount::class
    ]
)
@TypeConverters(Converters::class)
abstract class DatabaseSource : RoomDatabase() {

    abstract fun databaseDao() : DatabaseDao

    companion object {

        @Volatile
        private var instance : DatabaseSource? = null

        fun getDataBase(context : Context) : DatabaseSource {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(context, DatabaseSource::class.java, "main").build()
                instance = database
                database
            }
        }
    }
}