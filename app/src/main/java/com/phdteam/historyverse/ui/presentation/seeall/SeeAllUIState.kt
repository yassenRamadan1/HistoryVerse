package com.phdteam.historyverse.ui.presentation.seeall

import com.phdteam.historyverse.ui.presentation.home.ArtifactUiState
import com.phdteam.historyverse.ui.presentation.home.UniversityUiState

data class SeeAllUIState(
    val type: SeeAllType = SeeAllType.NoThing,
    val universities: List<UniversityUiState> = emptyList(),
    val artifacts: List<ArtifactUiState> = emptyList(),

    val isLoading: Boolean = false,
    val isError: Boolean = false,
)


enum class SeeAllType(val value: String) {
    Mentors("Mentors"),
    Universities("Universities"),
    NoThing("")
}

fun String.toSeeAllType(): SeeAllType {
    return when (this) {
        SeeAllType.Mentors.value -> SeeAllType.Mentors
        SeeAllType.Universities.value -> SeeAllType.Universities
        else -> SeeAllType.NoThing
    }
}