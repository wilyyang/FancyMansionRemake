package com.cheesejuice.fancymansion.data.interfaceDatasource

import kotlinx.coroutines.flow.Flow

interface UserPreferencesSource {

    suspend fun getUserId() : String
    fun getUserIdFlow() : Flow<String>
    suspend fun setUserId(value : String)
}