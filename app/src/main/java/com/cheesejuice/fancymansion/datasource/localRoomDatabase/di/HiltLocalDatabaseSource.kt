package com.cheesejuice.fancymansion.datasource.localRoomDatabase.di

import com.cheesejuice.fancymansion.data.interfaceDatasource.RecordLocalDatabaseSource
import com.cheesejuice.fancymansion.datasource.localRoomDatabase.impl.RecordRoomDatabaseSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Qualifier
annotation class RoomDatabase

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltRecordLocalDatabaseSource {
    @Binds
    @RoomDatabase
    abstract fun bindRecordRoomDatabaseSource(recordRoomDatabaseSource : RecordRoomDatabaseSource): RecordLocalDatabaseSource
}