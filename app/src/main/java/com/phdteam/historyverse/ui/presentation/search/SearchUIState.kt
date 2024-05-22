package com.phdteam.historyverse.ui.presentation.search

import com.phdteam.historyverse.ui.presentation.favorite.CardUiState

data class SearchUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val searchQuery: String = "",
    val searchResults: List<CardUiState> = emptyList()
)

val fake = listOf(
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(),
    CardUiState(
        imageUrl = "https://egymonuments.gov.eg//media/8211/whatsapp-image-2023-10-10-at-111142-am-1.jpeg?anchor=center&mode=crop&width=1200&height=630&rnd=133566135520000000",
        museumName = "Graeco-Roman Museum",
    ),
    CardUiState(
        imageUrl = "https://egymonuments.gov.eg//media/8211/whatsapp-image-2023-10-10-at-111142-am-1.jpeg?anchor=center&mode=crop&width=1200&height=630&rnd=133566135520000000",
        museumName = "Graeco-Roman Museum",
    ),
    CardUiState(
        imageUrl = "https://egymonuments.gov.eg//media/8211/whatsapp-image-2023-10-10-at-111142-am-1.jpeg?anchor=center&mode=crop&width=1200&height=630&rnd=133566135520000000",
        museumName = "Graeco-Roman Museum",
    ),
    CardUiState(
        imageUrl = "https://egymonuments.gov.eg//media/8211/whatsapp-image-2023-10-10-at-111142-am-1.jpeg?anchor=center&mode=crop&width=1200&height=630&rnd=133566135520000000",
        museumName = "Graeco-Roman Museum",
    ),
    CardUiState(
        imageUrl = "https://egymonuments.gov.eg//media/8211/whatsapp-image-2023-10-10-at-111142-am-1.jpeg?anchor=center&mode=crop&width=1200&height=630&rnd=133566135520000000",
        museumName = "Graeco-Roman Museum",
    ),
)