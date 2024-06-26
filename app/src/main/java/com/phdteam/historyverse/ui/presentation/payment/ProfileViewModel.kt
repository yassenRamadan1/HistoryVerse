package com.phdteam.historyverse.ui.presentation.payment

import android.net.Uri
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import com.phdteam.historyverse.ui.presentation.profile.ProfileUIEffect
import com.phdteam.historyverse.ui.presentation.profile.ProfileUIState
import kotlinx.coroutines.delay

class PaymentViewModel(
    private val historyVerseRepository: HistoryVerseRepository
) : BaseViewModel<PaymentUIState, PaymentUIEffect>(PaymentUIState()) {

    init {
        onMakeRequest()
    }
    fun onChangeCardName(userName: String) {
        updateState { it.copy(cardName = userName) }
    }
    fun onChangeCardNumber(userName: String) {
        updateState { it.copy(cardNumber = userName) }
    }
    fun onChangeValidDate(userName: String) {
        updateState { it.copy(validDate = userName) }
    }
    fun onChangeCvv(userName: String) {
        updateState { it.copy(cvv = userName) }
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
            PaymentUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(PaymentUIEffect.PaymentError)
    }


}