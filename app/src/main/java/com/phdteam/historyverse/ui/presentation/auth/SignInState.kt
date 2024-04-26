package com.phdteam.historyverse.ui.presentation.auth

data class SignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null
)
