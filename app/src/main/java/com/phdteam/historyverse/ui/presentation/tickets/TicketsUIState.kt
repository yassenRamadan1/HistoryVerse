package com.phdteam.historyverse.ui.presentation.tickets

import com.phdteam.historyverse.ui.presentation.favorite.CardUiState
import com.phdteam.historyverse.ui.presentation.ticket.TicketUIState

data class TicketsUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,

    val ticket: List<TicketUIState> = mutableListOf()
    )
