package com.phdteam.historyverse.ui.presentation.market.marketDetails

import com.phdteam.historyverse.ui.presentation.details.Review
import com.phdteam.historyverse.ui.presentation.details.ReviewTabState
import com.phdteam.historyverse.ui.presentation.market.MarketItem

data class MarketItemDetailsUiState(
    val isLoading: Boolean = false,
    val name: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val errorMessage: String? = null,
    val isFavorite: Boolean = false,
    val shopImage: String = "",
    val price: String = "",
    val rating: Float = 0f,
    val similarProducts: List<MarketItem> = emptyList(),
    val shopName: String = "",
    val itemId: Int = 0,
    val reviewTabState: ReviewTabState = ReviewTabState(),
    val isError: Boolean = false,
)
