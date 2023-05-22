package com.cheesejuice.di.injectDatasource

import com.cheesejuice.data.interfaceDatasource.RecordLocalDatabaseSource
import com.cheesejuice.datasource.localRoomDatabase.impl.RecordRoomDatabaseSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltRecordLocalDatabaseSource {
    @Binds
    abstract fun bindRecordRoomDatabaseSource(recordRoomDatabaseSource : RecordRoomDatabaseSource) : RecordLocalDatabaseSource
}