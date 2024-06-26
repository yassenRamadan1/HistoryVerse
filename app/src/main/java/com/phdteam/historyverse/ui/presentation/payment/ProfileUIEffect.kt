package com.phdteam.historyverse.ui.presentation.payment

import com.phdteam.historyverse.ui.presentation.profile.ProfileUIEffect

sealed interface PaymentUIEffect {
    object PaymentError : PaymentUIEffect
}