package com.cheesejuice.fancymansion.core.data.source.local_database.di

import android.content.Context
import androidx.room.Room
import com.cheesejuice.fancymansion.core.data.source.local_database.room_database.RoomDatabaseImpl
import com.cheesejuice.fancymansion.core.data.source.local_database.room_database.dao.RoomDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltRoomDatabase {
    @Singleton
    @Provides
    fun provideRoomDatabaseImpl(
        @ApplicationContext context : Context
    ) = Room.databaseBuilder(context, RoomDatabaseImpl::class.java, "main").build()

    @Singleton
    @Provides
    fun provideRoomDatabaseDao(databaseImpl : RoomDatabaseImpl) : RoomDatabaseDao
    = databaseImpl.databaseDao()
}