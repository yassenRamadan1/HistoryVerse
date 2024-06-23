package com.phdteam.historyverse.ui.presentation.market.marketDetails

interface MarketDetailsUiEffect {
    data object OnMarketDetailsError : MarketDetailsUiEffect
    data class NavigateToMarketDetails(val id: Int) : MarketDetailsUiEffect
    data class NavigateToReview(val id: Int) : MarketDetailsUiEffect
}