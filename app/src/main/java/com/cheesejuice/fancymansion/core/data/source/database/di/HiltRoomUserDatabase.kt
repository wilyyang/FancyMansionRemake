package com.cheesejuice.fancymansion.core.data.source.database.di

import android.content.Context
import androidx.room.Room
import com.cheesejuice.fancymansion.core.data.source.database.room_database.RoomUserDatabaseImpl
import com.cheesejuice.fancymansion.core.data.source.database.room_database.dao.RoomUserDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HiltRoomUserDatabase {
    @Singleton
    @Provides
    fun provideRoomUserDatabaseImpl(
        @ApplicationContext context : Context
    ) = Room.databaseBuilder(context, RoomUserDatabaseImpl::class.java, "main").build()

    @Singleton
    @Provides
    fun provideRoomUserDatabaseDao(userDatabaseImpl : RoomUserDatabaseImpl) : RoomUserDatabaseDao
    = userDatabaseImpl.databaseDao()
}