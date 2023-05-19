package com.cheesejuice.fancymansion.di.diRepository

import com.cheesejuice.fancymansion.domain.interfaceRepository.MakeBookRepository
import com.cheesejuice.fancymansion.domain.interfaceRepository.ReadBookRepository
import com.cheesejuice.fancymansion.domain.interfaceRepository.UserRepository
import com.cheesejuice.fancymansion.data.repository.MakeBookRepositoryImpl
import com.cheesejuice.fancymansion.data.repository.ReadBookRepositoryImpl
import com.cheesejuice.fancymansion.data.repository.UserRepositoryImpl
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