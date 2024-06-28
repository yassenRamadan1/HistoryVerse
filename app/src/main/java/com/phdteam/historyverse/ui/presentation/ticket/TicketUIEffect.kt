package com.phdteam.historyverse.ui.presentation.ticket


sealed interface TicketUIEffect {
    object TicketError : TicketUIEffect
}