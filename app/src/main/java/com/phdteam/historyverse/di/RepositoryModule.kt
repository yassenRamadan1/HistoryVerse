package com.phdteam.historyverse.di

import com.phdteam.historyverse.data.network.repositories.UserRepository
import com.phdteam.historyverse.data.network.repositories.UserRepositoryImpl
import com.phdteam.historyverse.data.network.repositories.AuthRepository
import com.phdteam.historyverse.data.network.repositories.AuthRepositoryImpl
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepositoryImp
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val RepositoryModule = module {
    singleOf(::HistoryVerseRepositoryImp) { bind<HistoryVerseRepository>() }
    singleOf(::AuthRepositoryImpl) { bind<AuthRepository>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
}