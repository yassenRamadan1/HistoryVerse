package com.phdteam.historyverse.ui.presentation.search

import com.phdteam.historyverse.ui.presentation.favorite.CardUiState
import com.phdteam.historyverse.ui.presentation.home.ArtifactUiState

data class SearchUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val searchQuery: String = "",
    val searchResults: List<ArtifactUiState> = emptyList(),
    val totalItems: List<ArtifactUiState> = emptyList()
)

fun CardUiState.toArtifactUiState() = ArtifactUiState(
    id = cardId,
    name = cardTitleName,
    imageUrl = imageUrl ?: "",
    artifactHistory = "",
)

val fake = listOf(
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
)