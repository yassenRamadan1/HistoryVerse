package com.phdteam.historyverse.ui.presentation.tickets

import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.data.network.model.Museum
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import com.phdteam.historyverse.ui.presentation.details.toDetailsUiState
import com.phdteam.historyverse.ui.presentation.details.toMuseumDetailsUiState
import com.phdteam.historyverse.ui.presentation.favorite.CardUiState
import com.phdteam.historyverse.ui.presentation.market.MarketUiEffect
import com.phdteam.historyverse.ui.presentation.ticket.TicketUIEffect
import com.phdteam.historyverse.ui.presentation.ticket.TicketUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

class TicketsViewModel(
    private val id: Int?,
    private val historyVerseRepository: HistoryVerseRepository
) : BaseViewModel<TicketsUIState, TicketsUIEffect>(TicketsUIState()) {

    init {
        onMakeRequest()
//        getLocalTickets(id)
    }
//    fun getItemById(id: Int?) {
//        updateState { it.copy(isLoading = true) }
//        tryToExecute(
//            {
//                val result = getLocalTickets(id)
//                onSuccess(result)
//            },
//            { onSuccess() },
//            ::onError
//        )
//    }
//    private fun getLocalTickets(id: Int?): TicketsUIState {
//        val tickets = listOf(
//            TicketUIState(
//                ticketId = 1,
//                museumName = "British Museum",
//                visitorName = "Alex",
//                locationName = "London",
//                ticketNumber = "123456",
//                visitDate = "12/12/2021",
//                ticketType = "Regular"
//            ),
//            TicketUIState(
//                ticketId = 2,
//                museumName = "Louvre Museum",
//                visitorName = "John",
//                locationName = "Paris",
//                ticketNumber = "123457",
//                visitDate = "12/12/2021",
//                ticketType = "Regular"
//            ),
//            TicketUIState(
//                ticketId = 3,
//                museumName = "Hermitage Museum",
//                visitorName = "Mike",
//                locationName = "Saint Petersburg",
//                ticketNumber = "123458",
//                visitDate = "12/12/2021",
//                ticketType = "Regular"
//            )
//        )
//
////        return tickets.find { it.ticketId == id }
//    }

    fun onTicketClick(itemId: Int) {
        sendNewEffect(TicketsUIEffect.NavigateToTicket(itemId))
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
            )
        }
    }
//    private fun onSuccess(id: TicketsUIState) {
//        updateState { it.copy(ticket= id.ticket) }
//    }

    private fun onError() {
        updateState {
            TicketsUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(TicketsUIEffect.TicketError)
    }


}