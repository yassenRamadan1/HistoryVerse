package com.phdteam.historyverse.ui.presentation.auth.welcome


sealed interface WelcomeUiEffect {
    data object OnClickLogin:WelcomeUiEffect
    data object OnClickSignIn:WelcomeUiEffect
    data object WelcomeError : WelcomeUiEffect

}