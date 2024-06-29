package com.phdteam.historyverse.ui.presentation.home

import com.phdteam.historyverse.ui.presentation.seeall.SeeAllType

sealed interface HomeUIEffect {
    data object HomeError : HomeUIEffect

    data class NavigateToSeeAll(val type: SeeAllType) : HomeUIEffect

    data object NavigateToMarket : HomeUIEffect

    data object NavigateToChatBoot : HomeUIEffect

    data class NavigateToDetail(val id: Int) : HomeUIEffect
    data class NavigateToArtifactDetails(val id: Int) : HomeUIEffect
}