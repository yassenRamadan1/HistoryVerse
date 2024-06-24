package com.phdteam.historyverse.data.network.repositories.marketItemDetails

interface MarketItemDetailsRepository {
    suspend fun fetchItemDetails(itemId: Int)
}