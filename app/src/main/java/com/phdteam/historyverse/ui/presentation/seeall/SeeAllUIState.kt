package com.phdteam.historyverse.ui.presentation.seeall

import com.phdteam.historyverse.ui.presentation.home.ArtifactUiState
import com.phdteam.historyverse.ui.presentation.home.MuseumUiState

data class SeeAllUIState(
    val type: SeeAllType = SeeAllType.NoThing,
    val museums: List<MuseumUiState> = emptyList(),
    val artifacts: List<ArtifactUiState> = emptyList(),

    val isLoading: Boolean = false,
    val isError: Boolean = false,
)


enum class SeeAllType(val value: String) {
    Artifacts("Artifacts"),
    Museums("Museums"),
    NoThing("")
}

fun String.toSeeAllType(): SeeAllType {
    return when (this) {
        SeeAllType.Artifacts.value -> SeeAllType.Artifacts
        SeeAllType.Museums.value -> SeeAllType.Museums
        else -> SeeAllType.NoThing
    }
}