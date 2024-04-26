package com.phdteam.historyverse.ui.presentation.login

data class LoginUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)
