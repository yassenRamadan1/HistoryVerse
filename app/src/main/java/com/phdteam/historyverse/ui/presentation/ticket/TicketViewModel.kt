package com.phdteam.historyverse.ui.presentation.ticket

import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import com.phdteam.historyverse.ui.presentation.tickets.FakeTickets
import kotlinx.coroutines.delay

class TicketViewModel(
    private val id: Int? ,
    private val historyVerseRepository: HistoryVerseRepository
) : BaseViewModel<TicketScreenUIState, TicketUIEffect>(TicketScreenUIState()) {

    init {
        onMakeRequest()
    }

    private fun onMakeRequest() {
        updateState { it.copy(isLoading = true) }

        tryToExecute(
            {
                delay(1900)
                updateState { it.copy(isLoading = false, isSuccess = true) }
            },
            { onSuccess() },
            ::onError
        )
    }


    private fun onSuccess() {
        updateState {
            it.copy(
                isSuccess = true,
                isError = false,
                isLoading = false,
                ticket = FakeTickets.find { it.ticketId == id } ?: Ticket()
            )
        }
    }

    private fun onError() {
        updateState {
            TicketScreenUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(TicketUIEffect.TicketError)
    }


}