package com.phdteam.historyverse.ui.presentation.search

data class SearchUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
)
