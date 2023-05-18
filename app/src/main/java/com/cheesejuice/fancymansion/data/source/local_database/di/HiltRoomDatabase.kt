package com.cheesejuice.fancymansion.data.source.local_database.di

import android.content.Context
import com.cheesejuice.fancymansion.data.source.local_database.room_database.RoomDatabaseHelper
import com.cheesejuice.fancymansion.data.source.local_database.room_database.dao.RoomDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltRoomDatabase {

    @Singleton
    @Provides
    fun provideRoomDatabaseHelper(
        @ApplicationContext context : Context
    ) = RoomDatabaseHelper.getDataBase(context, CoroutineScope(SupervisorJob()))

    @Singleton
    @Provides
    fun provideRoomDatabaseDao(databaseHelper : RoomDatabaseHelper) : RoomDatabaseDao = databaseHelper.databaseDao()
}