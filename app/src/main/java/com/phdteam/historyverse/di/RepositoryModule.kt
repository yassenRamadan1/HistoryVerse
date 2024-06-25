package com.phdteam.historyverse.di

import com.phdteam.historyverse.data.network.repositories.ChatBotRepositoryImpl
import com.phdteam.historyverse.data.network.repositories.AuthRepository
import com.phdteam.historyverse.data.network.repositories.AuthRepositoryImpl
import com.phdteam.historyverse.data.network.repositories.ChatBotRepository
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepositoryImp
import com.phdteam.historyverse.data.network.repositories.MarketRepository
import com.phdteam.historyverse.data.network.repositories.MarketRepositoryImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val RepositoryModule = module {
    singleOf(::HistoryVerseRepositoryImp) { bind<HistoryVerseRepository>() }
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    singleOf(::MarketRepositoryImp) { bind<MarketRepository>() }
    singleOf(::ChatBotRepositoryImpl) { bind<ChatBotRepository>() }
}