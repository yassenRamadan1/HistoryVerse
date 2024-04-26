package com.phdteam.historyverse.di

import androidx.room.Room
import com.phdteam.historyverse.data.local.database.HistoryVerseDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val LocalDatabaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), HistoryVerseDataBase::class.java, "MindfulMentorDatabase")
            .build()
    }

    single {
        val database = get<HistoryVerseDataBase>()
        database.historyVerseDao()
    }
}