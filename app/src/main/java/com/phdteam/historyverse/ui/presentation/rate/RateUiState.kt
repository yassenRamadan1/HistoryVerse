package com.phdteam.historyverse.ui.presentation.rate

data class RateUiState(
    val rate: Int = 0,
    val isRateSubmitted: Boolean = false,
    val isError: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val isRateSuccess: Boolean = false,
    val comment:String = "",
)
