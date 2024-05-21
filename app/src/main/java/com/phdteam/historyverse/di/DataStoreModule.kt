package com.phdteam.historyverse.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.phdteam.historyverse.data.local.DataStoreDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val DataStoreModule = module {
    single {
        PreferenceDataStoreFactory.create {
            androidContext().preferencesDataStoreFile("HistoryVerseDataStore")
        }
    }

    single {
        val dataStore = get<DataStore<Preferences>>()
        DataStoreDataSource(dataStore)
    }
}