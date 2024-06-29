package com.phdteam.historyverse.ui.presentation.search

import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import com.phdteam.historyverse.ui.presentation.home.toArtifactUiState

class SearchViewModel(
    private val historyVerseRepository: HistoryVerseRepository
) : BaseViewModel<SearchUIState, SearchUIEffect>(SearchUIState()) {

    init {
        onMakeRequest()
    }

    private fun onMakeRequest() {
        updateState { it.copy(isLoading = true) }

        tryToExecute(
            {
//                delay(1900)
                historyVerseRepository.getArtifacts()
            },
            ::onSuccess,
            ::onError
        )
    }


    private fun onSuccess(items: List<Artifact>) {
        updateState {
            it.copy(
                isSuccess = true,
                isError = false,
                isLoading = false,
                searchResults = items.toArtifactUiState().shuffled(),
                totalItems = items.toArtifactUiState()
            )
        }
    }

    private fun onError() {
        updateState {
            SearchUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(SearchUIEffect.SearchError)
    }

    fun onSearchQueryChange(query: String) {
        if (query.isBlank()) {
            updateState {
                it.copy(searchQuery = query, searchResults = it.totalItems)
            }
            return
        }
        updateState {
            it.copy(
                searchQuery = query,
                searchResults = state.value.totalItems.filter { item ->
                    item.name.startsWith(
                        prefix = query,
                        ignoreCase = true
                    )
                }
            )
        }

    }

    fun onItemClick(id: Int) {
        sendNewEffect(SearchUIEffect.NavigateToDetails(id))
    }
}