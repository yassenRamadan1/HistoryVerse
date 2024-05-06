package com.phdteam.historyverse.ui.presentation.auth.login

sealed interface LoginUIEffect {
    data object LoginError : LoginUIEffect
}
