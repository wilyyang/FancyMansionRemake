package com.cheesejuice.fancymansion.core.data.source.local_database.di

import com.cheesejuice.fancymansion.core.data.source.local_database.LocalDatabaseSource
import com.cheesejuice.fancymansion.core.data.source.local_database.room_database.RoomDatabaseSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
annotation class RoomDatabase

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltLocalDatabaseSource {
    @Binds
    @RoomDatabase
    abstract fun bindRoomDatabaseSource(databaseSource : RoomDatabaseSource): LocalDatabaseSource
}