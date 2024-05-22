package com.phdteam.historyverse.ui.presentation.profile

import android.net.Uri
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.phdteam.historyverse.data.network.model.User
import com.phdteam.historyverse.data.network.repositories.AuthRepository
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.data.network.repositories.UserRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val historyVerseRepository: HistoryVerseRepository,
    private val userRepository: UserRepository,
    private val authRepository: AuthRepository

) : BaseViewModel<ProfileUIState, ProfileUIEffect>(ProfileUIState()) {

    init {
        viewModelScope.launch {
            authRepository.getUserToken().also {
                Log.d("token", it)
                onMakeRequest(it)
            }

        }
    }

    fun onUpdateImageUri(uri: Uri) {
        updateState { oldState ->
            oldState.copy(imageUri = uri.toString())
        }
    }

    //saving image to api
//    fun saveImageToApi() {
//        val imageUri = state.value.imageUri
//        if (imageUri.isNotBlank()) {
//            viewModelScope.launch {
//                try {
//                    val response = "my response from repository"
//                    if (response.isSuccessful) {
//                        // Handle successful response
//                    } else {
//                        // Handle error
//                    }
//                } catch (e: Exception) {
//                    // Handle exception
//                }
//            }
//        }
//    }


    private fun onMakeRequest(token: String) {
        updateState { it.copy(isLoading = true) }

        tryToExecute(
            {
                userRepository.getUSerData(token)

            },
            ::onSuccess,
            ::onError
        )
    }


    private fun onSuccess(user: User) {
        updateState {
            it.copy(
                name = user.username,
                isSuccess = true,
                isError = false,
                isLoading = false,
            )
        }
    }

    private fun onError() {
        updateState {
            ProfileUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(ProfileUIEffect.ProfileError)
    }

    fun onLogOut() {
        viewModelScope.launch {
            authRepository.signOut()
            sendNewEffect(ProfileUIEffect.ProfileLogOut)
        }
    }

}