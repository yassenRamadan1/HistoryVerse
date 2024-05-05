package com.phdteam.historyverse.ui.presentation.auth.signin

data class SignInUiState(
    val isSignInSuccessful: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val userName: String = "",
    val password: String = "",
    val email: String = "",
    val userId: Int? = null,
    val isScreenContinue: Boolean = true,
)
