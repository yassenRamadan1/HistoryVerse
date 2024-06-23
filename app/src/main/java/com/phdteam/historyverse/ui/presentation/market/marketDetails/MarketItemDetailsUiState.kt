package com.phdteam.historyverse.ui.presentation.market.marketDetails

import com.phdteam.historyverse.ui.presentation.details.Review
import com.phdteam.historyverse.ui.presentation.details.ReviewTabState
import com.phdteam.historyverse.ui.presentation.market.MarketItem

data class MarketItemDetailsUiState(
    val isLoading: Boolean = false,
    val name: String = "item name",
    val description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
    val imageUrl: String = "https://static01.nyt.com/images/2020/08/14/arts/14museums-reopening-2/14museums-reopening-2-videoSixteenByNineJumbo1600.jpg",
    val errorMessage: String? = null,
    val isFavorite: Boolean = true,
    val shopImage: String = "https://static01.nyt.com/images/2020/08/14/arts/14museums-reopening-2/14museums-reopening-2-videoSixteenByNineJumbo1600.jpg",
    val price: String = "100 EGP",
    val rating: Float = 3.5f,
    val similarProducts: List<MarketItem> = itemsList,
    val shopName: String = "louvre museum",
    val itemId: Int = 0,
    val reviewTabState: ReviewTabState = ReviewTabState(),
    val isError: Boolean = false,
)

private val itemsList = listOf(
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
    MarketItem(),
)
