package com.phdteam.historyverse.ui.presentation.payment

import androidx.lifecycle.viewModelScope
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val historyVerseRepository: HistoryVerseRepository
) : BaseViewModel<PaymentUIState, PaymentUIEffect>(PaymentUIState()) {

    init {
        onMakeRequest()

    }

    private fun onClickGoNext() {
        viewModelScope.launch {
            delay(2000)
            updateState { it.copy(showWarning = true) }
        }
    }

    fun onClickNext() {
        viewModelScope.launch {
            if (validateCardName(state.value.cardName) &&
                validateCardNumber(state.value.cardNumber) &&
                validateValidDate(state.value.validDate) &&
                validateCvv(state.value.cvv)
            ) {
                onClickGoNext()
            } else {
                sendNewEffect(PaymentUIEffect.PaymentError)
            }
        }
    }

    fun onClickGoBack() {
        updateState { it.copy(showWarning = false) }
    }

    fun onChangeCardName(cardName: String) {
        updateState { it.copy(cardName = cardName) }
    }

    fun onChangeCardNumber(cardNumber: String) {
        updateState { it.copy(cardNumber = cardNumber) }
    }

    fun onChangeValidDate(validDate: String) {
        updateState { it.copy(validDate = validDate) }
    }

    private fun validateCardName(cardName: String): Boolean {
        return cardName
            .all { it.isLetter() }
    }

    private fun validateCardNumber(cardNumber: String): Boolean {
        return cardNumber
            .length == 16 && cardNumber
            .all { it.isDigit() }
    }

    private fun validateValidDate(validDate: String): Boolean {
        val parts = validDate.split("/")
        if (parts.size != 2) return false

        val month = parts[0].toIntOrNull()
        val year = parts[1].toIntOrNull()

        val currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) % 100
        val currentMonth = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH) + 1
        val maxYear = (java.util.Calendar.getInstance().get(java.util.Calendar.YEAR) + 10) % 100

        return if (year == currentYear) {
            month in currentMonth..12
        } else {
            month in 1..12
        } && year in currentYear..maxYear
    }

    private fun validateCvv(cvv: String): Boolean {
        return cvv.length == 3 && cvv.all { it.isDigit() }
    }

    fun onChangeCvv(cvv: String) {
        updateState { it.copy(cvv = cvv) }
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