package com.phdteam.historyverse.ui.presentation.profile

data class ProfileUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,

    val imageUri: String = "",
    val name: String = "",
    val phone: String = "",

    )
