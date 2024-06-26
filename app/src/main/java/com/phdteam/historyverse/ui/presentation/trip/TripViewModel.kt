package com.phdteam.historyverse.ui.presentation.trip

import androidx.lifecycle.viewModelScope
import com.phdteam.historyverse.data.network.model.Museum
import com.phdteam.historyverse.data.network.model.Trip
import com.phdteam.historyverse.data.network.model.TripChoice
import com.phdteam.historyverse.data.network.repositories.HistoryVerseRepository
import com.phdteam.historyverse.ui.presentation.base.BaseViewModel
import com.phdteam.historyverse.ui.presentation.home.toMuseumUiState
import kotlinx.coroutines.launch

class TripViewModel (
    private val repository: HistoryVerseRepository
) : BaseViewModel<TripUiState, TripUiEffect>(TripUiState()) {

    private fun getTrip(tripChoice: TripChoice) {
        updateState { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                val result = repository.getTripMuseums(tripChoice)
                onSuccessTrip(result.tripPlan)
            } catch (e: Exception) {
                onError()
            }
        }
    }

    private fun onSuccessTrip(trip: List<Trip.TripPlan?>?) {
        updateState { it.copy(trip = trip, isLoading = false) }
    }
    fun onClickChip(city: String) {
        val newTargetCities  = if (state.value.targetCities.contains(city)) {
            state.value.targetCities - city
        } else {
            state.value.targetCities + city
        }
        updateState { it.copy(targetCities = newTargetCities) }
    }
    fun onClickMakeTrip() {
        updateState { it.copy(isLoading = true) }
        tryToExecute(
            repository::getMuseums,
            ::onSuccessMuseums,
            ::onError
        )
    }
    private fun onSuccessMuseums(museums: List<Museum>) {
        updateState { it.copy(museums = museums.toMuseumUiState().filter { museum -> museum.city in state.value.targetCities }.sortedBy { museum -> museum.city }, isLoading = false) }
    }

    private fun onError() {
        updateState { TripUiState(isError = true, isLoading = false) }
    }

}