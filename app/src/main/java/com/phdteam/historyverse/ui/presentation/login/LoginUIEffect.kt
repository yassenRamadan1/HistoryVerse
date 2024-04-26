package com.phdteam.historyverse.ui.presentation.login

sealed interface LoginUIEffect {
    object LoginError : LoginUIEffect
}