package com.phdteam.historyverse.di

import org.koin.dsl.module

fun appModule() = module {
    includes(
        LocalDatabaseModule,
        NetworkModule,
        RepositoryModule,
        viewModelModule,
        DataStoreModule
    )
}
