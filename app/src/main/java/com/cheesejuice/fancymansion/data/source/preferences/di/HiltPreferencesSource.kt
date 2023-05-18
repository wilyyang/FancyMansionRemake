package com.cheesejuice.fancymansion.data.source.preferences.di

import com.cheesejuice.fancymansion.data.source.preferences.UserPreferencesSource
import com.cheesejuice.fancymansion.data.source.preferences.datastore.UserDatastoreSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Qualifier

@Qualifier
annotation class DataStore

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltUserPreferencesSource{

    @Binds
    @DataStore
    abstract fun bindUserDatastoreSource(userDatastoreSource : UserDatastoreSource): UserPreferencesSource
}