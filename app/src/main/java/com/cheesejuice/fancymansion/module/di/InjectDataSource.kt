package com.cheesejuice.fancymansion.module.di

import android.content.Context
import com.cheesejuice.fancymansion.data.source.local.database.DatabaseDao
import com.cheesejuice.fancymansion.data.source.local.database.DatabaseSource
import com.cheesejuice.fancymansion.data.source.local.storage.StorageSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InjectDataSource {
    @Singleton
    @Provides
    fun provideStorageSource(
        @ApplicationContext context : Context
    ) : StorageSource = StorageSource(context)

    @Singleton
    @Provides
    fun provideDatabaseSource(
        @ApplicationContext context : Context
    ) : DatabaseSource = DatabaseSource.getDataBase(context)

    @Singleton
    @Provides
    fun provideDatabaseDao(databaseSource : DatabaseSource) : DatabaseDao
    = databaseSource.databaseDao()
}