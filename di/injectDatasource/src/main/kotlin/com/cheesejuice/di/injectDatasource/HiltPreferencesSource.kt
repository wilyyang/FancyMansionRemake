package com.cheesejuice.di.injectDatasource

import com.cheesejuice.data.interfaceDatasource.UserPreferencesSource
import com.cheesejuice.datasource.preferencesDatastore.UserDatastoreSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltUserPreferencesSource {

    @Binds
    abstract fun bindUserDatastoreSource(userDatastoreSource : UserDatastoreSource) : UserPreferencesSource
}