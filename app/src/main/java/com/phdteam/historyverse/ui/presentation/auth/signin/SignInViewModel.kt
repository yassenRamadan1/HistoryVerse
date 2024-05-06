package com.phdteam.historyverse.ui.presentation.auth.signin

import androidx.lifecycle.viewModelScope
import com.phdteam.historyverse.data.network.model.User
import com.phdteam.historyverse.data.network.repositories.AuthRepository
import com.phdteam.historyverse.data.network.response.SignInResult
import com.phdteam.historyverse.data.network.utils.UserAlreadyExistsException
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRepository: AuthRepository,
) : BaseViewModel<SignInUiState, SignInUIEffect>(SignInUiState()) {

    fun onSignInResult(result: SignInResult) {
        updateState {
            it.copy(
                isSignInSuccessful = result.data != null,
                errorMessage = result.errorMessage
            )
        }
    }

    private fun onSuccess() {
        updateState {
            it.copy(
                isSignInSuccessful = true,
                isError = false,
                isLoading = false,
            )
        }
    }

    private fun onLoading() {
        updateState {
            it.copy(
                isLoading = true,
                isSignInSuccessful = false,
                errorMessage = "",
                isError = false
            )
        }
    }

    private fun onError(errorMessage: String) {
        updateState {
            SignInUiState(
                isError = true,
                isLoading = false,
                isSignInSuccessful = false,
                errorMessage = errorMessage
            )
        }
    }


    fun onClickSignUp() {
        if (
            validateEmail(state.value.email)
            && validatePassword(state.value.password)
            && validateUserName(state.value.userName)
        ) {
            onSignUP()
        } else {
            updateState {
                it.copy(
                    isError = true,
                    errorMessage = "email, password or username is not invalid "
                )
            }
        }
    }
    private fun onSignUP() {
        viewModelScope.launch {
            try {
                onLoading()
                val result =
                    authRepository.signUp(state.value.email, state.value.password)
                val userInfo = User(
                    id = result.user?.uid,
                    username = state.value.userName,
                    email = state.value.email,
                )
                    authRepository.addUserInfo(userInfo = userInfo, user = result.user!!)
                    onSuccess()

            } catch (e: UserAlreadyExistsException) {
                onError(e.message ?: "error")
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

    fun onChangeUserName(userName: String) {
        updateState { it.copy(userName = userName) }
    }

    fun onChangePassword(password: String) {
        updateState { it.copy(password = password) }
    }

    fun onChangeEmail(email: String) {
        updateState { it.copy(email = email) }
    }

    private fun validateEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun validatePassword(password: String): Boolean {
        return password.length >= 6
    }

    private fun validateUserName(userName: String): Boolean {
        return userName.length >= 6
    }

}
