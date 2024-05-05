package com.phdteam.historyverse.ui.presentation.auth.welcome

import com.phdteam.historyverse.ui.presentation.base.BaseViewModel

class WelcomeViewModel(
) : BaseViewModel<WelcomeUiState, WelcomeUiEffect>(WelcomeUiState()) {
    init {
        onSuccess()
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
     fun onClickLogin(){
        sendNewEffect(WelcomeUiEffect.OnClickLogin)
    }
     fun onClickSignIn(){
        sendNewEffect(WelcomeUiEffect.OnClickSignIn)
    }

}