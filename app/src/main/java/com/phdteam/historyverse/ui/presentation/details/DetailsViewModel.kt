package com.phdteam.historyverse.ui.presentation.details

import androidx.lifecycle.viewModelScope
import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.data.network.model.Museum
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import com.phdteam.historyverse.ui.presentation.home.toArtifactUiState
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val id: Int?,
    private val repository: HistoryVerseRepository
) :
    BaseViewModel<DetailsScreenUiState, DetailsUiEffect>(DetailsScreenUiState()) {

    init {
        updateState { it.copy(isLoading = true) }
        getData()
    }

    private fun getData() {
        updateState { it.copy(isLoading = true) }
        getArtifacts()
        getMuseumById(id?:281)
        getArtifactById(id?:2352)
        updateState { it.copy(isLoading = false) }
    }

    private fun getMuseumById(id: Int) {
        updateState { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val result = repository.getMuseumById(id)
                onSuccessMuseums(result)
            } catch (e: Exception) {
                onError()
            } finally {
                updateState { it.copy(isLoading = false) }
            }
        }
    }

    private fun onSuccessMuseums(museum: Museum) {
        updateState { it.copy(museum = museum.toMuseumDetailsUiState()) }
        if (id!! < 2000){
            updateState { it.copy(details = museum.toDetailsUiState() , isLoading = false) }
        }
    }
    private fun getArtifactById(id: Int) {
        updateState { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val result = repository.getArtifactById(id)
                onSuccessArtifact(result)
            } catch (e: Exception) {
                onError()
            } finally {
                updateState { it.copy(isLoading = false) }
            }
        }
    }

    private fun onSuccessArtifact(artifact: Artifact) {
        updateState { it.copy(artifactDetails = artifact.toArtifactDetailsUiState()) }
        if (id!! > 2000){
            updateState { it.copy(details = artifact.toDetailsUiState() , isLoading = false) }
        }
    }

    private fun getArtifacts() {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            repository::getArtifacts,
            ::onSuccessArtifacts,
            ::onError
        )
    }

    private fun onSuccessArtifacts(artifact: List<Artifact>) {
        updateState { it.copy(artifacts = artifact.toArtifactUiState(), isLoading = false) }
        updateState {
            it.copy(
                recommendedArtifacts = artifact.toArtifactUiState().shuffled(),
                isLoading = false
            )
        }
        updateState {
            it.copy(
                mostPopularArtifacts = artifact.toArtifactUiState().shuffled(),
                isLoading = false
            )
        }
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
        updateState { DetailsScreenUiState(isError = true, isLoading = false) }
    }

}