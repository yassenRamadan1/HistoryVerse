package com.phdteam.historyverse.ui.presentation.market.marketDetails

import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import com.phdteam.historyverse.ui.presentation.market.MarketUiEffect

class MarketItemDetailsViewModel(private val itemId: Int) :
    BaseViewModel<MarketItemDetailsUiState, MarketDetailsUiEffect>(MarketItemDetailsUiState()) {
    init {
        getItemDetails()
    }

    fun getItemDetails() {}
    fun addToCart(itemId: Int) {
        //
    }

    fun onReview() {
        //
        sendNewEffect(MarketDetailsUiEffect.NavigateToReview(state.value.itemId))
    }

    fun onItemClick(itemId: Int) {

    }

    fun onClickFavorite() {
        updateState { it.copy(isFavorite = !it.isFavorite) }
    }
}