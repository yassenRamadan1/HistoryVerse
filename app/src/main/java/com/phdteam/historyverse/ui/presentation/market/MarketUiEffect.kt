package com.phdteam.historyverse.ui.presentation.market

sealed interface MarketUiEffect {
    data object MarketError : MarketUiEffect
    data object navigateToItemDetails : MarketUiEffect

}