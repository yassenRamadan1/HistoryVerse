package com.phdteam.historyverse.ui.presentation.profile

sealed interface ProfileUIEffect {
    object ProfileError : ProfileUIEffect
}