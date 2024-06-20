package com.phdteam.historyverse.data.network.repositories

import com.phdteam.historyverse.ui.presentation.market.MarketItem

class MarketRepositoryImp : MarketRepository {
    override suspend fun fetchItems(): List<MarketItem> {
        return listOf(
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
            MarketItem(),
        )
    }

}