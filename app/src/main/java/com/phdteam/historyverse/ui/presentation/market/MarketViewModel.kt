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
            }, ::onSuccess, ::onError
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
                isError = true,
                errorMessage = "error retrieving data try again later"
            )
        }
        sendNewEffect(MarketUiEffect.MarketError)
    }

    fun updateSearchText(text: String) {
        updateState {
            it.copy(searchText = text)
        }
    }

    fun search() {
        val query = state.value.searchText
        // search logic
    }

    fun clearErrorState() {
        updateState { currentState ->
            currentState.copy(errorMessage = null, isError = false)
        }
    }

    fun openBottomSheet() {
        updateState {
            it.copy(isSheetVisible = true)
        }
    }

    fun closeBottomSheet() {
        updateState {
            it.copy(isSheetVisible = false)
        }
    }

    fun onFilterClick(filterItem: FilterItem) {


        updateState { uiState ->
            var newSelectedFilters: List<FilterItem> = emptyList()
            val newFilters = uiState.filters.map { filter ->
                if (filter.name == filterItem.name) {
                    val newSelectedState = !filter.isSelected
                    if (newSelectedState) {
                        newSelectedFilters =
                            uiState.selectedFilters + filter.copy(isSelected = newSelectedState)
                    } else {
                        newSelectedFilters =
                            uiState.selectedFilters.filterNot { it.name == filter.name }
                    }
                    filter.copy(isSelected = newSelectedState)
                } else {
                    filter
                }
            }
            uiState.copy(filters = newFilters, selectedFilters = newSelectedFilters)
        }
    }

    fun onItemClick(itemId: Int) {
        sendNewEffect(MarketUiEffect.NavigateToItemDetails(itemId))
    }
}