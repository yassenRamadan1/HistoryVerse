package com.phdteam.historyverse.ui.presentation.payment

sealed interface PaymentUIEffect {
    object PaymentError : PaymentUIEffect
}