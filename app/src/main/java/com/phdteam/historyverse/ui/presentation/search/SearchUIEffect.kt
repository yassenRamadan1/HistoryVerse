package com.phdteam.historyverse.ui.presentation.search

sealed interface SearchUIEffect {
    object SearchError : SearchUIEffect
    data class NavigateToDetails(val id: Int) : SearchUIEffect
}