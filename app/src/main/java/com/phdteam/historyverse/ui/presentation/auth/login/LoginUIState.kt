package com.phdteam.historyverse.ui.presentation.auth.login

data class LoginUIState(
    val email: String = "",
    val password: String = "",
    val userId:Int = 0,
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)
