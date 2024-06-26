package com.phdteam.historyverse.ui.presentation.cart

import androidx.lifecycle.viewModelScope
import com.phdteam.historyverse.data.network.repositories.cartRepository.CartRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class CartViewModel(private val cartRepository: CartRepository) :
    BaseViewModel<CartUiState, CartUiEffect>(CartUiState()) {

    init {
        viewModelScope.launch {
            cartRepository.getCartItems().also { items ->
                updateState { it.copy(items = items.toUiState()) }.also {
                    updateState { uiState ->
                        uiState.copy(
                            quantity = uiState.items.size,
                            totalPrice = uiState.items.sumOf { it.price })
                    }
                }
            }
        }
    }

    fun onNextClick() {
        sendNewEffect(CartUiEffect.NavigateToPaymentScreen)
    }

    fun onDeleteItem(id: Int) {
        updateState {
            it.copy(items = it.items.filter { item -> item.id != id })
        }
    }


}