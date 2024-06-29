package com.phdteam.historyverse.ui.presentation.market

import com.phdteam.historyverse.data.network.model.Artifact
import com.phdteam.historyverse.ui.presentation.market.Category.Cat
import com.phdteam.historyverse.ui.presentation.market.Category.Chinese
import com.phdteam.historyverse.ui.presentation.market.Category.Egyptian
import com.phdteam.historyverse.ui.presentation.market.Category.Pharaohs
import com.phdteam.historyverse.ui.presentation.market.Category.Pyramids
import com.phdteam.historyverse.ui.presentation.market.Category.Roman

data class MarketUiState(
    val items: List<MarketItem> = emptyList(),
    val selectedFilters: List<FilterItem> = emptyList(),
    val filters: List<FilterItem> = FixedFilters,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isError: Boolean = false,
    val searchText: String = "",
    val isSheetVisible: Boolean = false,
    val filteredItems: List<MarketItem> = emptyList()
)

private val FixedFilters = listOf(
    FilterItem(name = "Egyptian", type = MarketFilterType.Culture, category = Egyptian),
    FilterItem(name = "Roman", type = MarketFilterType.Culture, category = Roman),
    FilterItem(name = "Chinese", type = MarketFilterType.Culture, category = Chinese),
    FilterItem(name = "pharaohs", type = MarketFilterType.Categories, category = Pharaohs),
    FilterItem(name = "pyramids", type = MarketFilterType.Categories, category = Pyramids),
    FilterItem(name = "cat", type = MarketFilterType.Categories, category = Cat),
//    FilterItem(name = "ABDU", type = MarketFilterType.Categories),
//    FilterItem(name = "ana t3bt", type = MarketFilterType.Categories),
)

data class MarketItem(
    val name: String = "Tikana",
    val price: String = "25 EGP",
    val image: String = "https://static01.nyt.com/images/2020/08/14/arts/14museums-reopening-2/14museums-reopening-2-videoSixteenByNineJumbo1600.jpg",
    val rating: Double = 3.5,
    val shopName: String = "shop name",
    val shopImage: String = "https://static01.nyt.com/images/2020/08/14/arts/14museums-reopening-2/14museums-reopening-2-videoSixteenByNineJumbo1600.jpg",
    val id: Int = 0,
    val categories: List<Category> = listOf(Egyptian)
)

fun Artifact.toMarketItem() = MarketItem(
    name = name ?: "",
    price = "200 EGP",
    image = artifactImageUrl ?: "",
    rating = 4.0,
    shopName = "",
    shopImage = "https://static01.nyt.com/images/2020/08/14/arts/14museums-reopening-2/14museums-reopening-2-videoSixteenByNineJumbo1600.jpg",
    id = id ?: 0,
)

data class FilterItem(
    val name: String = "",
    val type: MarketFilterType = MarketFilterType.Categories,
    val isSelected: Boolean = false,
    val category: Category = Egyptian
)