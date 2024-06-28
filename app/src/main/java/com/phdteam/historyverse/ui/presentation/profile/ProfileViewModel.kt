package com.phdteam.historyverse.ui.presentation.profile

import android.net.Uri
import com.google.firebase.auth.FirebaseAuth
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.delay

class ProfileViewModel(
    private val historyVerseRepository: HistoryVerseRepository
) : BaseViewModel<ProfileUIState, ProfileUIEffect>(ProfileUIState()) {

    init {
        onMakeRequest()
    }
    fun onUpdateImageUri(uri: Uri) {
        updateState { oldState ->
            oldState.copy(imageUri = uri.toString())
        }
    }
    fun signOut() {
        FirebaseAuth.getInstance().signOut()
        sendNewEffect(ProfileUIEffect.NavigateToLogin)
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
            ProfileUIState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(ProfileUIEffect.ProfileError)
    }


}