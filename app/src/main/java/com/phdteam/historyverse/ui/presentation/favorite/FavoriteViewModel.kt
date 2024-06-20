package com.phdteam.historyverse.ui.presentation.favorite

import com.phdTeam.HistoryVerse.R
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import kotlinx.coroutines.delay

class FavoriteViewModel(
    private val historyVerseRepository: HistoryVerseRepository
) : BaseViewModel<FavoriteUiState, FavoriteUiEffect>(FavoriteUiState()) {

    init {
        onMakeRequest()
        getCardInfoLocal()
    }

    private fun getCardInfoLocal() {
        updateState {
            it.copy(
                cards = listOf(
                    CardUiState(
                        cardTitleName = "Product",
                        favorite = true,
                        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        ratingAvg = 4.0,
                        museumName = "Alex Museum",
                        museumImageUrl = R.drawable.hvlogo,
                        cardId = "1"
                    ),
                    CardUiState(
                        cardTitleName = "Alex Musuem",
                        favorite = true,
                        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        ratingAvg = 3.4,
                        museumName = "China town",
                        museumImageUrl = R.drawable.hvlogo,
                        cardId = "2"
                    ),
                    CardUiState(
                        cardTitleName = "Artifact",
                        favorite = true,
                        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        ratingAvg = 1.5,
                        museumName = "Cairo Museum",
                        museumImageUrl = R.drawable.hvlogo,
                        cardId = "3"
                    ),
                    CardUiState(
                        cardTitleName = "Product",
                        favorite = true,
                        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        ratingAvg = 4.0,
                        museumName = "Alex Museum",
                        museumImageUrl = R.drawable.hvlogo,
                        cardId = "4"
                    ),
                    CardUiState(
                        cardTitleName = "Product",
                        favorite = true,
                        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        ratingAvg = 4.0,
                        museumName = "Alex Museum",
                        museumImageUrl = R.drawable.hvlogo,
                        cardId = "5"
                    ),
                    CardUiState(
                        cardTitleName = "Product",
                        favorite = true,
                        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        ratingAvg = 4.0,
                        museumName = "Alex Museum",
                        museumImageUrl = R.drawable.hvlogo,
                        cardId = "6"
                    ),
                    CardUiState(
                        cardTitleName = "Product",
                        favorite = true,
                        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        ratingAvg = 4.0,
                        museumName = "Alex Museum",
                        museumImageUrl = R.drawable.hvlogo,
                        cardId = "7"
                    ),
                    CardUiState(
                        cardTitleName = "Product",
                        favorite = true,
                        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        ratingAvg = 4.0,
                        museumName = "Alex Museum",
                        museumImageUrl = R.drawable.hvlogo,
                        cardId = "8"
                    ), CardUiState(
                        cardTitleName = "Product",
                        favorite = true,
                        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        ratingAvg = 4.0,
                        museumName = "Alex Museum",
                        museumImageUrl = R.drawable.hvlogo,
                        cardId = "9"
                    ), CardUiState(
                        cardTitleName = "Product",
                        favorite = true,
                        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        ratingAvg = 4.0,
                        museumName = "Alex Museum",
                        museumImageUrl = R.drawable.hvlogo,
                        cardId = "10"
                    ),
                    CardUiState(
                        cardTitleName = "Product",
                        favorite = true,
                        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        ratingAvg = 4.0,
                        museumName = "Alex Museum",
                        museumImageUrl = R.drawable.hvlogo,
                        cardId = "11"
                    ), CardUiState(
                        cardTitleName = "Product",
                        favorite = true,
                        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        ratingAvg = 4.0,
                        museumName = "Alex Museum",
                        museumImageUrl = R.drawable.hvlogo,
                        cardId = "12"
                    ), CardUiState(
                        cardTitleName = "Product",
                        favorite = true,
                        imageUrl = "https://images.unsplash.com/photo-1628074958552-7c9d0b4173b7?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
                        ratingAvg = 4.0,
                        museumName = "Alex Museum",
                        museumImageUrl = R.drawable.hvlogo,
                        cardId = "13"
                    ),
                )
            )
        }
    }

    //index must be added when trying api
    //because this code only modifies index 0
    fun onUpdateFavorite(id: Int) {
        //update from api
        updateState {
            it.copy(
                cards = it.cards.mapIndexed() { index, cardUiState ->
                    if (index == id )
                        cardUiState.copy(favorite = !cardUiState.favorite)
                    else
                        cardUiState
                }
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