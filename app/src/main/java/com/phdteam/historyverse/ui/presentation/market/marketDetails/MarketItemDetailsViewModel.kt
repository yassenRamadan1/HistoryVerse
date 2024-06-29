package com.phdteam.historyverse.ui.presentation.market.marketDetails

import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import com.phdteam.historyverse.ui.presentation.market.toMarketItem

class MarketItemDetailsViewModel(
    private val itemId: Int?,
    private val repository: HistoryVerseRepository
) :
    BaseViewModel<MarketItemDetailsUiState, MarketDetailsUiEffect>(MarketItemDetailsUiState()) {

    init {
        getItemDetails()
        getArtifacts()
    }

    private fun getArtifacts() {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            {
                repository.getArtifacts()
            }, ::onSuccessArtifacts,
            ::onError
        )
    }

    private fun onSuccessArtifacts(artifacts: List<Artifact>) {
        updateState { it.copy(isLoading = true) }
        updateState {
            it.copy(
                similarProducts = artifacts.map { it.toMarketItem() }.shuffled(),
                isLoading = false
            )
        }
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
        updateState { it.copy(itemState = artifact.toMarketItemState(), isLoading = false) }
    }

    fun addToCart(itemId: Int) {
        //
    }

    fun onReview() {
        //
        sendNewEffect(MarketDetailsUiEffect.NavigateToReview(state.value.itemState.itemId))
    }

    fun onItemClick(itemId: Int) {

    }

    fun onClickFavorite() {
        updateState { it.copy(isFavorite = !it.isFavorite) }
    }
}