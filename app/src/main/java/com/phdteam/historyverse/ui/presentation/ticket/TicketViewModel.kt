package com.phdteam.historyverse.ui.presentation.ticket

import androidx.lifecycle.viewModelScope
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import com.phdteam.historyverse.ui.presentation.payment.PaymentUIEffect
import com.phdteam.historyverse.ui.presentation.payment.PaymentUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class TicketViewModel(
    private val historyVerseRepository: HistoryVerseRepository
) : BaseViewModel<TicketUIState, TicketUIEffect>(TicketUIState()) {

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
            TicketUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(TicketUIEffect.TicketError)
    }


}