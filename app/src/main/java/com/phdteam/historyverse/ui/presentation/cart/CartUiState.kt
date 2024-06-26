package com.phdteam.historyverse.ui.presentation.cart

import com.phdteam.historyverse.data.network.model.CartItem

data class CartUiState(
    val items: List<CartItemUiState> = Items,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val quantity: Int = 0,
    val totalPrice: Double = 0.0,
)

data class CartItemUiState(
    val id: Int = 0,
    val name: String = "Vissage",
    val price: Double = 1500.0,
    val imageUrl: String = ""
)

private val Items = listOf(
    CartItemUiState(),
    CartItemUiState(),
    CartItemUiState()
)

fun CartItem.toUiState() = CartItemUiState(
    id = id,
    name = name,
    price = price,
    imageUrl = image
)

fun List<CartItem>.toUiState() = map { it.toUiState() }