package com.phdteam.historyverse.ui.presentation.tickets


sealed interface TicketsUIEffect {
    object TicketError : TicketsUIEffect
}