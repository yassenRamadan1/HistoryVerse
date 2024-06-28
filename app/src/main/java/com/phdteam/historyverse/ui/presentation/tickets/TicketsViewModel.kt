package com.phdteam.historyverse.ui.presentation.tickets

import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

class TicketsViewModel(
    private val historyVerseRepository: HistoryVerseRepository
) : BaseViewModel<TicketsUIState, TicketsUIEffect>(TicketsUIState()) {

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
            )
        }
    }

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