package com.phdteam.historyverse.data.network.repositories

import com.phdteam.historyverse.ui.presentation.market.MarketItem
import com.phdteam.historyverse.ui.presentation.market.marketItems

class MarketRepositoryImp : MarketRepository {
    override suspend fun fetchItems(): List<MarketItem> {
        return marketItems
    }

}