package com.cheesejuice.di.injectDatasource

import com.cheesejuice.data.interfaceDatasource.BookLocalStorageSource
import com.cheesejuice.datasource.localAppStorage.impl.BookAppStorageSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltBookLocalStorageSource {

    @Binds
    abstract fun bindBookAppStorageSource(bookAppStorageSource : BookAppStorageSource) : BookLocalStorageSource
}