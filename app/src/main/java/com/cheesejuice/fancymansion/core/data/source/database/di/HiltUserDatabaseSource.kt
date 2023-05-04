package com.cheesejuice.fancymansion.core.data.source.database.di

import com.cheesejuice.fancymansion.core.data.source.database.UserDatabaseSource
import com.cheesejuice.fancymansion.core.data.source.database.room_database.RoomUserDatabaseSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
annotation class RoomUserDatabase

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltUserDatabaseSource {
    @Binds
    @RoomUserDatabase
    abstract fun bindRoomUserDatabaseSource(databaseSource : RoomUserDatabaseSource): UserDatabaseSource
}