package com.phdteam.historyverse.data.network.repositories.cartRepository

import com.phdteam.historyverse.data.network.model.CartItem

interface CartRepository {

    suspend fun getCartItems() :List<CartItem>
    suspend fun deleteItemFromCart(id:Int)
}