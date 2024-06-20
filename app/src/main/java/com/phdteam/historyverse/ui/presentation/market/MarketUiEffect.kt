package com.phdteam.historyverse.ui.presentation.market

sealed interface MarketUiEffect {
    data object MarketError : MarketUiEffect
    data object NavigateToItemDetails : MarketUiEffect
    data object OpenBottomSheet : MarketUiEffect
}