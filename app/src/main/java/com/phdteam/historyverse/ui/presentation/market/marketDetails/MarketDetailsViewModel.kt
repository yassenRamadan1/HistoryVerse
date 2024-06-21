package com.phdteam.historyverse.ui.presentation.market.marketDetails

import com.phdteam.historyverse.ui.presentation.base.BaseViewModel

class MarketDetailsViewModel :
    BaseViewModel<MarketItemDetailsUiState, MarketDetailsUiEffect>(MarketItemDetailsUiState()) {
    init {
        getItemDetails()
    }

    fun getItemDetails() {}
    fun addToCart(itemId: Int) {
        //
    }

    fun onReview(rate: Int) {
        //
    }

    fun onItemClick(itemId: Int){

    }
}