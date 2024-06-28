package com.phdteam.historyverse.ui.presentation.ticket

import android.os.Parcelable

data class TicketUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isSuccess: Boolean = false,

    val ticketId: Int? = null,
    val museumName: String = "",
    val visitorName: String = "",
    val locationName: String = "",
    val ticketNumber: String = "",
    val visitDate: String = "",
    val ticketType: String = "",
    )