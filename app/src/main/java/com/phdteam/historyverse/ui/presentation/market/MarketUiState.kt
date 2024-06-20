package com.phdteam.historyverse.ui.presentation.market

data class MarketUiState(
    val items: List<MarketItem> = emptyList(),
    val selectedFilters: List<FilterItem> = emptyList(),
    val filters: List<FilterItem> = FixedFilters,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isError: Boolean = false,
    val searchText: String = "",
    val isSheetVisible: Boolean = false,

    )

private val FixedFilters = listOf(
    FilterItem(name = "Egyptian", type = MarketFilterType.Culture),
    FilterItem(name = "Roman", type = MarketFilterType.Culture),
    FilterItem(name = "Chinese", type = MarketFilterType.Culture),
    FilterItem(name = "pharaohs", type = MarketFilterType.Categories),
    FilterItem(name = "pyramids", type = MarketFilterType.Categories),
    FilterItem(name = "cat", type = MarketFilterType.Categories),
    FilterItem(name = "ABDU", type = MarketFilterType.Categories),
    FilterItem(name = "ana t3bt", type = MarketFilterType.Categories),
)

data class MarketItem(
    val name: String = "Tikana",
    val price: String = "25 EGP",
    val image: String = "https://static01.nyt.com/images/2020/08/14/arts/14museums-reopening-2/14museums-reopening-2-videoSixteenByNineJumbo1600.jpg",
    val rating: Double = 3.5,
    val shopName: String = "shop name",
    val shopImage: String = "https://static01.nyt.com/images/2020/08/14/arts/14museums-reopening-2/14museums-reopening-2-videoSixteenByNineJumbo1600.jpg",
)

data class FilterItem(
    val name: String = "",
    val type: MarketFilterType = MarketFilterType.Categories,
    val isSelected: Boolean = false,
)