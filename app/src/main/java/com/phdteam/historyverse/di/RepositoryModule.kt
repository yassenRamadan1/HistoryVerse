package com.phdteam.historyverse.di

import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepositoryImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val RepositoryModule = module {
    singleOf(::HistoryVerseRepositoryImp) { bind<HistoryVerseRepository>() }
}