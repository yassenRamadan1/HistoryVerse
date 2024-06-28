package com.phdteam.historyverse.ui.presentation.ticket

data class TicketUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,

    val museumName: String = "",
    val visitorName: String = "",
    val ticketNumber: String = "",
    val date: String = "",
    val ticketType: String = "",
    )
