package com.phdteam.historyverse.ui.presentation.trip

import com.phdteam.historyverse.data.entity.MuseumType

data class TripUiState(
    val museums: List<MuseumType> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
)
