package com.phdteam.historyverse.di

import org.koin.dsl.module

fun appModule() = module {
    includes(
        AiModel,
        LocalDatabaseModule,
        NetworkModule,
        RepositoryModule,
        viewModelModule,
        DataStoreModule,
    )
}
