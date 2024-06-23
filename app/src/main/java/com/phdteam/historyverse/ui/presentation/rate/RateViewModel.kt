package com.phdteam.historyverse.ui.presentation.rate

import com.phdteam.historyverse.ui.presentation.base.BaseViewModel

class RateViewModel(private val itemId: Int) :
    BaseViewModel<RateUiState, RateUiEffect>(RateUiState()) {

    fun onCommentChange(comment: String) {
        updateState { it.copy(comment = comment) }
    }

    fun onRateChange(rate: Int) {
        updateState { it.copy(rate = rate) }
    }

    fun onPostClick() {

    }

    private fun onPostSuccess() {
        updateState { it.copy(isRateSubmitted = true) }
        sendNewEffect(RateUiEffect.OnRateSubmitted)
    }

    private fun onPostError() {
        updateState { it.copy(isError = true) }
    }

    fun onBackPressed() {
        sendNewEffect(RateUiEffect.OnBackPresses)
    }
}