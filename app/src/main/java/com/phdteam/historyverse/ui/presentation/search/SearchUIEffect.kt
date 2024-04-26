package com.phdteam.historyverse.ui.presentation.search

sealed interface SearchUIEffect {
    object SearchError : SearchUIEffect
}