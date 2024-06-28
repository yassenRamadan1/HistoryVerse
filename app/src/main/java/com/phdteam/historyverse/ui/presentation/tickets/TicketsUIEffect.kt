package com.phdteam.historyverse.ui.presentation.tickets

import com.phdteam.historyverse.ui.presentation.ticket.TicketUIEffect


sealed interface TicketsUIEffect {
    object TicketError : TicketsUIEffect
    data class NavigateToTicket(val id: Int) : TicketsUIEffect
}