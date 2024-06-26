package com.phdteam.historyverse.ui.presentation.payment

data class PaymentUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,

    val cardName: String = "",
    val cardNumber: String = "",
    val validDate: String = "",
    val cvv: String = "",

    )
