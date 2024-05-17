package com.phdteam.historyverse.ui.presentation.favorite

import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.delay

class FavoriteViewModel (
    private val historyVerseRepository: HistoryVerseRepository
) : BaseViewModel<FavoriteUiState, FavoriteUiEffect>(FavoriteUiState()) {

    init {
        onMakeRequest()
        getCardInfoLocal()
    }
    private fun getCardInfoLocal() {
        updateState {
            it.copy(
                card = listOf(
                    CardUiState(
                        cardTitleName = "Product",
                        favorite = true,
                        imageUrl = R.drawable.hvlogo,
                        ratingAvg = 4.0,
                        museumName = "Alex Museum",
                        museumImageUrl =  R.drawable.hvlogo
                    ),
                    CardUiState(
                        cardTitleName = "Alex Musuem",
                        favorite = true,
                        imageUrl = R.drawable.hvlogo,
                        ratingAvg = 3.4,
                        museumName = "China town",
                        museumImageUrl =  R.drawable.hvlogo
                    ),
                    CardUiState(
                        cardTitleName = "Artifact",
                        favorite = true,
                        imageUrl = R.drawable.hvlogo,
                        ratingAvg = 1.5,
                        museumName ="Cairo Museum",
                        museumImageUrl =  R.drawable.hvlogo
                    ),
                )
            )
        }
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