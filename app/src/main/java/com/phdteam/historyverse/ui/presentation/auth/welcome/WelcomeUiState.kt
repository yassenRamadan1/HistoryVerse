package com.phdteam.historyverse.ui.presentation.auth.welcome

data class WelcomeUiState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)