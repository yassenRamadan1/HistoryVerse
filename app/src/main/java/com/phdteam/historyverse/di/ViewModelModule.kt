package com.phdteam.historyverse.di

import com.phdteam.historyverse.ui.presentation.auth.login.LoginViewModel
import com.phdteam.historyverse.ui.presentation.home.HomeViewModel
import com.phdteam.historyverse.ui.presentation.search.SearchViewModel
import com.phdteam.historyverse.ui.presentation.profile.ProfileViewModel
import com.phdteam.historyverse.ui.presentation.auth.welcome.WelcomeViewModel
import com.phdteam.historyverse.ui.presentation.auth.signin.SignInViewModel
import com.phdteam.historyverse.ui.presentation.favorite.FavoriteViewModel
import com.phdteam.historyverse.ui.presentation.details.DetailsViewModel
import com.phdteam.historyverse.ui.presentation.seeall.SeeAllViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SeeAllViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::SearchViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::DetailsViewModel)
    viewModelOf(::WelcomeViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::FavoriteViewModel)
}