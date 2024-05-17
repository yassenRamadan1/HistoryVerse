package com.phdteam.historyverse.ui.presentation.favorite

import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import com.phdteam.historyverse.ui.presentation.profile.ProfileUIEffect
import com.phdteam.historyverse.ui.presentation.profile.ProfileUIState
import kotlinx.coroutines.delay

class FavoriteViewModel (
    private val historyVerseRepository: HistoryVerseRepository
) : BaseViewModel<FavoriteUiState, FavoriteUiEffect>(FavoriteUiState()) {
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
            FavoriteUiState(
                isError = true,
                isLoading = false,
                isSuccess = false
            )
        }
        sendNewEffect(FavoriteUiEffect.FavoriteError)
    }

}