package com.cheesejuice.datasource.preferencesDatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.cheesejuice.data.interfaceDatasource.UserPreferencesSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

const val NOT_ASSIGN_USER_ID = "NOT_ASSIGN_USER_ID"

@Singleton
class UserDatastoreSource @Inject constructor(
    @ApplicationContext private val context : Context
) : UserPreferencesSource {

    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "main")

    private val keyUserId = stringPreferencesKey("KEY_USER_ID")

    override suspend fun getUserId() : String = context.dataStore.data.first()[keyUserId] ?: NOT_ASSIGN_USER_ID

    override fun getUserIdFlow() : Flow<String> = context.dataStore.data.map { preferences ->
        preferences[keyUserId] ?: NOT_ASSIGN_USER_ID
    }

    override suspend fun setUserId(value : String){
        context.dataStore.edit { pref ->
            pref[keyUserId] = value
        }
    }
}