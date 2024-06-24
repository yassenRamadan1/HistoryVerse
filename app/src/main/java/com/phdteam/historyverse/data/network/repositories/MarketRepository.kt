package com.phdteam.historyverse.data.network.repositories

import com.phdteam.historyverse.ui.presentation.market.MarketItem

interface MarketRepository {
    suspend fun fetchItems(): List<MarketItem>
}