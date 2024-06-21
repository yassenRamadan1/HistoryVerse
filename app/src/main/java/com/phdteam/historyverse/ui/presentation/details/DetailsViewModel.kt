package com.phdteam.historyverse.ui.presentation.details

import com.phdteam.historyverse.ui.presentation.base.BaseViewModel

class DetailsViewModel : BaseViewModel<MuseumDetailsUiState, DetailsUiEffect>(MuseumDetailsUiState()) {

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