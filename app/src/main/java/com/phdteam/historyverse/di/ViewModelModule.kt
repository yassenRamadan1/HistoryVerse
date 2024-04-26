package com.phdteam.historyverse.di

import com.phdteam.historyverse.ui.presentation.login.LoginViewModel
import com.phdteam.historyverse.ui.presentation.home.HomeViewModel
import com.phdteam.historyverse.ui.presentation.search.SearchViewModel
import com.phdteam.historyverse.ui.presentation.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::ProfileViewModel)
}