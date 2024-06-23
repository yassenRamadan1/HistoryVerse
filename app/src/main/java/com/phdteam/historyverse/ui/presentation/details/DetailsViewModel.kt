package com.phdteam.historyverse.ui.presentation.details

import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel

class DetailsViewModel(
    private val repository: HistoryVerseRepository
) : BaseViewModel<DetailsUiState, DetailsUiEffect>(DetailsUiState())
{
    init {

    }


    fun onBookClick() {
        // TODO
    }

    fun onMakeReview(review: Int) {
        updateState {
            it.copy(review = review)
        }
    }

    fun onArtifactClick(artifactId: String) {
        // TODO
    }

    fun onFavoriteClick(itemId: String) {
        // TODO
    }

    fun onProductClick(itemId: String) {
        // TODO
    }

}