package com.cheesejuice.fancymansion.core.data.source.local_database.room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cheesejuice.fancymansion.core.data.source.local_database.room_database.dao.RoomDatabaseDao
import com.cheesejuice.fancymansion.core.data.source.local_database.model.*

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        UserData::class,
        ReadData::class,
        CountData::class
    ]
)
abstract class RoomDatabaseImpl : RoomDatabase() {

    abstract fun databaseDao() : RoomDatabaseDao
}