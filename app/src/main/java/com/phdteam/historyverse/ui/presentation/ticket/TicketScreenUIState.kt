package com.phdteam.historyverse.ui.presentation.ticket

data class TicketScreenUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,
    val ticket: Ticket = Ticket(),

    )

data class Ticket(
    val ticketId: Int = 0,
    val museumName: String = "Alexandria Aquarium",
    val visitorName: String = "Abdelrahman",
    val locationName: String = "Alexandria",
    val ticketNumber: String = "264857",
    val visitDate: String = "29/6/2024",
    val ticketType: String = "Adult",
)