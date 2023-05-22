package com.cheesejuice.di.injectRepository

import com.cheesejuice.data.repository.MakeBookRepositoryImpl
import com.cheesejuice.data.repository.ReadBookRepositoryImpl
import com.cheesejuice.data.repository.UserRepositoryImpl
import com.cheesejuice.domain.interfaceRepository.MakeBookRepository
import com.cheesejuice.domain.interfaceRepository.ReadBookRepository
import com.cheesejuice.domain.interfaceRepository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface HiltRepository {

    @Binds
    fun bindMakeBookRepository(repository : MakeBookRepositoryImpl) : MakeBookRepository

    @Binds
    fun bindReadBookRepository(repository : ReadBookRepositoryImpl) : ReadBookRepository

    @Binds
    fun bindUserRepository(repository : UserRepositoryImpl) : UserRepository
}