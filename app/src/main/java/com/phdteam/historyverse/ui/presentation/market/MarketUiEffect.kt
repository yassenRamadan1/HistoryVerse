package com.phdteam.historyverse.ui.presentation.market

sealed interface MarketUiEffect {
    data object MarketError : MarketUiEffect
    data class NavigateToItemDetails(val id: Int) : MarketUiEffect
    data object OpenBottomSheet : MarketUiEffect
}