package com.cheesejuice.fancymansion.core.data.repository.di

import com.cheesejuice.fancymansion.core.data.repository.MakeBookRepository
import com.cheesejuice.fancymansion.core.data.repository.ReadBookRepository
import com.cheesejuice.fancymansion.core.data.repository.UserRepository
import com.cheesejuice.fancymansion.core.data.repository.impl.MakeBookRepositoryImpl
import com.cheesejuice.fancymansion.core.data.repository.impl.ReadBookRepositoryImpl
import com.cheesejuice.fancymansion.core.data.repository.impl.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HiltRepository {

    @Binds
    fun bindMakeBookRepository(repository: MakeBookRepositoryImpl): MakeBookRepository

    @Binds
    fun bindReadBookRepository(repository: ReadBookRepositoryImpl): ReadBookRepository

    @Binds
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository
}