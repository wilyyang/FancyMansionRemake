package com.cheesejuice.fancymansion.core.data.source.local_storage.di

import com.cheesejuice.fancymansion.core.data.source.local_storage.BookLocalStorageSource
import com.cheesejuice.fancymansion.core.data.source.local_storage.app_external.BookAppStorageSource
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