package com.cheesejuice.fancymansion.core.data.repository.di

import com.cheesejuice.fancymansion.core.data.repository.BookRepository
import com.cheesejuice.fancymansion.core.data.repository.UserRepository
import com.cheesejuice.fancymansion.core.data.repository.impl.BookRepositoryImpl
import com.cheesejuice.fancymansion.core.data.repository.impl.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HiltRepository {

    @Binds
    fun bindBookRepository(repository: BookRepositoryImpl): BookRepository

    @Binds
    fun bindUserRepository(repository: UserRepositoryImpl): UserRepository
}