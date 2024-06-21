package com.phdteam.historyverse.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.map

class DataStoreDataSource(private val dataStore: DataStore<Preferences>) {
    suspend fun getUserSignInState(): Boolean {
        return dataStore.data.mapLatest {
            it[USER_SIGN_IN_STATE] ?: false
        }.first()
    }

    suspend fun setUserSignInState(signInState:Boolean){
        dataStore.edit {
            it[USER_SIGN_IN_STATE] = signInState
        }
    }

    companion object {
        val USER_SIGN_IN_STATE = booleanPreferencesKey("user_sign_in_state")
    }

}