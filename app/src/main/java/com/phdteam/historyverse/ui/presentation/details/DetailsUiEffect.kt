package com.phdteam.historyverse.ui.presentation.details

interface DetailsUiEffect {
    data class NavigateToReview(val museumId: Int) : DetailsUiEffect
    data class NavigateToProductDetails(val itemId: Int) : DetailsUiEffect
    data class NavigateToArtifactDetails(val itemId: Int) : DetailsUiEffect
}