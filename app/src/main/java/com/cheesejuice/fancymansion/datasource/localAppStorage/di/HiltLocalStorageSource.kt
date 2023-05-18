package com.cheesejuice.fancymansion.datasource.localAppStorage.di

import com.cheesejuice.fancymansion.data.interfaceDatasource.BookLocalStorageSource
import com.cheesejuice.fancymansion.datasource.localAppStorage.impl.BookAppStorageSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Qualifier

@Qualifier
annotation class AppStorage

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltBookLocalStorageSource{

    @Binds
    @AppStorage
    abstract fun bindBookAppStorageSource(bookAppStorageSource : BookAppStorageSource): BookLocalStorageSource
}