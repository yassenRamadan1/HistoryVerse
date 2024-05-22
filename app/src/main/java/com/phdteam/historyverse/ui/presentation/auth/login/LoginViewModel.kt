package com.phdteam.historyverse.ui.presentation.auth.login

import androidx.lifecycle.viewModelScope
import com.phdteam.historyverse.data.network.repositories.AuthRepository
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.data.network.repositories.UserRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository,
) : BaseViewModel<LoginUIState, LoginUIEffect>(LoginUIState()) {

    private fun onSuccess(token: String) {
        viewModelScope.launch {
            authRepository.setSignInState(true)
            authRepository.setUserToken(token)
        }
        updateState {
            it.copy(
                isSuccess = true,
                isError = false,
                isLoading = false,
            )
        }
    }

    private fun onLoading() {
        updateState {
            it.copy(
                isLoading = true,
                isSuccess = false,
                errorMessage = "",
                isError = false
            )
        }
    }

    private fun onError(errorMessage: String) {
        updateState {
            LoginUIState(
                isError = true,
                isLoading = false,
                isSuccess = false,
                errorMessage = errorMessage
            )
        }
        sendNewEffect(LoginUIEffect.LoginError)
    }

    fun onChangeUserName(userName: String) {
        updateState { it.copy(email = userName) }
    }

    fun onChangePassword(password: String) {
        updateState { it.copy(password = password) }
    }

    fun onClickLogin() {
        viewModelScope.launch {
            try {
                onLoading()
                val result = authRepository.signIn(state.value.email, state.value.password)
                result.user?.uid?.let { onSuccess(it) }
            } catch (e: Exception) {
                onError(e.message ?: "error")
            }
        }
    }

    fun clearErrorState() {
        updateState { currentState ->
            currentState.copy(errorMessage = null, isError = false)
        }
    }
}
