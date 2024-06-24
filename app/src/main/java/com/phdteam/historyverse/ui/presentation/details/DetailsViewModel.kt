package com.phdteam.historyverse.ui.presentation.details

import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import com.phdteam.historyverse.ui.presentation.home.HomeUIEffect
import com.phdteam.historyverse.ui.presentation.home.HomeUIState
import com.phdteam.historyverse.ui.presentation.home.toArtifactUiState

class DetailsViewModel (
    private val id : Int?,
    private val repository: HistoryVerseRepository
)  :
    BaseViewModel<DetailsUiState, DetailsUiEffect>(DetailsUiState()) {

        init {
            getData()
        }

    private fun getData() {
        updateState { it.copy(isLoading = true) }
        getArtifacts()
        getMuseums()
    }

    private fun getMuseumById() {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            repository::getMuseumById(),
            ::onSuccessMuseums,
            ::onError
        )
    }
    private fun getArtifacts() {
        updateState { it.copy(isLoading = true)}
        tryToExecute(
            repository::getArtifacts,
            ::onSuccessArtifacts,
            ::onError
        )
    }

    private fun onSuccessArtifacts(artifact: List<Artifact>) {
        updateState { it.copy(artifacts = artifact.toArtifactUiState(), isLoading = false) }
        updateState { it.copy(recommendedArtifacts = artifact.toArtifactUiState().shuffled(), isLoading = false) }
        updateState { it.copy(mostPopularArtifacts = artifact.toArtifactUiState().shuffled(), isLoading = false) }
    }

    fun onBookClick() {
        // TODO
    }

    fun onMakeReview() {
        sendNewEffect(DetailsUiEffect.NavigateToReview(state.value.museum.museumId))
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

    private fun onError() {
        updateState { DetailsUiState(isError = true, isLoading = false) }
    }

}