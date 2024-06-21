package com.phdteam.historyverse.ui.presentation.rate

sealed interface RateUiEffect {
    data object OnBackPresses : RateUiEffect
    data object OnRateSubmitted : RateUiEffect
    data object OnRateError : RateUiEffect
}