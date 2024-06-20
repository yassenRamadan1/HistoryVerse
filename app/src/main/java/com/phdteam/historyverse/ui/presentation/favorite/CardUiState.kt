package com.phdteam.historyverse.ui.presentation.favorite

data class CardUiState(
    val cardTitleName: String = "Anwana",
    val favorite: Boolean = false,
    val imageUrl: String? = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    val ratingAvg: Double = 0.0,
    val museumName: String = "",
    val museumImageUrl: Int? = null,
    val cardId:String= "",
)
