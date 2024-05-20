package com.phdteam.historyverse.ui.presentation.favorite

data class CardUiState(
    val cardTitleName: String = "",
    val favorite: Boolean = false,
    val imageUrl: String? = null,
    val ratingAvg: Double = 0.0,
    val museumName: String = "",
    val museumImageUrl: Int? = null,
    val cardId:String= "",
)
