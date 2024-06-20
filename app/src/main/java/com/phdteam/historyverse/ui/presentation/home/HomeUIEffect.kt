package com.phdteam.historyverse.ui.presentation.home

import com.phdteam.historyverse.ui.presentation.seeall.SeeAllType

sealed interface HomeUIEffect {
    data object HomeError : HomeUIEffect

    data class NavigateToSeeAll(val type: SeeAllType) : HomeUIEffect

    data class NavigateToMentorProfile(val id: String) : HomeUIEffect
    data class NavigateToUniversityProfile(val id: String) : HomeUIEffect

    data object NavigateToNotification : HomeUIEffect

    data object NavigateToChatBooks : HomeUIEffect

    data class NavigateToSubject(val id: String) : HomeUIEffect

    data class NavigateToDetail(val id: Int) : HomeUIEffect
}