package com.cheesejuice.fancymansion.core.data.source.storage.di

import com.cheesejuice.fancymansion.core.data.source.storage.BookStorageSource
import com.cheesejuice.fancymansion.core.data.source.storage.local_storage.LocalBookStorageSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Qualifier

@Qualifier
annotation class LocalBookStorage

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltBookStorageSource {

    @Binds
    @LocalBookStorage
    abstract fun bindLocalBookStorageSource(storageSource : LocalBookStorageSource): BookStorageSource
}