package com.phdteam.historyverse.ui.presentation.favorite

data class FavoriteUiState(

    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,

    val card: List<CardUiState> = emptyList(),


    )
