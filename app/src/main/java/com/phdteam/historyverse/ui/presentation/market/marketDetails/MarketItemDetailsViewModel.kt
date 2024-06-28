package com.phdteam.historyverse.ui.presentation.market.marketDetails

import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel

class MarketItemDetailsViewModel(
    private val itemId: Int?,
    private val repository: HistoryVerseRepository
) :
    BaseViewModel<MarketItemDetailsUiState, MarketDetailsUiEffect>(MarketItemDetailsUiState()) {

    init {
        getItemDetails()
    }

    fun getItemDetails() {
        tryToExecute(
            {
                repository.getArtifactById(itemId ?: 2352)
            }, ::onSuccess,
            ::onError
        )
    }

    private fun onError() {
        updateState { it.copy(isError = true, isLoading = false) }
    }

    fun onSuccess(artifact: Artifact) {
//        updateState { it.copy(artifact = artifact.toMarketItemDetailsUiState()) }
    }

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