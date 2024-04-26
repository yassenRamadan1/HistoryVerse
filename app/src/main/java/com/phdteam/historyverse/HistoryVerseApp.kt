package com.phdteam.historyverse

import android.app.Application
import com.phdteam.historyverse.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HistoryVerseApp: Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@HistoryVerseApp)
            modules(appModule())
        }
    }
}