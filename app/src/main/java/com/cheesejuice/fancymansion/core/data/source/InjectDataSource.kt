package com.cheesejuice.fancymansion.core.data.source

import android.content.Context
import com.cheesejuice.fancymansion.core.data.source.database.DatabaseSource
import com.cheesejuice.fancymansion.core.data.source.database.dao.DatabaseDao
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
    fun provideDatabaseSource(
        @ApplicationContext context : Context
    ) : DatabaseSource = DatabaseSource.getDataBase(context)

    @Singleton
    @Provides
    fun provideDatabaseDao(databaseSource : DatabaseSource) : DatabaseDao
    = databaseSource.databaseDao()
}