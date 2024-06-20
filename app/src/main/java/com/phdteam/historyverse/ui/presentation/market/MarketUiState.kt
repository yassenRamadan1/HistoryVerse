package com.phdteam.historyverse.ui.presentation.market

data class MarketUiState(
    val items: List<MarketItem> = emptyList(),
    val selectedFilters: List<FilterItem> = emptyList(),
    val filters: List<FilterItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchText: String = ""
)

data class MarketItem(
    val name: String = "",
    val price: String = "",
    val image: String = "",
    val rating: Double = 0.0,
    val shopName: String = "",
    val shopImage: String = "",
)

data class FilterItem(
    val filter: String = "",
    val type: MarketFilterType
)