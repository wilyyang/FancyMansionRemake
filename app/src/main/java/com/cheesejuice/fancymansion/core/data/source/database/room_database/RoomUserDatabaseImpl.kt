package com.cheesejuice.fancymansion.core.data.source.database.room_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cheesejuice.fancymansion.core.data.source.database.room_database.dao.RoomUserDatabaseDao
import com.cheesejuice.fancymansion.core.data.source.database.model.*

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        UserData::class,
        ReadData::class,
        CountData::class
    ]
)
abstract class RoomUserDatabaseImpl : RoomDatabase() {

    abstract fun databaseDao() : RoomUserDatabaseDao
}