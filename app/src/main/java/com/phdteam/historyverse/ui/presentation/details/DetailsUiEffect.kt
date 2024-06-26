package com.phdteam.historyverse.ui.presentation.details

interface DetailsUiEffect {
    data class NavigateToReview(val museumId: Int) : DetailsUiEffect
}