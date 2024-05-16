package com.phdteam.historyverse.ui.presentation.favorite

data class FavoriteUiState(

    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,

    val name: String = "",
    val favorite: Boolean = false,
    val imageUrl: Int? = null,
    val rating: Int? = null,
    val museumName: String = "",
    val museumImageUrl: Int? = null,

    )
