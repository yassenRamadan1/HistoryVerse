package com.phdteam.historyverse.ui.presentation.auth.welcome

import androidx.lifecycle.viewModelScope
import com.phdteam.historyverse.data.network.repositories.AuthRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class WelcomeViewModel(
    private val authRepository: AuthRepository
) : BaseViewModel<WelcomeUiState, WelcomeUiEffect>(WelcomeUiState()) {
    init {
        onSuccess()
        viewModelScope.launch {
            authRepository.checkSignInState().let {
                if (it) {
                    sendNewEffect(WelcomeUiEffect.onSignedIn)
                }
            }
        }
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

    fun onClickLogin() {
        sendNewEffect(WelcomeUiEffect.OnClickLogin)
    }

    fun onClickSignIn() {
        sendNewEffect(WelcomeUiEffect.OnClickSignIn)
    }

}