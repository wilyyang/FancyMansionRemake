package com.cheesejuice.fancymansion.datasource.localRoomDatabase.impl

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.cheesejuice.core.common.LOCAL_USER_ID
import com.cheesejuice.fancymansion.data.mapper.user.UserInfoMapper
import com.cheesejuice.fancymansion.datasource.localRoomDatabase.impl.dao.RoomDatabaseDao
import com.cheesejuice.fancymansion.datasource.localRoomDatabase.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    version = 1,
    exportSchema = true,
    entities = [
        UserInfoData::class,
        ReadRecordData::class,
        CountRecordData::class
    ]
)
abstract class RoomDatabaseHelper : RoomDatabase() {

    abstract fun databaseDao() : RoomDatabaseDao

    companion object {

        @Volatile
        private var instance : RoomDatabaseHelper? = null

        fun getDataBase(contextApplication : Context, scope : CoroutineScope) : RoomDatabaseHelper {
            return instance ?: synchronized(this) {
                val database = Room.databaseBuilder(contextApplication, RoomDatabaseHelper::class.java, "main")
                    .addCallback(CallbackDatabaseMain(scope))
                    .build()
                instance = database
                database
            }
        }

        private class CallbackDatabaseMain(
            private val scope : CoroutineScope,
        ) : Callback() {
            override fun onCreate(db : SupportSQLiteDatabase) {
                super.onCreate(db)
                instance?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        initMain(database.databaseDao())
                    }
                }
            }
        }

        suspend fun initMain(databaseDao : RoomDatabaseDao) {
            databaseDao.insertUserInfo(UserInfoMapper(userId = LOCAL_USER_ID).asData())
        }
    }
}