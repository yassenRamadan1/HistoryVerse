package com.phdteam.historyverse.ui.presentation.market

import com.phdteam.historyverse.data.network.repositories.MarketRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel

class MarketViewModel(
    private val marketRepository: MarketRepository
) : BaseViewModel<MarketUiState, MarketUiEffect>(MarketUiState()) {
    init {
        fetchItems()
    }

    private fun fetchItems() {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            {
                marketRepository.fetchItems()

            }, ::onSuccess, {}
        )
    }

    private fun onSuccess(items: List<MarketItem>) {
        updateState {
            it.copy(
                isLoading = false,
                items = items
            )
        }
    }

    private fun onError() {
        updateState {
            MarketUiState(
                isLoading = false,
            )
        }
        sendNewEffect(MarketUiEffect.MarketError)
    }

    fun updateSearchText(text: String) {
        updateState {
            it.copy(searchText = text)
        }
    }

    fun search(query:String) {
        // search logic
    }
}