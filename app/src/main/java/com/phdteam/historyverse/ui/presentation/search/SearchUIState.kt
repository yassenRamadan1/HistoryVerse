package com.phdteam.historyverse.ui.presentation.search

import com.phdteam.historyverse.ui.presentation.favorite.CardUiState

data class SearchUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val searchQuery: String = "",
    val searchResults: List<CardUiState> = emptyList()
)

 val fake = listOf(
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
)