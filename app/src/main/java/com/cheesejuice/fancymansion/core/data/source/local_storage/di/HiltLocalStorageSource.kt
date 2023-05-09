package com.cheesejuice.fancymansion.core.data.source.local_storage.di

import com.cheesejuice.fancymansion.core.data.source.local_storage.LocalStorageSource
import com.cheesejuice.fancymansion.core.data.source.local_storage.app_external.AppExternalStorageSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Qualifier

@Qualifier
annotation class AppExternalStorage

@Module
@InstallIn(SingletonComponent::class)
abstract class HiltLocalStorageSource {

    @Binds
    @AppExternalStorage
    abstract fun bindAppExternalStorageSource(storageSource : AppExternalStorageSource): LocalStorageSource
}