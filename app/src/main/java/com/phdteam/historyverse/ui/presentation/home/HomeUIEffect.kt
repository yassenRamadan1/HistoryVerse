package com.phdteam.historyverse.ui.presentation.home

sealed interface HomeUIEffect {
    object HomeError : HomeUIEffect
}